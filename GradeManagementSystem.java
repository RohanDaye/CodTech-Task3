package Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeManagementSystem {
    private static Map<String, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student\n2. Update Student\n3. Delete Student\n4. Calculate Grades\n5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    calculateGrades();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.println("Enter student name: ");
        String name = scanner.next();

        System.out.println("Enter student roll number: ");
        String rollNumber = scanner.next();

        System.out.println("Enter marks for each subject (comma-separated): ");
        String[] subjects = scanner.next().split(",");
        int[] marks = new int[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            marks[i] = Integer.parseInt(subjects[i]);
        }

        students.put(rollNumber, new Student(name, rollNumber, marks));
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        System.out.println("Enter student roll number to update: ");
        String rollNumber = scanner.next();

        if (students.containsKey(rollNumber)) {
            System.out.println("Enter new marks for each subject (comma-separated): ");
            String[] subjects = scanner.next().split(",");
            int[] marks = new int[subjects.length];
            for (int i = 0; i < subjects.length; i++) {
                marks[i] = Integer.parseInt(subjects[i]);
            }

            students.get(rollNumber).setMarks(marks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.println("Enter student roll number to delete: ");
        String rollNumber = scanner.next();

        if (students.containsKey(rollNumber)) {
            students.remove(rollNumber);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void calculateGrades() {
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            Student student = entry.getValue();
            double percentage = student.calculatePercentage();
            char grade = student.calculateGrade(percentage);

            System.out.println("Roll Number: " + student.getRollNumber() + ", Percentage: " + percentage + ", Grade: " + grade);
        }
    }
}

class Student {
    private String name;
    private String rollNumber;
    private int[] marks;

    public Student(String name, String rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public double calculatePercentage() {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return (double) totalMarks / marks.length;
    }

    public char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
