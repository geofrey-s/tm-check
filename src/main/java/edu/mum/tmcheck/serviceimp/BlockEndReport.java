package edu.mum.tmcheck.serviceimp;

import java.util.ArrayList;
import java.util.List;

public class BlockEndReport {
    private List<BlockEndEachStudentMeditationData> studentsdata;

    public BlockEndReport() {
        this.studentsdata = new ArrayList<>();
    }

    public List<BlockEndEachStudentMeditationData> getStudentsdata() {
        return studentsdata;
    }

    public void setStudentsdata(List<BlockEndEachStudentMeditationData> studentsdata) {
        this.studentsdata = studentsdata;
    }
}
