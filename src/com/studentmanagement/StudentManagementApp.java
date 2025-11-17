package com.studentmanagement;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class for Student Management System
 */
public class StudentManagementApp {
    private static StudentManager studentManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        studentManager = new StudentManager();
        scanner = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║   STUDENT MANAGEMENT SYSTEM                       ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.println();

        boolean running = true;
        while (running) {
            running = showMenuAndProcess();
        }

        scanner.close();
        System.out.println("\nThank you for using Student Management System!");
    }

    private static boolean showMenuAndProcess() {
        printMenu();
        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                viewAllStudents();
                break;
            case 3:
                searchStudent();
                break;
            case 4:
                updateStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                showStatistics();
                break;
            case 0:
                return false;
            default:
                System.out.println("\n Invalid choice! Please try again.\n");
        }

        return true;
    }

    private static void printMenu() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│              MAIN MENU                          │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│  1. Add New Student                             │");
        System.out.println("│  2. View All Students                           │");
        System.out.println("│  3. Search Student                              │");
        System.out.println("│  4. Update Student                              │");
        System.out.println("│  5. Delete Student                              │");
        System.out.println("│  6. Statistics                                  │");
        System.out.println("│  0. Exit                                        │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    private static void addStudent() {
        System.out.println("\n═══ ADD NEW STUDENT ═══");

        String name = getStringInput("Enter student name: ");
        String email = getStringInput("Enter student email: ");

        if (studentManager.existsByEmail(email)) {
            System.out.println(" Error: A student with this email already exists!\n");
            return;
        }

        int age = getIntInput("Enter student age: ");
        String course = getStringInput("Enter course name: ");

        if (studentManager.addStudent(name, email, age, course)) {
            System.out.println("✓ Student added successfully!\n");
        } else {
            System.out.println(" Error: Failed to add student!\n");
        }
    }

    private static void viewAllStudents() {
        System.out.println("\n═══ ALL STUDENTS ═══");

        List<Student> students = studentManager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found in the system.\n");
            return;
        }

        System.out.println("Total students: " + students.size());
        System.out.println("─".repeat(100));

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("─".repeat(100));
        System.out.println();
    }

    private static void searchStudent() {
        System.out.println("\n═══ SEARCH STUDENT ═══");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Course");

        int choice = getIntInput("Enter search type: ");

        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchByCourse();
                break;
            default:
                System.out.println(" Invalid search type!\n");
        }
    }

    private static void searchById() {
        int id = getIntInput("Enter student ID: ");
        Optional<Student> student = studentManager.findStudentById(id);

        if (student.isPresent()) {
            System.out.println("\n✓ Student found:");
            System.out.println("─".repeat(100));
            System.out.println(student.get());
            System.out.println("─".repeat(100));
            System.out.println();
        } else {
            System.out.println(" No student found with ID: " + id + "\n");
        }
    }

    private static void searchByName() {
        String name = getStringInput("Enter student name (or part of it): ");
        List<Student> students = studentManager.searchStudentsByName(name);

        displaySearchResults(students, "name '" + name + "'");
    }

    private static void searchByCourse() {
        String course = getStringInput("Enter course name (or part of it): ");
        List<Student> students = studentManager.searchStudentsByCourse(course);

        displaySearchResults(students, "course '" + course + "'");
    }

    private static void displaySearchResults(List<Student> students, String criteria) {
        if (students.isEmpty()) {
            System.out.println(" No students found matching " + criteria + "\n");
            return;
        }

        System.out.println("\n✓ Found " + students.size() + " student(s) matching " + criteria + ":");
        System.out.println("─".repeat(100));

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("─".repeat(100));
        System.out.println();
    }

    private static void updateStudent() {
        System.out.println("\n═══ UPDATE STUDENT ═══");

        int id = getIntInput("Enter student ID to update: ");
        Optional<Student> studentOpt = studentManager.findStudentById(id);

        if (!studentOpt.isPresent()) {
            System.out.println(" No student found with ID: " + id + "\n");
            return;
        }

        Student student = studentOpt.get();
        System.out.println("\nCurrent details:");
        System.out.println(student);
        System.out.println();

        String name = getStringInput("Enter new name (current: " + student.getName() + "): ");
        String email = getStringInput("Enter new email (current: " + student.getEmail() + "): ");
        int age = getIntInput("Enter new age (current: " + student.getAge() + "): ");
        String course = getStringInput("Enter new course (current: " + student.getCourse() + "): ");

        if (studentManager.updateStudent(id, name, email, age, course)) {
            System.out.println("✓ Student updated successfully!\n");
        } else {
            System.out.println(" Error: Failed to update student (email might already exist)!\n");
        }
    }

    private static void deleteStudent() {
        System.out.println("\n═══ DELETE STUDENT ═══");

        int id = getIntInput("Enter student ID to delete: ");
        Optional<Student> student = studentManager.findStudentById(id);

        if (!student.isPresent()) {
            System.out.println(" No student found with ID: " + id + "\n");
            return;
        }

        System.out.println("\nStudent to delete:");
        System.out.println(student.get());
        System.out.println();

        String confirmation = getStringInput("Are you sure you want to delete this student? (yes/no): ");

        if (confirmation.equalsIgnoreCase("yes")) {
            if (studentManager.deleteStudent(id)) {
                System.out.println("✓ Student deleted successfully!\n");
            } else {
                System.out.println(" Error: Failed to delete student!\n");
            }
        } else {
            System.out.println("ℹ Delete operation cancelled.\n");
        }
    }

    private static void showStatistics() {
        System.out.println("\n═══ SYSTEM STATISTICS ═══");
        System.out.println("Total Students: " + studentManager.getTotalStudents());
        System.out.println();
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a valid number.");
            }
        }
    }
}
