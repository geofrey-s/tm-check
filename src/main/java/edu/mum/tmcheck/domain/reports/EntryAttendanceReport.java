package edu.mum.tmcheck.domain.reports;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "EntryAttendanceReport")
@Subselect(value = "SELECT " +
        "u.student_reg_id as student_id, " +
        "u.name, " +
        "e.name AS entry," +
        "e.start_date AS entryStart," +
        "e.end_date AS entryEnd," +
        "SUM(CASE WHEN LOWER(mt.name) = 'standard' THEN 1 ELSE 0 END) AS standardTm," +
        "SUM(CASE WHEN LOWER(mt.name) = 'retreat' THEN 1 ELSE 0 END) AS retreats," +
        "SUM(CASE WHEN LOWER(mt.name) = 'check' THEN 1 ELSE 0 END) AS checks" +
        "FROM attendance AS a " +
        "LEFT JOIN user AS u ON u.id = a.student_id" +
        "LEFT JOIN entry AS e ON e.id = u.entry_id" +
        "LEFT JOIN meditation_type AS mt ON mt.id = a.meditation_type_id" +
        "LEFT JOIN tmtype AS tt ON tt.id = a.tm_type_id" +
        "GROUP BY u.student_reg_id, u.name, e.name, e.start_date, e.end_date")
@Synchronize({"attendance", "user", "entry", "meditation_type"})
public class EntryAttendanceReport implements Serializable {
    @Id
    String studentId;
    String name;
    String entry;
    LocalDate entryStart;
    LocalDate entryEnd;
    int standardTm;
    int retreats;
    int checks;

    @Formula(value = "ROUND((standardTm + (0.2 * retreats * DATEDIFF('DAY', entryStart, entryEnd))/DATEDIFF('DAY', entryStart, entryEnd), 1)*100")
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
        return entryStart;
    }

    public void setEntryStart(LocalDate entryStart) {
        this.entryStart = entryStart;
    }

    public LocalDate getEntryEnd() {
        return entryEnd;
    }

    public void setEntryEnd(LocalDate entryEnd) {
        this.entryEnd = entryEnd;
    }

    public int getStandardTm() {
        return standardTm;
    }

    public void setStandardTm(int standardTm) {
        this.standardTm = standardTm;
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
}
