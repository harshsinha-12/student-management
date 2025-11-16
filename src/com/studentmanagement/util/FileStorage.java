package com.studentmanagement.util;

import com.studentmanagement.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for file-based storage of student data
 */
public class FileStorage {
    private static final String DATA_FILE = "data/students.dat";

    /**
     * Save students to file
     */
    public void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    /**
     * Load students from file
     */
    @SuppressWarnings("unchecked")
    public List<Student> loadStudents() {
        File file = new File(DATA_FILE);

        // Create data directory if it doesn't exist
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Clear all student data
     */
    public void clearData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
