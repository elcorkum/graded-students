package student_grades;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        Student[] studentsByScore = new Student[students.length];
        Double[] studentAverages = new Double[students.length];
        for (int i = 0; i < students.length; i++) {
            if(students[i] != null){
                studentAverages[i] = students[i].getAverageExamScore();
            } else{studentAverages[i] = 0.0;}
        }

        Arrays.sort(studentAverages, Collections.reverseOrder());

        for(int x = 0; x < students.length; x++){
            for(int y = 0; y < students.length; y++){
                if(students[y] != null){
                    if(studentAverages[x] == students[y].getAverageExamScore() && x + 1 < students.length && studentAverages[x].equals(studentAverages[x + 1])){
                        if(y + 1 < students.length && studentAverages[x + 1].equals(studentAverages[y + 1])){
                            if(students[y].getFirstName().compareTo(students[y+1].getFirstName()) > 0 || students[y].getLastName().compareTo(students[y+1].getLastName()) > 0){
                                studentsByScore[x] = students[y];
                                studentsByScore[x + 1] = students[y +1];
                                x++;
                            }
                        }
                    }
                    if(studentAverages[x] == students[y].getAverageExamScore()){
                        studentsByScore[x] = students[y];
                    }
                }
            }
        }
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
