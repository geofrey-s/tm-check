package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.serviceimp.AttendanceServiceImp;
import edu.mum.tmcheck.serviceimp.LocationServiceImp;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class AttendanceFixture extends BaseFixture {
    @Autowired
    AttendanceServiceImp attendanceServiceImp;

    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;

    @Autowired
    LocationServiceImp locationServiceImp;

    List<Student> students = new ArrayList<>();
    List<MeditationType> meditationTypes = new ArrayList<>();
    List<Location> locations = new ArrayList<>();

    @Override
    public void generate(int size) {
        students = studentServiceImp.findAll();
        meditationTypes = meditationTypeServiceImp.findAll();
        locations = locationServiceImp.findAll();

        Date now = java.sql.Date.valueOf(LocalDate.now());

        while (size-- > 0) {
            try{
                Attendance attendance = new Attendance();

                attendance.setStudent(randomStudent());
                attendance.setMeditationType(randomMeditationType());
                attendance.setLocation(randomLocations());
                Date from = faker.date().past(4 * 30, TimeUnit.DAYS);

                LocalDate createdAt = toLocalDate(faker.date().between(from, now));
                attendance.setCreatedAt(createdAt);

                attendanceServiceImp.save(attendance);
            } catch (Exception e){
                size++;
            }
        }
    }

    public Student randomStudent() {
        int index = random.nextInt(students.size() - 1);

        return students.get(index);
    }

    public MeditationType randomMeditationType() {
        int index = random.nextInt(meditationTypes.size() - 1);

        return meditationTypes.get(index);
    }

    public Location randomLocations() {
        int index = random.nextInt(locations.size() - 1);

        return locations.get(index);
    }
}
