package student_grades;
import java.util.*;

public class StudentTester {
    public static void main(String[] args) {

        Double[] s1Scores = { 90.0, 95.0};
        Double[] s2Scores = { 70.0, 35.0, 78.8, 85.2};
        Double[] s3Scores = {28.0, 57.0};
        Double[] s4Scores = { 95.0, 90.0};

        Student s1 = new Student("Penny", "Soo", s1Scores);
        Student s2 = new Student("Bobby", "Blue", s2Scores);
        Student s3 = new Student("Lilly", "Lou", s3Scores);
        Student s4 = new Student("Penny", "Pugh", s4Scores);
        System.out.println(s2.getNumberOfExamsTaken());
        System.out.println(s2.getListOfExamScores());
        //s3.addExamScore(67.5);
        System.out.println(s3.getListOfExamScores());
        //s4.setExamScore(1, 82.5);
        System.out.println(s1.getAverageExamScore());
        System.out.println(s2);
        System.out.println("==========================================");

        Student[] students = {s1, s2, s3, s4};
        Classroom classroom = new Classroom(students);
        //classroom.removeStudent("Bobby", "Blue");
        System.out.println(classroom.getClassAverageExamScore());
        System.out.println("==========================================");
        System.out.println(Arrays.toString(classroom.getStudents()));
        System.out.println("==========================================");

        Student[] studentsInClassroom = classroom.getStudentsByScore();
        for(Student student : studentsInClassroom){
            System.out.println(student);
        }
        Map<Student, Character> gradeBook = classroom.getGradeBook();
        Iterator<Map.Entry<Student, Character>> iterator = gradeBook.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Student, Character> studentGrade = iterator.next();
            System.out.println(studentGrade.getKey() + "Grade : " +studentGrade.getValue());
       }

    }
}
