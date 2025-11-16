#!/bin/bash

echo "Compiling Student Management System..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin src/com/studentmanagement/model/*.java \
              src/com/studentmanagement/util/*.java \
              src/com/studentmanagement/service/*.java \
              src/com/studentmanagement/*.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo "Run './run.sh' to start the application"
else
    echo "❌ Compilation failed!"
    exit 1
fi
