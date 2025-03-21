@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  jsonplaceholder-api startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and JSONPLACEHOLDER_API_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo. 1>&2
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% 1>&2
echo. 1>&2
echo Please set the JAVA_HOME variable in your environment to match the 1>&2
echo location of your Java installation. 1>&2

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\jsonplaceholder-api-1.0-SNAPSHOT.jar;%APP_HOME%\lib\rest-assured-5.5.0.jar;%APP_HOME%\lib\commons-codec-1.17.1.jar;%APP_HOME%\lib\jackson-annotations-2.17.2.jar;%APP_HOME%\lib\jackson-core-2.17.2.jar;%APP_HOME%\lib\jackson-databind-2.17.2.jar;%APP_HOME%\lib\allure-rest-assured-2.29.0.jar;%APP_HOME%\lib\guice-6.0.0.jar;%APP_HOME%\lib\guava-33.2.1-jre.jar;%APP_HOME%\lib\gson-2.10.1.jar;%APP_HOME%\lib\allure-attachments-2.29.0.jar;%APP_HOME%\lib\allure-java-commons-2.29.0.jar;%APP_HOME%\lib\slf4j-api-2.0.16.jar;%APP_HOME%\lib\xml-path-5.5.0.jar;%APP_HOME%\lib\json-path-5.5.0.jar;%APP_HOME%\lib\groovy-json-4.0.22.jar;%APP_HOME%\lib\groovy-xml-4.0.22.jar;%APP_HOME%\lib\rest-assured-common-5.5.0.jar;%APP_HOME%\lib\groovy-4.0.22.jar;%APP_HOME%\lib\httpmime-4.5.13.jar;%APP_HOME%\lib\httpclient-4.5.13.jar;%APP_HOME%\lib\hamcrest-2.2.jar;%APP_HOME%\lib\tagsoup-1.2.1.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\jakarta.inject-api-2.0.1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\failureaccess-1.0.2.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\checker-qual-3.42.0.jar;%APP_HOME%\lib\error_prone_annotations-2.26.1.jar;%APP_HOME%\lib\httpcore-4.4.13.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-lang3-3.11.jar;%APP_HOME%\lib\freemarker-2.3.33.jar;%APP_HOME%\lib\allure-model-2.29.0.jar


@rem Execute jsonplaceholder-api
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JSONPLACEHOLDER_API_OPTS%  -classpath "%CLASSPATH%"  %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable JSONPLACEHOLDER_API_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%JSONPLACEHOLDER_API_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
