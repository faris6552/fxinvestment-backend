@echo off
echo ========================================
echo    FxInvestment Backend Starter
echo ========================================
echo.
echo Starting Spring Boot Application...
echo.

cd /d %~dp0
mvn clean spring-boot:run

echo.
echo Application stopped.
pause