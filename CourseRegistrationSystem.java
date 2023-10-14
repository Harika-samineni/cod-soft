import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean isFull() {
        return registeredStudents.size() >= capacity;
    }

    public boolean registerStudent(Student student) {
        if (!isFull()) {
            registeredStudents.add(student);
            return true;
        }
        return false;
    }

    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && !course.isFull()) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAvailableCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Registered Students: " + course.getRegisteredStudents().size());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic programming concepts", 30, "Mon/Wed 10:00 AM - 11:30 AM");
        Course course2 = new Course("MATH201", "Calculus II", "Advanced calculus topics", 25, "Tue/Thu 2:00 PM - 3:30 PM");

        Student student1 = new Student("12345", "Alice");
        Student student2 = new Student("67890", "Bob");

        system.addCourse(course1);
        system.addCourse(course2);
        system.addStudent(student1);
        system.addStudent(student2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register for a Course");
            System.out.println("2. Remove a Course");
            System.out.println("3. Display Available Courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter your student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter the course code you want to register for: ");
                    String courseCode = scanner.next();

                    Student student = null;
                    for (Student s : system.students) {
                        if (s.getStudentID().equals(studentID)) {
                            student = s;
                            break;
                        }
                    }

                    Course course = null;
                    for (Course c : system.courses) {
                        if (c.getCourseCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }

                    if (student != null && course != null) {
                        if (student.registerCourse(course)) {
                            if (course.registerStudent(student)) {
                                System.out.println("Registration successful.");
                            } else {
                                System.out.println("Course is full. Registration failed.");
                            }
                        } else {
                            System.out.println("You are already registered for this course.");
                        }
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter your student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter the course code you want to remove: ");
                    courseCode = scanner.next();

                    student = null;
                    for (Student s : system.students) {
                        if (s.getStudentID().equals(studentID)) {
                            student = s;
                            break;
                        }
                    }

                    course = null;
                    for (Course c : system.courses) {
                        if (c.getCourseCode().equals(courseCode)) {
                            course = c;
                            break;
                        }
                    }

                    if (student != null && course != null) {
                        if (student.getRegisteredCourses().contains(course)) {
                            student.removeCourse(course);
                            course.removeStudent(student);
                            System.out.println("Course removed successfully.");
                        } else {
                            System.out.println("You are not registered for this course.");
                        }
                    } else {
                        System.out.println("Student or course not found.");
                    }
                    break;

                case 3:
                    system.displayAvailableCourses();
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
