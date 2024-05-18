package student_grades;

import java.util.*;

public class Classroom {
    private Student[] students;

    public Student[] getStudents() {
        return students;
    }

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

//    public Student[] getStudentsByScore() {
//        Comparator<Student> comp = Comparator.comparing(Student::getAverageExamScore, Comparator.reverseOrder())
//                .thenComparing(Student::getFirstName)
//                .thenComparing(Student::getLastName);
//
//        Comparator<Student> comparingWithNull = Comparator.nullsLast(comp);
//        Arrays.sort(students, comparingWithNull);
//        return students;
//    }
    //FUNCTIONAL PROGRAMMING --easier but expensive

    public Student[] getStudentsByScore(){
        Comparator<Student> scoreComparator = new AverageComparator();
        Comparator<Student> nameComparator = new NameComparator();
        Arrays.sort(students, scoreComparator.reversed().thenComparing(nameComparator));
        return students;
    }

    public Map<Student, Character> getGradeBook() {
        Map<Student, Character> gradeBook = new HashMap<>();
        char grade;
        try {
            for (int i = 0; i < students.length; i++) {
                double desiredClassAveragePercentage = (getClassAverageExamScore() * 100) / 80;
                double adjustedStudentAverage = (students[i].getAverageExamScore() / desiredClassAveragePercentage) * 100;
                if (adjustedStudentAverage >= 90.0){
                    grade = 'A';
                }else if (adjustedStudentAverage >= 70.0) {
                    grade = 'B';
                }else if (adjustedStudentAverage >= 50.0) {
                    grade = 'C';
                }else if (adjustedStudentAverage >= 11.0) {
                    grade = 'D';
                }else{
                    grade = 'F';
                }
                gradeBook.put(students[i], grade);
            }
        }catch(NullPointerException npe) {
            System.out.println("Student spot is null");
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
