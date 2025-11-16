#!/bin/bash

# Check if compiled classes exist
if [ ! -d "bin" ]; then
    echo "Please compile first using ./compile.sh"
    exit 1
fi

# Run the application
java -cp bin com.studentmanagement.StudentManagementApp
