@echo off
rem deploy.bat - compile Java sources, build WAR and copy to Tomcat webapps
setlocal enabledelayedexpansion

cd /d "d:\ITU_S5\Naina\FRAMEWORK\Sprint_framework\test" || (
	echo Failed to change directory.
	exit /b 1
)

rem Adjust this path to your Tomcat webapps folder if needed
set "TOMCAT_WEBAPPS=C:\tomcat\apache-tomcat-10.1.28\webapps"

echo Project dir: %CD%
echo Tomcat webapps: %TOMCAT_WEBAPPS%

rem Ensure classes output dir exists
if not exist "webapp\WEB-INF\classes" (
	mkdir "webapp\WEB-INF\classes"
)

rem Create temporary sources list
set "SOURCES=%TEMP%\deploy_sources.txt"
if exist "%SOURCES%" del /f /q "%SOURCES%"

echo Gathering .java files...
for /R %%f in (*.java) do (
	set "p=%%~f"
	set "p=!p:\=/!"
	echo !p! >> "%SOURCES%"
)

if not exist "%SOURCES%" (
	echo No Java source files found.
	exit /b 1
)

echo Compiling Java sources...
javac -cp "webapp\WEB-INF\lib\*" -d "webapp\WEB-INF\classes" @"%SOURCES%"
if errorlevel 1 (
	echo javac failed.
	del "%SOURCES%"
	exit /b 1
)

del "%SOURCES%"

echo Creating WAR file...
if exist test.war del /f /q test.war
jar -cvf test.war -C webapp .
if errorlevel 1 (
	echo jar failed.
	exit /b 1
)

if not exist "%TOMCAT_WEBAPPS%" (
	echo Tomcat webapps directory not found: %TOMCAT_WEBAPPS%
	echo Edit the TOMCAT_WEBAPPS variable in this script and re-run.
	exit /b 1
)

echo Copying WAR to Tomcat webapps...
copy /Y test.war "%TOMCAT_WEBAPPS%\" >nul
if errorlevel 1 (
	echo Copy failed.
	exit /b 1
)

echo Deployment successful. Access: http://localhost:8080/test/
endlocal
exit /b 0