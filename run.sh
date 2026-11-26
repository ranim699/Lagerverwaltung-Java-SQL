#!/usr/bin/env bash
# Compiles and starts the Inventory Management app (for Git Bash / Linux / macOS).
# Usage:  ./run.sh
set -e
cd "$(dirname "$0")"

# Git Bash on Windows often does not know where Java is installed
if ! command -v javac >/dev/null 2>&1; then
  export PATH="/c/Program Files/Common Files/Oracle/Java/javapath:$PATH"
fi

# Windows uses ";" between classpath entries, Linux/macOS use ":"
SEP=":"
case "$(uname -s)" in MINGW*|MSYS*|CYGWIN*) SEP=";" ;; esac
CP="lib/classes111.jar${SEP}lib/jdom.jar"

echo "Compiling ..."
javac -encoding UTF-8 -cp "$CP" -d bin $(find src -name "*.java" -not -path "*/tests/*")

echo "Starting ..."
java -cp "bin${SEP}${CP}" gui.HomeWindow
