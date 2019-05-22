package edu.mum.tmcheck.domain.reports;


import edu.mum.tmcheck.utils.Dates;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity(name = "ECAttendanceReport")
@Subselect("SELECT " +
        "       concat(s.id,a.block_id) as id , " +
        "       s.student_reg_id, " +
        "       s.name, " +
        "       a.faculty_id, " +
        "       a.block_id, " +
        "       a.block_start, " +
        "       a.block_end, " +
        "       a.standard_tm " +
        " from STUDENT as s " +
        "         left join ( " +
        "    select ai.STUDENT_ID," +
        "           oc.faculty_id, " +
        "           b.id                                                         as block_id, " +
        "           b.START_DATE                                                 AS block_start, " +
        "           b.END_DATE                                                   AS block_end, " +
        "           SUM(CASE WHEN LOWER(mt.name) = 'standard' THEN 1 ELSE 0 END) AS standard_tm " +
        "    from ATTENDANCE ai " +
        "             LEFT JOIN meditation_type AS mt ON mt.id = ai.meditation_type_id " +
        "             LEFT JOIN STUDENT_ENROLLED_COURSES as sc on sc.STUDENTS_ID = ai.STUDENT_ID " +
        "             LEFT JOIN OFFERED_COURSE as oc on oc.ID = sc.ENROLLED_COURSES_ID " +
        "             LEFT JOIN BLOCK as b on b.ID = oc.BLOCK_ID " +
        "    group by ai.STUDENT_ID, oc.faculty_id,b.id, b.START_DATE, b.END_DATE " +
        ") as a on s.id = a.student_id " +
        " where a.block_id is not null")
@Synchronize({"attendance", "student", "block", "OFFERED_COURSE", "STUDENT_ENROLLED_COURSES", "meditation_type"})
public class ECAttendanceReport {
    @Id
    String id;
    String studentRegId;
    String name;
    long facultyId;
    long blockId;
    LocalDate blockStart;
    LocalDate blockEnd;
    int standardTm;

    @Transient
    private double overrallAttendance = 0;

    @Transient
    private double extraCredit = 0;

    @Transient
    private int daysAttended = 0;

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public int getDaysAttended() {
        return (int) Dates.countWeekDays(blockStart, blockEnd);
    }

    public void setDaysAttended(int daysAttended) {
        this.daysAttended = daysAttended;
    }

    public double getExtraCredit() {
        double attendance = getOverrallAttendance();
        double ec = 0;

        if (attendance >= 70 && attendance<80)
            ec = 0.5;
        else if (attendance >= 80 && attendance<90)
            ec = 1.0;
        else if (attendance >= 90)
            ec = 1.5;

        return ec;
    }

    public void setExtraCredit(double extraCredit) {
        this.extraCredit = extraCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentRegId() {
        return studentRegId;
    }

    public void setStudentRegId(String studentRegId) {
        this.studentRegId = studentRegId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBlockId() {
        return blockId;
    }

    public void setBlockId(long blockId) {
        this.blockId = blockId;
    }

    public LocalDate getBlockStart() {
        return blockStart;
    }

    public void setBlockStart(LocalDate blockStart) {
        this.blockStart = blockStart;
    }

    public LocalDate getBlockEnd() {
        return blockEnd;
    }

    public void setBlockEnd(LocalDate blockEnd) {
        this.blockEnd = blockEnd;
    }

    public int getStandardTm() {
        return standardTm;
    }

    public void setStandardTm(int standardTm) {
        this.standardTm = standardTm;
    }

    public double getOverrallAttendance() {
        long days = Dates.countWeekDays(blockStart, blockEnd);

        if (days == 0) return 0;

        double _overrallAttendance = (Double.valueOf(standardTm) / Double.valueOf(days)) * 100;

        return Math.round(_overrallAttendance);
    }
}
