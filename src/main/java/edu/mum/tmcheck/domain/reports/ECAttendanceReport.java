package edu.mum.tmcheck.domain.reports;


import edu.mum.tmcheck.utils.Dates;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDate;

@Entity(name = "ECAttendanceReport")
@Subselect("SELECT concat(s.id, a.block_id) as id,\n" +
        "       s.student_reg_id,\n" +
        "       s.name,\n" +
        "       a.faculty_id,\n" +
        "       a.block_id,\n" +
        "       a.block_start,\n" +
        "       a.block_end,\n" +
        "       a.standard_tm\n" +
        "from STUDENT as s\n" +
        "         left join (\n" +
        "    select ai.STUDENT_ID,\n" +
        "           oc.faculty_id,\n" +
        "           b.id                                                         as block_id,\n" +
        "           b.START_DATE                                                 AS block_start,\n" +
        "           b.END_DATE                                                   AS block_end,\n" +
        "           SUM(CASE WHEN LOWER(mt.name) = 'standard' THEN 1 ELSE 0 END) AS standard_tm\n" +
        "    from ATTENDANCE ai\n" +
        "             LEFT JOIN meditation_type AS mt ON mt.id = ai.meditation_type_id\n" +
        "             LEFT JOIN STUDENT_ENROLLED_COURSES as sc on sc.STUDENTS_ID = ai.STUDENT_ID\n" +
        "             LEFT JOIN OFFERED_COURSE as oc on oc.ID = sc.ENROLLED_COURSES_ID\n" +
        "             LEFT JOIN BLOCK as b on b.ID = oc.BLOCK_ID\n" +
        "            where ai.CREATED_AT between b.START_DATE and b.END_DATE\n" +
        "    group by b.id, ai.STUDENT_ID, oc.faculty_id,  b.START_DATE, b.END_DATE\n" +
        ") as a on s.id = a.student_id\n" +
        "where a.block_id is not null")
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
