package student_grades;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<Double> examScores;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public ArrayList<Double> getExamScores() {
        return examScores;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Student(String firstName, String lastName, Double[] testScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<>(Arrays.asList(testScores));
    }
    public int getNumberOfExamsTaken() {
        return examScores.size();
    }
    public String getListOfExamScores() {
        StringBuilder allExamScores = new StringBuilder();
        allExamScores.append("Exam Scores:\n\t\t");
        int count = 1;
        for(Double examScore: this.examScores){
            allExamScores.append(String.format("Exam %s %c%c %s\n\t\t", count, '-', '>', examScore));
            count++;
        }
        return allExamScores.toString();
    }


    public void addExamScore(double examScore) {
        this.examScores.add(examScore);
    }

    public void setExamScore(int examNumber, double newScore){
        if(examNumber - 1 >= 0){
            this.examScores.set(examNumber - 1, newScore);
        }
    }

    public double getAverageExamScore(){
        double result = 0.0;
        for(Double examScore: this.examScores){
            result += examScore;
        }
        return result/this.examScores.size();
    }

    @Override
    public String toString() {
        return "Student Name: " + firstName + " " + lastName + "\n" +
                "> Average Score: " + getAverageExamScore() + "\n" +
                "> " + getListOfExamScores();
    }
}
