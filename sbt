#!/bin/sh

# IntelliJ Idea Debugging
#DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5005"

# Internal options, always specified
INTERNAL_OPTS="-Dfile.encoding=UTF-8 -Xss8M -Xmx1G -XX:MaxPermSize=768M -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -noverify"
#INTERNAL_OPTS="-Dfile.encoding=UTF-8 -Xss8M -Xmx1G -XX:MaxPermSize=768M -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -noverify -javaagent:/usr/jrebel/jrebel.jar -Drebel.lift_plugin=true"

# Default options, if nothing is specified
DEFAULT_OPTS="-Dsbt.intransitive=true"

exec java ${INTERNAL_OPTS} ${DEBUG} ${DEFAULT_OPTS} -jar $( dirname $0 )/sbt-launch.jar "$@"


