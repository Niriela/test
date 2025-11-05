@echo off
setlocal enabledelayedexpansion

REM === Configuration (modifie si besoin) ===
set SCRIPT_DIR=%~dp0
set PROJECT_ROOT=%SCRIPT_DIR%..
set WEBAPP_SRC=%PROJECT_ROOT%\test\webapp
set BUILD_DIR=%PROJECT_ROOT%\build
set CLASSES_DIR=%BUILD_DIR%\classes
set WAR_NAME=Sprint1.war
set WAR_PATH=%BUILD_DIR%\%WAR_NAME%

REM Tomcat (change si different)
set TOMCAT_HOME=C:\tomcat\apache-tomcat-10.1.28
set TOMCAT_WEBAPPS=%TOMCAT_HOME%\webapps
set APP_NAME=Sprint1

REM Optional: include servlet-api from local lib for compilation (but DO NOT bundle it into the WAR)
set LOCAL_LIB=%WEBAPP_SRC%\WEB-INF\lib
set SERVLET_JAR=%LOCAL_LIB%\servlet-api.jar

REM === Préparation ===
echo Deploy script started...
if not exist "%WEBAPP_SRC%" (
  echo ERROR: webapp source not found: %WEBAPP_SRC%
  exit /b 1
)

REM nettoyer anciens builds
if exist "%CLASSES_DIR%" rd /s /q "%CLASSES_DIR%"
mkdir "%CLASSES_DIR%" >nul 2>&1

REM liste des sources
set SOURCES=%TEMP%\sources_%APP_NAME%.txt
pushd "%PROJECT_ROOT%"
dir /b /s *.java > "%SOURCES%"
if not exist "%SOURCES%" (
  echo Aucun fichier Java trouve.
  popd
  exit /b 1
)

REM === Compilation ===
if exist "%SERVLET_JAR%" (
  echo Compiling Java sources with servlet-api on classpath...
  javac -cp "%SERVLET_JAR%" -d "%CLASSES_DIR%" @"%SOURCES%"
) else (
  echo Compiling Java sources...
  javac -d "%CLASSES_DIR%" @"%SOURCES%"
)

if errorlevel 1 (
  echo Compilation echouee.
  del "%SOURCES%"
  popd
  exit /b 1
)

REM === Préparer structure WAR temporaire ===
set WAR_TMP=%BUILD_DIR%\war_tmp
if exist "%WAR_TMP%" rd /s /q "%WAR_TMP%"
mkdir "%WAR_TMP%" >nul 2>&1

REM copier les fichiers webapp (pages, WEB-INF/web.xml, etc.)
xcopy "%WEBAPP_SRC%\*" "%WAR_TMP%\" /E /I /Y >nul

REM remplacer/mettre les classes compilées
if not exist "%WAR_TMP%\WEB-INF" mkdir "%WAR_TMP%\WEB-INF"
if not exist "%WAR_TMP%\WEB-INF\classes" mkdir "%WAR_TMP%\WEB-INF\classes"
xcopy "%CLASSES_DIR%\*" "%WAR_TMP%\WEB-INF\classes\" /E /I /Y >nul

REM copier les libs (sauf servlet-api.jar si present) dans WEB-INF\lib
if exist "%LOCAL_LIB%" (
  if not exist "%WAR_TMP%\WEB-INF\lib" mkdir "%WAR_TMP%\WEB-INF\lib"
  for %%f in ("%LOCAL_LIB%\*") do (
    if /I "%%~nxf"=="servlet-api.jar" (
      rem skip servlet api - provided by the container
    ) else (
      copy /Y "%%f" "%WAR_TMP%\WEB-INF\lib\" >nul
    )
  )
)

REM === Créer le WAR ===
pushd "%WAR_TMP%"
if exist "%WAR_PATH%" del /f /q "%WAR_PATH%" >nul 2>&1
echo Creating WAR: %WAR_PATH%
jar cvf "%WAR_PATH%" . >nul
popd

REM nettoyage temp
rd /s /q "%WAR_TMP%"

REM === Déployer dans Tomcat ===
if not exist "%TOMCAT_WEBAPPS%" (
  echo ATTENTION: Tomcat webapps not found: %TOMCAT_WEBAPPS%
  echo Copie du WAR locale seulement.
  copy /Y "%WAR_PATH%" "%LOCAL_LIB%\" >nul
  goto :done
)

REM supprimer l'application déployée (exploded) pour forcer redeploy
if exist "%TOMCAT_WEBAPPS%\%APP_NAME%" rd /s /q "%TOMCAT_WEBAPPS%\%APP_NAME%"
if exist "%TOMCAT_WEBAPPS%\%WAR_NAME%" del /f /q "%TOMCAT_WEBAPPS%\%WAR_NAME%"

echo Copying WAR to Tomcat...
copy /Y "%WAR_PATH%" "%TOMCAT_WEBAPPS%\%WAR_NAME%" >nul

echo Deployment finished. Tomcat will (re)deploy the WAR automatically.
:done

REM cleanup
del "%SOURCES%" >nul 2>&1
endlocal
echo Done.