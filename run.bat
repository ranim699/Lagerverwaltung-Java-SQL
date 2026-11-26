@echo off
rem Compiles and starts the Inventory Management app (Windows).
rem Usage: double-click run.bat, or type run.bat in a terminal.
cd /d "%~dp0"

echo Compiling ...
dir /s /b src\*.java | findstr /v /i "\\tests\\" > sources.txt
javac -encoding UTF-8 -cp "lib\classes111.jar;lib\jdom.jar" -d bin @sources.txt
if errorlevel 1 (
  del sources.txt
  echo Compilation failed.
  pause
  exit /b 1
)
del sources.txt

echo Starting ...
java -cp "bin;lib\classes111.jar;lib\jdom.jar" gui.HomeWindow
