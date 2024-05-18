package student_grades;

import java.util.Comparator;

public class AverageComparator implements Comparator<Student> {
    @Override
    public int compare(Student thisStudent, Student thatStudent) {
        if(thisStudent.getAverageExamScore() > thatStudent.getAverageExamScore()){
            return 1;
        } else if(thisStudent.getAverageExamScore() < thatStudent.getAverageExamScore()){
            return -1;
        }else
            return 0;
    }
}
