import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getMarks() {
        return marks;
    }
    public String getRank() {
        if (marks < 5.0) return "Fail";
        if (marks < 6.5) return "Medium";
        if (marks < 7.5) return "Good";
        if (marks < 9.0) return "Very Good";
        return "Excellent";
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMarks(double marks) {
        this.marks = marks;
    }
    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}
class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        System.out.println("Enter Student ID:");
        String id = scanner.nextLine();
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Student Marks:");
        double marks = scanner.nextDouble();
        scanner.nextLine();  // consume newline
        students.add(new Student(id, name, marks));
    }
    public void editStudent() {
        System.out.println("Enter Student ID to Edit:");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Enter New Name:");
                student.setName(scanner.nextLine());
                System.out.println("Enter New Marks:");
                student.setMarks(scanner.nextDouble());
                scanner.nextLine();
                System.out.println("Student Updated!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }
    public void deleteStudent() {
        System.out.println("Enter Student ID to Delete:");
        String id = scanner.nextLine();
        students.removeIf(student -> student.getId().equals(id));
        System.out.println("Student Removed!");
    }
    public void searchStudent() {
        System.out.println("Enter Student ID to Search:");
        String id = scanner.nextLine();
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    public void sortStudents() {
        int n = students.size(); // Lấy kích thước của danh sách sinh viên
        // Vòng lặp để thực hiện nhiều lần sắp xếp
        for (int outer = 0; outer < n - 1; outer++) {
            // Biến swapped để kiểm tra có sự hoán đổi hay không
            boolean swapped = false;
            // Vòng lặp so sánh từng cặp sinh viên
            for (int inner = 0; inner < n - outer - 1; inner++) {
                // So sánh điểm của sinh viên hiện tại với sinh viên tiếp theo
                if (students.get(inner).getMarks() < students.get(inner + 1).getMarks()) {
                    // Hoán đổi vị trí nếu cần
                    Student temp = students.get(inner);
                    students.set(inner, students.get(inner + 1));
                    students.set(inner + 1, temp);
                    swapped = true; // Đặt swapped là true nếu có sự hoán đổi
                }
            }
            // Nếu không có sự hoán đổi nào, danh sách đã được sắp xếp
            if (!swapped) {
                break;
            }
        }

        System.out.println("Students Sorted by Marks using Bubble Sort!");
        printAllStudents(); // In danh sách sinh viên đã sắp xếp
    }

    public void printAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Print All Students");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.addStudent();
                    break;
                case 2:
                    manager.editStudent();
                    break;
                case 3:
                    manager.deleteStudent();
                    break;
                case 4:
                    manager.searchStudent();
                    break;
                case 5:
                    manager.sortStudents();
                    break;
                case 6:
                    manager.printAllStudents();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
