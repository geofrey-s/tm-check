package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.models.MeditationAttendanceEditor;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;

import java.util.List;

public interface AttendanceService {
    public void create();

    public void update();

    public Attendance get();

    public Attendance save(Attendance attendance);

    public boolean loadFromFile(String filename);

    public void deletebyid(Long RecordId);

    public List<Attendance> findTMCheckRecord(String StudentId, Long MeditationTypeID);

    public Attendance createFromEditor(MeditationAttendanceEditor editor);

    public BlockEndEachStudentMeditationData computeBlockEC(String studentId, Long blockId);
}
