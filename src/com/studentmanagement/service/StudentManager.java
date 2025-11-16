package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.util.FileStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class to manage student CRUD operations
 */
public class StudentManager {
    private List<Student> students;
    private FileStorage fileStorage;
    private int nextId;

    public StudentManager() {
        this.fileStorage = new FileStorage();
        this.students = fileStorage.loadStudents();
        this.nextId = calculateNextId();
    }

    /**
     * Calculate the next available ID
     */
    private int calculateNextId() {
        return students.stream()
                .mapToInt(Student::getId)
                .max()
                .orElse(0) + 1;
    }

    /**
     * Add a new student
     */
    public boolean addStudent(String name, String email, int age, String course) {
        // Validate email uniqueness
        if (students.stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(email))) {
            return false;
        }

        Student student = new Student(nextId++, name, email, age, course);
        students.add(student);
        fileStorage.saveStudents(students);
        return true;
    }

    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Find student by ID
     */
    public Optional<Student> findStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    /**
     * Search students by name
     */
    public List<Student> searchStudentsByName(String name) {
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Search students by course
     */
    public List<Student> searchStudentsByCourse(String course) {
        return students.stream()
                .filter(s -> s.getCourse().toLowerCase().contains(course.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Update student information
     */
    public boolean updateStudent(int id, String name, String email, int age, String course) {
        Optional<Student> studentOpt = findStudentById(id);

        if (!studentOpt.isPresent()) {
            return false;
        }

        // Check if email is being changed to one that already exists
        if (students.stream()
                .anyMatch(s -> s.getId() != id && s.getEmail().equalsIgnoreCase(email))) {
            return false;
        }

        Student student = studentOpt.get();
        student.setName(name);
        student.setEmail(email);
        student.setAge(age);
        student.setCourse(course);

        fileStorage.saveStudents(students);
        return true;
    }

    /**
     * Delete student by ID
     */
    public boolean deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (removed) {
            fileStorage.saveStudents(students);
        }
        return removed;
    }

    /**
     * Get total number of students
     */
    public int getTotalStudents() {
        return students.size();
    }

    /**
     * Check if student exists by email
     */
    public boolean existsByEmail(String email) {
        return students.stream()
                .anyMatch(s -> s.getEmail().equalsIgnoreCase(email));
    }
}
