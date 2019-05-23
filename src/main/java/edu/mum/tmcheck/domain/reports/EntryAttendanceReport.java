package edu.mum.tmcheck.domain.reports;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.utils.Dates;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "EntryAttendanceReport")
@Immutable
@Subselect(value = "SELECT u.student_reg_id                                              as student_id,\n" +
        "       u.name,\n" +
        "       a.name                                                        AS entry,\n" +
        "       a.entry_start,\n" +
        "       a.entry_end,\n" +
        "       case when a.standard_tm is null then 0 else a.standard_tm end as standard_tm,\n" +
        "       case when a.retreats is null then 0 else a.retreats end       as retreats,\n" +
        "       case when a.checks is null then 0 else a.checks end           as checks\n" +
        "FROM STUDENT as u\n" +
        "         left JOIN (\n" +
        "    select ai.STUDENT_ID,\n" +
        "           e.NAME,\n" +
        "           e.START_DATE                                                 as entry_start,\n" +
        "           LEAST(s.departure_date, CURRENT_DATE)                        as entry_end,\n" +
        "           SUM(CASE WHEN LOWER(mt.name) = 'standard' THEN 1 ELSE 0 END) AS standard_tm,\n" +
        "           SUM(CASE WHEN LOWER(mt.name) = 'retreat' THEN 1 ELSE 0 END)  AS retreats,\n" +
        "           SUM(CASE WHEN LOWER(mt.name) = 'check' THEN 1 ELSE 0 END)    AS checks\n" +
        "    from ATTENDANCE ai\n" +
        "             LEFT JOIN meditation_type AS mt ON mt.id = ai.meditation_type_id\n" +
        "             LEFT JOIN STUDENT s on s.id = ai.STUDENT_ID\n" +
        "             LEFT JOIN entry AS e ON e.id = s.entry_id\n" +
        "    where ai.CREATED_AT between e.START_DATE and LEAST(s.departure_date, CURRENT_DATE)\n" +
        "    group by STUDENT_ID, e.NAME,\n" +
        "             e.START_DATE,\n" +
        "             LEAST(s.departure_date, CURRENT_DATE)\n" +
        ") a ON u.id = a.student_id")
@Synchronize({"attendance", "user", "entry", "meditation_type"})
public class EntryAttendanceReport implements Serializable {
    @Id
    String studentId;
    String name;
    String entry;
    LocalDate entry_start;
    LocalDate entry_end;
    int standard_tm;
    int retreats;
    int checks;

    @Transient
    double overrallAttendance = 0;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOverrallAttendance() {
        long days = Dates.countWeekDays(entry_start, entry_end) - 2;

        if (days == 0) return 0;

        double _retreats = convertRetreats(retreats, days);

        double _overrallAttendance = (Double.valueOf(standard_tm + _retreats) / Double.valueOf(days)) * 100;
        return Math.round(_overrallAttendance);
    }

    public double convertRetreats(int count, long days) {
        return Attendance.RETREAT_TO_STANDARD_TM_RATE * count * days;
    }

    public void setOverrallAttendance(double overrallAttendance) {
        this.overrallAttendance = overrallAttendance;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public LocalDate getEntryStart() {
        return entry_start;
    }

    public void setEntryStart(LocalDate entry_start) {
        this.entry_start = entry_start;
    }

    public LocalDate getEntryEnd() {
        return entry_end;
    }

    public void setEntryEnd(LocalDate entry_end) {
        this.entry_end = entry_end;
    }

    public int getStandardTm() {
        return standard_tm;
    }

    public void setStandardTm(int standard_tm) {
        this.standard_tm = standard_tm;
    }

    public int getRetreats() {
        return retreats;
    }

    public void setRetreats(int retreats) {
        this.retreats = retreats;
    }

    public int getChecks() {
        return checks;
    }

    public void setChecks(int checks) {
        this.checks = checks;
    }

    @Override
    public String toString() {
        return "EntryAttendanceReport{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", entry='" + entry + '\'' +
                ", entry_start=" + entry_start +
                ", entry_end=" + entry_end +
                ", standard_tm=" + standard_tm +
                ", retreats=" + retreats +
                ", checks=" + checks +
                ", overrallAttendance=" + overrallAttendance +
                '}';
    }

    public LocalDate getEntry_start() {
        return entry_start;
    }

    public void setEntry_start(LocalDate entry_start) {
        this.entry_start = entry_start;
    }
}
