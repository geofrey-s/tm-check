package edu.mum.tmcheck.domain.models;

import edu.mum.tmcheck.domain.entities.Attendance;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MeditationAttendanceEditor {
    private long recordId;

    @NotEmpty(message = "{notEmpty}")
    @Pattern(regexp = "(0)(00)([-+]\\d+)([-+]\\d+)", message = "{regID}")
    private String studentRegId;
    private long meditationTypeId;
    private long locationId;
    private String createdAt;

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

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

    public static MeditationAttendanceEditor fromRecord(Attendance record){
        MeditationAttendanceEditor editor = new MeditationAttendanceEditor();

        if (record == null) return editor;

        editor.recordId = record.getId();
        editor.studentRegId = record.getStudent().getStudentRegId();
        editor.meditationTypeId = record.getMeditationType().getId();
        editor.locationId = record.getLocation().getId();
        editor.createdAt = record.getCreatedAt().toString();

        return editor;
    }
}
