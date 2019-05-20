package edu.mum.tmcheck.serviceimp;


import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.repository.AttendanceRepository;
import edu.mum.tmcheck.domain.repository.BlockRepository;
import edu.mum.tmcheck.domain.repository.FacultyRepository;
import edu.mum.tmcheck.domain.repository.OfferedCourseRepository;
import edu.mum.tmcheck.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

@Service
public class AttendanceServiceImp implements AttendanceService {
    public static final String ATTENDANCE_UPLOAD_DIR = "/attendance-logs";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    @Autowired
    IdCardServiceImp idCardServiceImp;
    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;
    @Autowired
    LocationServiceImp locationServiceImp;
    @Autowired
    StudentServiceImp studentServiceImp;
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    BlockRepository blockRepository;
    @Autowired
    OfferedCourseRepository offeredCourseRepository;

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public Attendance get() {
        return null;
    }

    @Override
    public Attendance save(Attendance attendance) {
        try {
            attendance = attendanceRepository.save(attendance);
        } catch (DataIntegrityViolationException e) {
        }

        return attendance;
    }

    @Override
    public void deletebyid(Long RecordId) {
        attendanceRepository.deleteById(RecordId);
    }

    @Override
    public List<Attendance> findTMCheckRecord(String StudentId, Long MeditationTypeID) {
        System.out.println(StudentId + " " + StudentId.getClass().getSimpleName() + " -------------------------------------" + MeditationTypeID + " " + MeditationTypeID.getClass().getSimpleName());
        List<Attendance> attendanceList = (List<Attendance>) attendanceRepository.findAll();
        System.out.println(attendanceList.get(0).getStudent().getStudentRegId());
        attendanceList = attendanceList.stream().filter(record -> record.getMeditationType().getId() == MeditationTypeID).collect(Collectors.toList());
        List<Attendance> finalattendcelist = new ArrayList<>();
        attendanceList.forEach(a -> {
            if(a.getStudent().getStudentRegId().compareToIgnoreCase(StudentId) == 0)
                finalattendcelist.add(a);
        } );
        return  finalattendcelist;
    }

    @Override
    /**
     * Reads all students TM attendance records from file and loads the into the database
     * @param filename log filename
     */
    public boolean loadFromFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.parallel()
                    .filter(Objects::nonNull)
                    .map(line -> line.split(","))
                    .map(aLine -> isManualEntry(aLine)
                            ? processManualAttendanceRecord(aLine)
                            : processScannedAttendanceRecord(aLine))
                    .forEach(this::insertOnlyUnique);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * process students' manually collected TM attendance sessions converting them to standard Attendance objects
     *
     * @param line
     * @return
     */
    protected Attendance processManualAttendanceRecord(String[] line) {
        Attendance record = new Attendance();

        record.setCreatedAt(parseDate(line[0], DATE_FORMAT));
        record.setStudent(studentServiceImp.findByStudentRegId(line[1]));
        record.setMeditationType(meditationTypeServiceImp.findByName(Attendance.DEFAULT_MEDITATION_TYPE));
        record.setLocation(locationServiceImp.findByCode(Attendance.DEFAULT_LOCATION_CODE));

        return record;
    }

    /**
     * process students' scanned TM attendance sessions converting them to standard Attendance objects
     *
     * @param line
     * @return
     */
    protected Attendance processScannedAttendanceRecord(String[] line) {
        Attendance record = new Attendance();

        record.setStudent(studentServiceImp.findByBarcode(line[0]));
        record.setCreatedAt(parseDate(line[1], DATE_FORMAT));

        String meditationName = MeditationTypeServiceImp.fromShortCode(line[2]);
        record.setMeditationType(meditationTypeServiceImp.findByName(meditationName));
        record.setLocation(locationServiceImp.findByCode(line[3]));

        return record;
    }

    /**
     * Determines if a give attendance log record was manually collected or scanned by the student
     *
     * @param line
     * @return
     */
    protected boolean isManualEntry(String[] line) {
        if (line.length == 0 || line[0].length() == 0) return false;

        // if the first entry is a date format
        return line[0].split("/").length > 1;
    }

    /**
     * Parses the string to a LocalDate instate using a predefined date format
     *
     * @param input
     * @param format
     * @return
     */
    public LocalDate parseDate(String input, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return LocalDate.parse(input, formatter);
    }

    /**
     * Inserts new attendance records in the database ignoring duplicates
     *
     * @param attendance
     */
    protected void insertOnlyUnique(Attendance attendance) {
        try {
            attendanceRepository.save(attendance);
        } catch (Exception e) {
        }
    }

    /**
     * Processes i.e persists attendance log file upload on the server and loads/adds all records to the database
     *
     * @param file
     * @return
     * @throws IOException
     */
    public boolean processFileUpload(MultipartFile file) throws IOException {
        String filename = Paths.get(ATTENDANCE_UPLOAD_DIR, UUID.randomUUID().toString() + ".log").toString();
        file.transferTo(new File(filename));

        return loadFromFile(filename);
    }

    public List<BlockEndEachStudentMeditationData> ComputeBlockEC(Long id, Long blockid) {
        // Get Current Block using the current date Using Filter
        Block currentblock = blockRepository.findById(blockid).get();

        // Get Current CourseOfferings in current block
        List<OfferedCourse> courses = offeredCourseRepository.findAll();


        // Filter the current course offerings using Faculty ID to get the current course of the professor
        Optional<OfferedCourse> currentcourse = courses.stream()
                .filter(x -> x.getFaculty().getId() == id && x.getBlock().getId() == currentblock.getId())
                .findFirst();

        if (!currentcourse.isPresent())
            return new ArrayList<BlockEndEachStudentMeditationData>();

        // Get Students of that specific course
        List<Student> students = currentcourse.get().getStudents();

        List<BlockEndEachStudentMeditationData> StudentsData = new ArrayList<>();

        // Calculate the available session from the block duration
        long noofdays = Duration.between(currentblock.getStartDate().atStartOfDay(), currentblock.getEndDate().atStartOfDay()).toDays();
        long availablesessions = (noofdays > 14) ? 11 : 12;

        // Calculate and Create Extra Credit Data for each student in that specific course and add it to to the report list
        students.forEach(s -> {
            List<Attendance> attendanceofstudent = (List<Attendance>) attendanceRepository.findByStudent(s);
            Long days_attended = attendanceofstudent.stream()
                    .filter(att -> att.getCreatedAt().isBefore(currentblock.getEndDate()) || att.getCreatedAt().isAfter(currentblock.getStartDate()) || att.getCreatedAt().isEqual(currentblock.getStartDate()) || att.getCreatedAt().isEqual(currentblock.getEndDate()))
                    .filter(att -> !att.getMeditationType().getName().equals("check") || att.getMeditationType().getName().equals("retreat"))
                    .count();
            double percentage = (days_attended * 100 / availablesessions);

            double ExtraCredit;
            if (percentage >= 70)
                ExtraCredit = 0.5;
            else if (percentage >= 80)
                ExtraCredit = 1.0;
            else if (percentage >= 90)
                ExtraCredit = 1.5;
            else
                ExtraCredit = 0.0;

            BlockEndEachStudentMeditationData studentdata = new BlockEndEachStudentMeditationData(s, toIntExact(days_attended), toIntExact(availablesessions), (float) percentage, (float) ExtraCredit);
            StudentsData.add(studentdata);
        });

        return StudentsData;
    }
}
