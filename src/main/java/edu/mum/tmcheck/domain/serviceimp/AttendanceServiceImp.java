package edu.mum.tmcheck.domain.serviceimp;


import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.domain.services.AttendanceService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class AttendanceServiceImp implements AttendanceService {

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
    public void save() {

    }

    @Override
    public boolean loadFromFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))){
            stream
                    .filter(Objects::nonNull)
                    .map(line -> line.split(","))
                    .map(aLine -> isManualEntry(aLine) ? processManualAttendanceRecord(aLine) : processScannedAttendanceRecord(aLine))
                    .forEach(record -> {

                    });
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    protected Object[] processManualAttendanceRecord(String[] line) {

    }

    protected Object[] processScannedAttendanceRecord(String[]line){

    }

    protected boolean isManualEntry(String[] line){
        if (line.length == 0 || line[0].length() == 0) return false;

        // if the first entry is a date format
        return line[0].split("/").length > 1;
    }

}
