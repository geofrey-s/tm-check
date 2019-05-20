package edu.mum.tmcheck.domain.reports;


import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.utils.Dates;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity(name = "BlockAttendanceReport")
@Subselect("SELECT s.student_reg_id                                                 as student_id,\n" +
        "       s.name," +
        "       b.id as blockId," +
        "       b.START_DATE AS block_start, " +
        "       b.END_DATE AS block_end, " +
        "       SUM(CASE WHEN LOWER(mt.name) = 'standard' THEN 1 ELSE 0 END) AS standard_tm, " +
        "       SUM(CASE WHEN LOWER(mt.name) = 'retreat' THEN 1 ELSE 0 END)      AS retreats, " +
        "       SUM(CASE WHEN LOWER(mt.name) = 'check' THEN 1 ELSE 0 END)        AS checks " +
        "FROM attendance AS a " +
        "         LEFT JOIN student AS s ON s.id = a.student_id " +
        "         LEFT JOIN STUDENT_ENROLLED_COURSES as sc on sc.STUDENTS_ID = s.ID " +
        "         LEFT JOIN  OFFERED_COURSE as oc on oc.ID = sc.ENROLLED_COURSES_ID " +
        "         LEFT JOIN  BLOCK as b on b.ID = oc.BLOCK_ID " +
        "         LEFT JOIN meditation_type AS mt ON mt.id = a.meditation_type_id " +
        "WHERE a.CREATED_AT between b.START_DATE and b.END_DATE " +
        "GROUP BY s.student_reg_id, s.name, b.START_DATE, b.END_DATE")
@Synchronize({"attendance", "student", "block", "OFFERED_COURSE", "STUDENT_ENROLLED_COURSES", "meditation_type"})
public class BlockAttendanceReport {
    @Id
    String studentId;
    String name;
    Long blockId;
    LocalDate block_start;
    LocalDate block_end;
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

    public LocalDate getBlock_start() {
        return block_start;
    }

    public void setBlock_start(LocalDate block_start) {
        this.block_start = block_start;
    }

    public LocalDate getBlock_end() {
        return block_end;
    }

    public void setBlock_end(LocalDate block_end) {
        this.block_end = block_end;
    }

    public int getStandard_tm() {
        return standard_tm;
    }

    public void setStandard_tm(int standard_tm) {
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

    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    public void setChecks(int checks) {
        this.checks = checks;
    }

    public double getOverrallAttendance() {
        long days = Dates.countWeekDays(block_start, block_end);

        if (days == 0) return 0;

        double _retreats = convertRetreats(retreats, days);

        double _overrallAttendance = ((standard_tm + _retreats) / days) * 100;
        return Math.round(_overrallAttendance);
    }

    public double convertRetreats(int count, long days) {
        return Attendance.RETREAT_TO_STANDARD_TM_RATE * count * days;
    }

    @Override
    public String toString() {
        return "BlockAttendanceReport{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", block_start=" + block_start +
                ", block_end=" + block_end +
                ", standard_tm=" + standard_tm +
                ", retreats=" + retreats +
                ", checks=" + checks +
                ", overrallAttendance=" + overrallAttendance +
                '}';
    }
}
