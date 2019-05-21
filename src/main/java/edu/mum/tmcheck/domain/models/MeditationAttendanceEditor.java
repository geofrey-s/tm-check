package edu.mum.tmcheck.domain.models;

public class MeditationAttendanceEditor {
    private String studentRegId;
    private long meditationTypeId;
    private long locationId;
    private String createdAt;


    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getStudentRegId() {
        return studentRegId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setStudentRegId(String studentRegId) {
        this.studentRegId = studentRegId;
    }

    public long getMeditationTypeId() {
        return meditationTypeId;
    }

    public void setMeditationTypeId(long meditationTypeId) {
        this.meditationTypeId = meditationTypeId;
    }
}
