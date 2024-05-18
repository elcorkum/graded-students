package student_grades;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student thisStudent, Student thatStudent) {
        if(thisStudent.getFirstName().equalsIgnoreCase(thatStudent.getFirstName())){
            return thisStudent.getLastName().compareToIgnoreCase(thatStudent.getLastName());
        }else
            return thisStudent.getFirstName().compareToIgnoreCase(thatStudent.getFirstName());
    }
}
