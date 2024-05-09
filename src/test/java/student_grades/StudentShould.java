package student_grades;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StudentShould {
    Double[] testScores = {54.6, 38.9, 52.0};
    Student student = new Student("Sandra", "Bullock", testScores);

    @Test
    @DisplayName("createStudentWithCorrectParameters")

    public void studentConstructor(){
        Double[] actualTestScores = student.getExamScores().toArray(new Double[0]);
        assertEquals("Sandra", student.getFirstName());
        assertEquals("Bullock", student.getLastName());
        assertTrue(Arrays.equals(testScores, actualTestScores));

    }

    @Test
    @DisplayName("returnCorrectNumberOfExamsTakenByStudent")
    public void returnCorrectNumberOfExams(){
        assertEquals(testScores.length, student.getNumberOfExamsTaken());
        assertNotEquals(testScores.length -2, student.getNumberOfExamsTaken());
    }

    @Test
    @DisplayName("returnStringRepresentationOfAllExamsTakenByStudent")
    public void returnsStringWithAllExamsTaken(){
        String allExamsTaken = student.getListOfExamScores();
        String expected = "Exam Scores:\n" +
                "\t\tExam 1 -> 54.6\n" +
                "\t\tExam 2 -> 38.9\n" +
                "\t\tExam 3 -> 52.0\n\t\t";
        assertTrue(expected.equals(allExamsTaken));
    }

    @Test
    @DisplayName("addTestScoreToListOfStudentsTestScores")
    public void addTestScoreToListOfTestScoresGivenSingleTestScore(){
        student.addExamScore(46.7);
        assertEquals(46.7, student.getExamScores().get(student.getExamScores().size() -1));
    }

    @Test
    @DisplayName("reassignTheValueOfTestScoreAtSpecifiedPosition")
    public void setScoreToDifferentValueGivenPositionAnNewVale(){
        student.setExamScore(3, 35.0);
        assertEquals(35.0, student.getExamScores().get(student.getExamScores().size() -1));
    }

    @Test
    @DisplayName("returnCalculatedAverageScoreOfStudent")
    public void getCorrectAverageScoreOfStudent(){
        double expectedAverage = (54.6 + 38.9 + 52.0) / 3;
        assertEquals(expectedAverage, student.getAverageExamScore());
    }

    @Test
    @DisplayName("returnCorrectStringRepresentationOfStudent")
    public void returnStudentInfoInString(){
        String expectedStudentInfo = "Student Name: Sandra Bullock\n" +
                "> Average Score: 48.5\n" +
                "> Exam Scores:\n" +
                "\t\tExam 1 -> 54.6\n" +
                "\t\tExam 2 -> 38.9\n" +
                "\t\tExam 3 -> 52.0\n\t\t";
        assertEquals(expectedStudentInfo, student.toString());
    }

}