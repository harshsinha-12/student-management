# Student Management System

A comprehensive Java-based student management system with CRUD operations and file-based persistence.

## Features

- **Add Students**: Register new students with unique email validation
- **View All Students**: Display all registered students in a formatted table
- **Search Students**: Search by ID, name, or course
- **Update Students**: Modify student information
- **Delete Students**: Remove students with confirmation
- **Data Persistence**: Automatic file-based storage
- **Statistics**: View system statistics

## Project Structure

```
student-management/
├── src/
│   └── com/
│       └── studentmanagement/
│           ├── model/
│           │   └── Student.java          # Student entity class
│           ├── service/
│           │   └── StudentManager.java   # Business logic and CRUD operations
│           ├── util/
│           │   └── FileStorage.java      # File-based persistence
│           └── StudentManagementApp.java # Main application with CLI
├── data/
│   └── students.dat                      # Student data storage (auto-generated)
├── bin/                                  # Compiled classes (auto-generated)
├── compile.sh                            # Compilation script
└── run.sh                                # Run script
```

## Requirements

- Java JDK 8 or higher
- Linux/Unix environment (or Git Bash on Windows)

## Installation & Running

### Method 1: Using Shell Scripts (Linux/Mac/Git Bash)

1. **Compile the project:**
   ```bash
   ./compile.sh
   ```

2. **Run the application:**
   ```bash
   ./run.sh
   ```

### Method 2: Manual Compilation

1. **Compile:**
   ```bash
   mkdir -p bin
   javac -d bin src/com/studentmanagement/model/*.java \
                 src/com/studentmanagement/util/*.java \
                 src/com/studentmanagement/service/*.java \
                 src/com/studentmanagement/*.java
   ```

2. **Run:**
   ```bash
   java -cp bin com.studentmanagement.StudentManagementApp
   ```

## Usage

When you run the application, you'll see an interactive menu:

```
╔═══════════════════════════════════════════════════╗
║   STUDENT MANAGEMENT SYSTEM                       ║
╚═══════════════════════════════════════════════════╝

┌─────────────────────────────────────────────────┐
│              MAIN MENU                          │
├─────────────────────────────────────────────────┤
│  1. Add New Student                             │
│  2. View All Students                           │
│  3. Search Student                              │
│  4. Update Student                              │
│  5. Delete Student                              │
│  6. Statistics                                  │
│  0. Exit                                        │
└─────────────────────────────────────────────────┘
```

### Operations

1. **Add New Student**
   - Enter student details (name, email, age, course)
   - Email must be unique

2. **View All Students**
   - Displays all students in a formatted table

3. **Search Student**
   - Search by ID: Find specific student
   - Search by Name: Find students with matching names
   - Search by Course: Find students in a specific course

4. **Update Student**
   - Enter student ID
   - Update any field (name, email, age, course)

5. **Delete Student**
   - Enter student ID
   - Confirm deletion

6. **Statistics**
   - View total number of students

## Student Model

Each student has the following attributes:
- **ID**: Unique identifier (auto-generated)
- **Name**: Student's full name
- **Email**: Unique email address
- **Age**: Student's age
- **Course**: Enrolled course name

## Data Persistence

- Student data is automatically saved to `data/students.dat`
- Data persists between application runs
- Uses Java serialization for storage

## Error Handling

- Email uniqueness validation
- Input validation for all fields
- File I/O error handling
- Graceful error messages

## Example Workflow

```bash
# Compile
./compile.sh

# Run
./run.sh

# In the application:
# 1. Add a student (John Doe, john@example.com, 20, Computer Science)
# 2. View all students
# 3. Search by course "Computer"
# 4. Update student details
# 5. Delete student with confirmation
```

## Technical Details

- **Language**: Java 8+
- **Design Pattern**: Service layer pattern
- **Storage**: File-based serialization
- **Collections**: Java Streams API for filtering
- **Error Handling**: Try-catch blocks with user-friendly messages

## Future Enhancements

- Database integration (MySQL, PostgreSQL)
- GUI interface (JavaFX or Swing)
- Export to CSV/Excel
- Batch import functionality
- Advanced search filters
- Student grade management
- Attendance tracking

## License

This project is open source and available for educational purposes.