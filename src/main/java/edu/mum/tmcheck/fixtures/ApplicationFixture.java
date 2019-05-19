package edu.mum.tmcheck.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationFixture {
    final int DEFAULT_NUMBER_OF_RECORDS = 10;

    @Autowired
    CardFixture cardFixture;

    @Autowired
    EntryFixture entryFixture;

    @Autowired
    CourseFixture courseFixture;

    @Autowired
    BlockFixture blockFixture;

    @Autowired
    FacultyFixture facultyFixture;

    @Autowired
    OfferedCourseFixture offeredCourseFixture;

    @Autowired
    LocationFixture locationFixture;

    @Autowired
    MeditationTypeFixture meditationTypeFixture;

    @Autowired
    StudentFixture studentFixture;

    @Autowired
    AdminFixture adminFixture;

    @Autowired
    AttendanceFixture attendanceFixture;

    @PostConstruct
    public void initialize() {
        entryFixture.generate(2);
        courseFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        cardFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        blockFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        facultyFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        offeredCourseFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        locationFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        meditationTypeFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        studentFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        adminFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        attendanceFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
    }
}
