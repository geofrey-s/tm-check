package edu.mum.tmcheck.serviceimp;


import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.repository.AttendanceRepository;
import edu.mum.tmcheck.domain.repository.BlockRepository;
import edu.mum.tmcheck.domain.repository.FacultyRepository;
import edu.mum.tmcheck.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

@Service
public class AttendanceServiceImp implements AttendanceService {
    public static final String ATTENDANCE_UPLOAD_DIR = "/attendance-logs";

    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;

    @Autowired
    TMtypeServiceImp tMtypeServiceImp;

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

    public static final String DATE_FORMAT = "dd/MM/yyyy";

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
        return attendanceRepository.save(attendance);
    }

    @Override
    public void deletebyid(Long RecordId){
        attendanceRepository.deleteById(RecordId);
    }

    @Override
    public Attendance findTMCheckRecord(Long StudentId, Long MeditationTypeID){
        List<Attendance> attendanceList  = (List<Attendance>) attendanceRepository.findAll();
        return attendanceList.stream().filter(record -> record.getStudent().getId() == StudentId && record.getMeditationType().getId() == MeditationTypeID).collect(Collectors.toList()).get(0);
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
        record.setTmType(tMtypeServiceImp.findByName(Attendance.DEFAULT_TM_TYPE));

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
        record.setMeditationType(meditationTypeServiceImp.findByName(line[2]));
        record.setLocation(locationServiceImp.findByCode(line[3]));
        record.setTmType(tMtypeServiceImp.findByName(Attendance.DEFAULT_TM_TYPE));

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


    public List<BlockEndEachStudentMeditationData> ComputeBlockEC(Long id)
    {
        /*
        * Get Current Block using the current date Using Filter
        *
        * */
        Block currentblock = ((List<Block>) (blockRepository.findAll())).stream()
                        .filter(x -> x.getStartDate().isBefore(LocalDate.now()) && x.getEndDate().isAfter(LocalDate.now()))
                        .collect(Collectors.toList())
                        .get(0);

        /*
         * Get Current CourseOfferings in current block
         *
         * */
        List<OfferedCourse> courses = currentblock.getOfferedCourses();


        /*
         * Filter the current course offerings using Faculty ID to get the current course of the professor
         *
         * */
        OfferedCourse currentcourse = courses.stream()
                                                .filter(x -> x.getFaculty().getId() == id)
                                                .collect(Collectors.toList()).get(0);


        /*
         * Get Students of that specific course
         *
         * */
        List<Student> students = currentcourse.getStudents();

        List<BlockEndEachStudentMeditationData> StudentsData =  new ArrayList<>();

        /*
         * Calculate the available session from the block duration
         *
         * */
        long noofdays = Duration.between(currentblock.getStartDate(), currentblock.getEndDate()).toDays();
        long availablesessions;
        if(noofdays > 14){
            availablesessions = 11;
        }
        else {
            availablesessions = 22;
        }

        /*
         * Calculate and Create Extra Credit Data for each student in that specific course and add it to to the report list
         *
         * */
        students.forEach(s ->{
            List<Attendance> attendanceofstudent = (List<Attendance>) attendanceRepository.findByStudent(s);
            Long days_attended = attendanceofstudent.stream()
                                                        .filter(att -> att.getCreatedAt().isBefore(currentblock.getEndDate()) || att.getCreatedAt().isAfter(currentblock.getStartDate()) || att.getCreatedAt().isEqual(currentblock.getStartDate()) || att.getCreatedAt().isEqual(currentblock.getEndDate()))
                                                        .count();
            Long percentage = (days_attended/availablesessions) * 100;
            double ExtraCredit;
            if(percentage >= 70)
                ExtraCredit = 0.5;
            else if(percentage >= 80)
                ExtraCredit = 1.0;
            else if(percentage >= 90)
                ExtraCredit = 1.5;
            else
                ExtraCredit = 0.0;

            BlockEndEachStudentMeditationData studentdata = new BlockEndEachStudentMeditationData(s, toIntExact(days_attended), toIntExact(availablesessions), (float)percentage, (float)ExtraCredit);
            StudentsData.add(studentdata);
        });


        /*
         * Return all the data from the List
         */
        return StudentsData;
    }
}
