package student_grades;

import java.util.*;

public class Classroom {
    private Student[] students;

    public Student[] getStudents() {
        return students;
    }

//    public void setStudents(Student[] students) {
//        this.students = students;
//    }

    public Classroom(int maxNumberOfStudents){
        this(new Student[maxNumberOfStudents]);
    }
    public Classroom(Student[] students){
        this.students = students;
    }
    public Classroom(){
        this(new Student[30]);
    }

    public double getClassAverageExamScore(){
        double average = 0;
        int emptyStudentSpots = 0;
        for(Student student: students){
            if (student != null){
                average += student.getAverageExamScore();
            } else{
                emptyStudentSpots++;
            }
        }
        int activeStudentSpots = students.length - emptyStudentSpots;
        return (double) Math.round(average / activeStudentSpots * 100) /100;
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if(students[i] == null) {
                students[i] = student;
                break;
            }
        }
    }


    public void removeStudent(String firstName, String lastName) {
        for (int i = 0; i < students.length; i++ ) {
            if (students[i].getFirstName().equals(firstName) && students[i].getLastName().equals(lastName)) {
                students[i] = null;
                Student emptyStudent = students[i];
                while ((i+1 < students.length) && (students[i+1] != null)) {
                    students[i] = students[i+1];
                    i++;
                }
                students[students.length - 1] = emptyStudent;
            }
        }
    }

    public Student[] getStudentsByScore() {
        ArrayList<Student> studentList = new ArrayList<>(Arrays.asList(students));
        Comparator<Student> comp = new Comparator<Student>(){
            public int compare(Student i, Student j){
                    if(i == null)
                        return 0;
                    if (i.getAverageExamScore() < j.getAverageExamScore())
                        return 1;
                    else
                        return -1;
                }

        };
        Collections.sort(studentList, comp);
        Student[] studentsByScore = new Student[studentList.size()];
        studentsByScore = studentList.toArray(studentsByScore);
        return studentsByScore;
    }

    public Map<Student, Character> getGradeBook() {
        char grade;
        Map<Student, Character> gradeBook = new HashMap<>();
        for(int i = 0; i < students.length; i++) {

            try {
                double studentScore = students[i].getAverageExamScore();
                if (studentScore < 0) {
                    throw new IllegalArgumentException("Negative scores cannot be graded.");
                } else if (studentScore < 11.0) {
                    grade = 'F';
                } else if (studentScore < 50.0) {
                    grade = 'D';
                } else if (studentScore < 70.0) {
                    grade = 'C';
                } else if (studentScore < 90.0) {
                    grade = 'B';
                } else {
                    grade = 'A';
                }
                gradeBook.put(students[i], grade);
            } catch(IllegalArgumentException ile){
                throw new IllegalArgumentException("Student's score than 0: " + ile.getMessage());
            }
        }
        return gradeBook;
    }
    @Override
    public String toString() {
        return "Students in this classroom: \n" +
                "students=" + Arrays.toString(students) +
                '}';
    }
}
