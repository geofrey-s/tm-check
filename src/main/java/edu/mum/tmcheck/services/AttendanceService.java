package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Attendance;

public interface AttendanceService {
    public void create();

    public void update();

    public Attendance get();

    public void save(Attendance studentrecord);

    public boolean loadFromFile(String filename);

    public void deletebyid(Long RecordId);

    public Attendance findTMCheckRecord(Long StudentId, Long MeditationTypeID);
}
