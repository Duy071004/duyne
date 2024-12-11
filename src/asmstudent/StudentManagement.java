/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package asmstudent;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private final String id;
    private String fullName;
    private double score;

    public Student(String id, String fullName, double score) {
        this.id = id;
        this.fullName = fullName;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRating() {
        if (score < 5.0) return "Fail";
        else if (score < 6.5) return "Medium";
        else if (score < 7.5) return "Good";
        else if (score < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Score: " + score + ", Rating: " + getRating();
    }
}

public class StudentManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Student> students = new ArrayList<>();

public void addStudent() {
    String id, name;
    double score;

    // Kiểm tra ID không được để trống
    while (true) {
        System.out.print("Enter Student ID: ");
        id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: Student ID cannot be empty. Please try again.");
        } else {
            // Kiểm tra ID trùng lặp
            boolean isDuplicate = false;
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (isDuplicate) {
                System.out.println("Error: Student ID already exists. Please enter a unique ID.");
            } else {
                break;
            }
        }
    }

    // Kiểm tra Tên không được để trống
    while (true) {
        System.out.print("Enter Full Name: ");
        name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Student Name cannot be empty. Please try again.");
        } else {
            break;
        }
    }

    // Kiểm tra Điểm không được để trống và phải hợp lệ
    while (true) {
        System.out.print("Enter Score (0 to 10): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Error: Score cannot be empty. Please try again.");
            continue;
        }
        try {
            score = Double.parseDouble(input);
            if (score >= 0 && score <= 10) {
                break; // Điểm hợp lệ
            } else {
                System.out.println("Error: Score must be between 0 and 10.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid numeric score.");
        }
    }

    // Thêm sinh viên vào danh sách sau khi kiểm tra thành công
    students.add(new Student(id, name, score));
    System.out.println("Student added successfully!");
}



    public void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new Name: ");
                student.setFullName(scanner.nextLine());
                System.out.print("Enter new Score: ");
                student.setScore(Double.parseDouble(scanner.nextLine()));
                System.out.println("Student details updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

public void deleteStudent() {
    System.out.print("Enter Student ID to delete: ");
    String id = scanner.nextLine().trim();

    boolean found = false;

    // Tìm và xóa sinh viên có ID khớp
    for (Student student : students) {
        if (student.getId().equals(id)) {
            students.remove(student);
            found = true;
            System.out.println("Student deleted successfully!");
            break; // Thoát vòng lặp sau khi xóa
        }
    }

    // Nếu không tìm thấy ID
    if (!found) {
        System.out.println("Error: Student ID not found. No student was deleted.");
    }
}


    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

public void sortStudents() {
    System.out.println("Choose sorting order:");
    System.out.println("1. Ascending");
    System.out.println("2. Descending");
    System.out.print("Enter your choice: ");
    int choice = Integer.parseInt(scanner.nextLine());

    switch (choice) {
        case 1 -> {
            // Sort ascending
            for (int i = 0; i < students.size() - 1; i++) {
                for (int j = 0; j < students.size() - 1 - i; j++) {
                    if (students.get(j).getScore() > students.get(j + 1).getScore()) {
                        Student temp = students.get(j);
                        students.set(j, students.get(j + 1));
                        students.set(j + 1, temp);
                    }
                }
            }
            System.out.println("Students sorted from low to high (ascending):");
            displayStudents();
            }

        case 2 -> {
            // Sort descending
            for (int i = 0; i < students.size() - 1; i++) {
                for (int j = 0; j < students.size() - 1 - i; j++) {
                    if (students.get(j).getScore() < students.get(j + 1).getScore()) {
                        Student temp = students.get(j);
                        students.set(j, students.get(j + 1));
                        students.set(j + 1, temp);
                    }
                }
            }
            System.out.println("Students sorted from high to low (descending):");
            displayStudents();
            }

        default -> System.out.println("Invalid choice. Returning to main menu.");
    }
}


    public void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort Students");
            System.out.println("6. Search Student");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> management.addStudent();
                case 2 -> management.editStudent();
                case 3 -> management.deleteStudent();
                case 4 -> management.displayStudents();
                case 5 -> management.sortStudents();
                case 6 -> management.searchStudent();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }
}
