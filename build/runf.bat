@echo off
if not "%1"=="" (
	set file=%1
	echo run EAQTool on %file%
	java -jar eaqtool-1.0-jar-with-dependencies.jar -p ./results/ -f %file%
) else (
	echo EA model : no filename!
)

