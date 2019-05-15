package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Attendance;

public interface AttendanceService {
    public void create();

    public void update();

    public Attendance get();

    public void save();

    public boolean loadFromFile(String filename);
}
