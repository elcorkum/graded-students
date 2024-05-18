package student_grades;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

class ClassroomShould {
    Double[] testScores1 = {46.5, 83.4, 64.0};
    Double[] testScores2 = {86.5, 53.4, 74.0};
    Double[] testScores3 = {46.5, 48.4, 44.0};
    Double[] testScores4 = {8.5, 9.4, 0.0};
    Double[] testScores5 = {96.5, 90.5, 98.0};
    Double[] testScores6 = {70.0, 70.0, 70.0};
    Double[] testScores7 = {90.0};
    Double[] testScores8 = {50.0};
    Double[] testScores9 = {11.0};
    Double[] testScores10 = {0.0};
    Double[] testScores11 = {-1.0};
    Double[] testScores12 = {53.4, 86.5, 74.0};
    Student student1 = new Student("Cami", "Cabello", testScores1);
    Student student2 = new Student("May", "Flower", testScores2);
    Student student3 = new Student("Jay", "Bloom", testScores3);
    Student student4 = new Student("Billy", "Brown", testScores4);
    Student student5 = new Student("Joey", "Mack", testScores5);
    Student student6 = new Student("Blue", "Misty", testScores6);
    Student student7 = new Student("Bo", "June", testScores7);
    Student student8 = new Student("Neo", "Ned", testScores8);
    Student student9 = new Student("Jane", "J", testScores9);
    Student student10 = new Student("Imani", "Musiki", testScores10);
    Student student11 = new Student("Pendo", "Mali", testScores11);
    Student student12 = new Student("May", "Bee", testScores12);
    Student[] studentsInClassroom = {student1, student2, student3};
    Classroom classroom = new Classroom(studentsInClassroom);
    Student[] gradedStudents = {student1, student2, student3, student4, student5, student6, student7,
            student8, student9, student10};
    Classroom room1 = new Classroom(gradedStudents);

    @Test
    @DisplayName("calculateCorrectClassroomAverageScore")
    public void returnCorrectClassroomAverageScore(){
        double totalAverageScore = (student1.getAverageExamScore() +
                student2.getAverageExamScore() +
                student3.getAverageExamScore()) / classroom.getStudents().length;
        double expected = (double) Math.round(totalAverageScore * 100) / 100;
        assertEquals(expected, classroom.getClassAverageExamScore());
    }

    @Test
    @DisplayName("calculateCorrectClassroomAverageScoreWithNullStudent")
    public void returnCorrectClassroomAverageScoreWithNullStudent(){
        classroom.removeStudent("May", "Flower");
        int remainingActiveStudents = classroom.getStudents().length - 1;
        double totalAverageScore = (student1.getAverageExamScore() +
                student3.getAverageExamScore()) / remainingActiveStudents;
        double expected = (double) Math.round(totalAverageScore * 100) / 100;
        assertEquals(expected, classroom.getClassAverageExamScore());
    }

    @Test
    @DisplayName("allowAdditionOfStudentToClassroom")
    public void addStudentToClassroomCorrectly(){
        classroom.removeStudent(student2.getFirstName(), student2.getLastName());
        classroom.addStudent(student4);
        int newStudentPosition = classroom.getStudents().length - 1;
        assertSame(student4, classroom.getStudents()[newStudentPosition]);
    }

    @Test
    @DisplayName("denyAdditionOfStudentToClassroomIfClassroomFull")
    public void denyAdditionOfStudentToClassroom(){
        classroom.addStudent(student4);
        int newStudentPosition = classroom.getStudents().length - 1;
        assertEquals(studentsInClassroom.length, classroom.getStudents().length);
        assertNotSame(student4, classroom.getStudents()[newStudentPosition]);
    }


    @Test
    @DisplayName("allowRemovalOfStudentFromClassroom")
    public void removeStudentFromClassroom(){
        classroom.removeStudent(student2.getFirstName(), student2.getLastName());
        Student[] remainingStudents = {student1, student3, null};
        int removedStudentPosition = classroom.getStudents().length - 1;
        assertNull(classroom.getStudents()[removedStudentPosition]);
        assertArrayEquals(remainingStudents, classroom.getStudents());
    }

    @Test
    @DisplayName("returnListOfStudentsSortedFromHighestToLowestScore")
    public void returnDescendingOrderOfStudentsByScore(){
        Student[] expectedOrderOfStudents = {student2, student1, student3};
        assertArrayEquals(expectedOrderOfStudents, classroom.getStudentsByScore());
    }

    @Test
    @DisplayName("returnListOfStudentsSortedFromHighestToLowestScoreWhenNullStudentPresent")
    public void returnDescendingOrderOfStudentsByScoreWhenNullStudentPresent(){
        classroom.removeStudent(student1.getFirstName(), student1.getLastName());
        Student[] expectedOrderOfStudents = {student2, student3, null};
        int positionOfNullStudent = classroom.getStudents().length - 1;
        assertArrayEquals(expectedOrderOfStudents, classroom.getStudentsByScore());
        assertNull(classroom.getStudentsByScore()[positionOfNullStudent]);
    }

    @Test
    @DisplayName("returnListOfStudentsSortedFromHighestToLowestScoreWhenSimilarStudentAveragesPresent")
    public void returnDescendingOrderOfStudentsByScoreWhenSimilarStudentAveragesPresent(){
        Classroom room3 = new Classroom(new Student[]{student1, student2, student12});
        Student[] expectedOrderOfStudents = {student12, student2, student1};
        assertArrayEquals(expectedOrderOfStudents, room3.getStudentsByScore());
    }

    @Test
    @DisplayName("returnCorrectlyCalculatedGradeBook")
    public void returnCorrectGradeBook(){

        Student[] allStudents = {student1, student2, student3, student11, student12};
        Classroom funClass = new Classroom(allStudents);
        double classAverage = funClass.getClassAverageExamScore();
        double desiredClassAverage = 80.0;
        double adjustedClassAverage = (classAverage / desiredClassAverage) * 100;

        Map<Student, Character> funClassGradeBook = new HashMap<>();
        char grade;
        for(int i = 0; i < allStudents.length; i++){
            double adjustedStudentAverage = allStudents[i].getAverageExamScore()/ adjustedClassAverage * 100;
            if(adjustedStudentAverage >= 90.0){
                grade = 'A';
            } else if(adjustedStudentAverage >= 70.0){
                grade = 'B';
            }else if(adjustedStudentAverage >= 50.0){
                grade = 'C';
            }else if(adjustedStudentAverage >= 11.0){
                grade = 'D';
            }else{
                grade = 'F';
            }
            funClassGradeBook.put(allStudents[i], grade);
        }
        //assertTrue(funClassGradeBook.equals(funClass.getGradeBook()));
        assertEquals(funClassGradeBook, funClass.getGradeBook());


    }

}