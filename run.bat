@echo off
echo *****************************************************************************
echo ************************* WELCOME TO INTENSITY-CALC *************************
echo *****************************************************************************
echo 
echo PROCESSING FILES...
For /f "tokens=2-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%a-%%b)
For /f "tokens=1-2 delims=/:" %%a in ("%TIME%") do (set mytime=%%a%%b)
java -jar intensityCalc.jar > "Resultado_%mydate%_%mytime%.txt"
echo FINISHED
echo *****************************************************************************
pause 