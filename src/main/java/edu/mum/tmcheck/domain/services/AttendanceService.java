package edu.mum.tmcheck.domain.services;

import edu.mum.tmcheck.domain.entities.Attendance;

public interface AttendanceService {
    public void create();

    public void update();

    public Attendance get();

    public void save();

    public void loadFromFile(String filename);
}
