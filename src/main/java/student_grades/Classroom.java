package student_grades;

import java.util.Arrays;
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
        for(Student student: students){
            average += student.getAverageExamScore();
        }
        return average / students.length;
    }

    public void addStudent(Student student) {
        for (int i = 0; i < students.length; i++) {
            if(students[i] == null) {
                students[i] = student;
                break;
            } else{
                System.out.println("Classroom is full! Cannot add new student");
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
        double[] studentAverages = new double[students.length];
        for (int i = 0; i < students.length; i++) {
            studentAverages[i] = students[i].getAverageExamScore();
        }
        Arrays.sort(studentAverages);
        for (int x = students.length - 1; x >= 0; x--) {
            for (int y = 0; y < students.length; y++) {
                if (studentAverages[x] == students[y].getAverageExamScore()) {
                    if ((x + 1) < students.length && studentAverages[x] == studentAverages[x + 1]) {
                        if (y-1 >= 0 && students[y].getFirstName().compareTo(students[y-1].getFirstName()) > 0 || y-1 >= 0 && students[y].getLastName().compareTo(students[y-1].getLastName()) > 0) {
                            Student orderedStudent = students[y];
                            students[y] = students[y - 1];
                            students[y - 1] = orderedStudent;
                        }
                    }
                    studentsByScore[students.length - (x + 1)] = students[y];
                }
            }
        }
        return studentsByScore;
    }



    public Map<String, Character> getGradeBook() {
        char grade;
        Map<String, Character> gradeBook = new HashMap<>();

        for(int i = 0; i < students.length; i++) {
            double studentScore = students[i].getAverageExamScore();
            if (studentScore > 90.0) {
                grade = 'A';
            }else if (studentScore >= 70.0) {
                grade = 'B';
            }else if (studentScore >= 50.0) {
                grade = 'C';
            }else if (studentScore > 11.0) {
                grade = 'D';
            } else{
                grade = 'F';
            }
            String studentName = students[i].getFirstName() + " " + students[i].getLastName();
            gradeBook.put(studentName, grade);
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
