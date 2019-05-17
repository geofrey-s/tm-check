package edu.mum.tmcheck.domain.reports;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "EntryAttendanceReport")
@Immutable
@Subselect(value = "SELECT " +
        "u.student_reg_id as student_id, " +
        "u.name, " +
        "e.name AS entry," +
        "e.start_date AS entry_start," +
        "e.end_date AS entry_end," +
        "SUM(CASE WHEN LOWER(mt.name) = 'standard' AND THEN 1 ELSE 0 END) AS standard_tm," +
        "SUM(CASE WHEN LOWER(mt.name) = 'retreat' THEN 1 ELSE 0 END) AS retreats," +
        "SUM(CASE WHEN LOWER(mt.name) = 'check' THEN 1 ELSE 0 END) AS checks " +
        "FROM attendance AS a " +
        "LEFT JOIN user AS u ON u.id = a.student_id " +
        "LEFT JOIN entry AS e ON e.id = u.entry_id " +
        "LEFT JOIN meditation_type AS mt ON mt.id = a.meditation_type_id " +
        "GROUP BY u.student_reg_id, u.name, e.name, e.start_date, e.end_date")
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

    @Formula(value = "ROUND(standard_tm + (0.2 * retreats * DATEDIFF('DAY', entry_start, entry_end))/DATEDIFF('DAY', entry_start, entry_end), 1)*100")
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

    public double getOverrallAttendance() {
        return overrallAttendance;
    }

    public void setOverrallAttendance(double overrallAttendance) {
        this.overrallAttendance = overrallAttendance;
    }

    public void setName(String name) {
        this.name = name;
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
}
