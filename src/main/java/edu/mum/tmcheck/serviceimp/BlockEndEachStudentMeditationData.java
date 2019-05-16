package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Student;

public class BlockEndEachStudentMeditationData
{
    private Student student;
    private int noofdaysattended;
    private int totalnoofdays;
    private float percentageattended;
    private float totalmarks;

    public BlockEndEachStudentMeditationData(Student student, int noofdaysattended, int totalnoofdays, float percentageattended, float totalmarks) {
        this.student = student;
        this.noofdaysattended = noofdaysattended;
        this.totalnoofdays = totalnoofdays;
        this.percentageattended = percentageattended;
        this.totalmarks = totalmarks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getNoofdaysattended() {
        return noofdaysattended;
    }

    public void setNoofdaysattended(int noofdaysattended) {
        this.noofdaysattended = noofdaysattended;
    }

    public int getTotalnoofdays() {
        return totalnoofdays;
    }

    public void setTotalnoofdays(int totalnoofdays) {
        this.totalnoofdays = totalnoofdays;
    }

    public float getPercentageattended() {
        return percentageattended;
    }

    public void setPercentageattended(float percentageattended) {
        this.percentageattended = percentageattended;
    }

    public float getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(float totalmarks) {
        this.totalmarks = totalmarks;
    }
}
