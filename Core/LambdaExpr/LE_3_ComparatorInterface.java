import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
https://www.geeksforgeeks.org/comparator-interface-java/

Comparator interface is used to order user-defined objects.
*/
class Student {
    public int rollNumber;
    public String name;
    public int score;

    Student(int rollNumber, String name, int score) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.score = score;
    }
    @Override
    public String toString() {
        return "\nStudent: [name: '" + name + "', roll number: '" + rollNumber + "', score: '" + score + "']";
    }
}

// Method 1: Sorting by rollNumber - actually implement the interface
class SortByRollNumber implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        if (student1.rollNumber == student2.rollNumber) {
            return 0;
        } else if (student1.rollNumber > student2.rollNumber) {
            return 1;
        } else {
            return -1;
        }
    }
}

class LE_3_ComparatorInterface {
    public static void main(String[] args) {
        Student elassar = new Student(54, "Elassar", 67);
        Student gandalf = new Student(23, "Gandalf", 59);
        Student sauroman = new Student(21, "Sauroman", 78);
        Student melkor = new Student(11, "Melkor", 88);
        Student feanor = new Student(35, "Feanor", 91);
        Student glorfindel = new Student(37, "Glorfindel", 91);
        Student glaurong = new Student(47, "Glaurong", 48);

        List<Student> students = new ArrayList<>();
        students.add(elassar);
        students.add(gandalf);
        students.add(sauroman);
        students.add(melkor);
        students.add(feanor);
        students.add(glorfindel);
        students.add(glaurong);
        System.out.println("Collection before sorting:\n" + students);

        // Method 1: Sorting by rollNumber - using implemented interface
        Collections.sort(students, new SortByRollNumber());
        System.out.println("\nCollection after sorting by Roll Number:\n" + students);

        // Method 2: Sort by score - using anonymous class
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                if (student1.score == student2.score) {
                    return 0;
                } else if (student1.score > student2.score) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        System.out.println("\nCollection after sorting by Score:\n" + students);

        // Method 3: Sort by name - using lambda
        Collections.sort(students, (student1, student2) -> student1.name.compareTo(student2.name));
        System.out.println("\nCollection after sorting by name:\n" + students);
    }
}