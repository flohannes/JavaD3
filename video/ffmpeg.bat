@ECHO OFF

SET "chartDirectory=%1"
SET "framerate=%2"
SET "outputFilePath=%3"
SET "basePath=%4"



START /W ./ffmpeg.exe -start_number 10 -i %chartDirectory%%%02d.jpeg -framerate %framerate% -c:v libx264 -r %framerate% -pix_fmt yuv420p ./%outputFilePath%.mp4