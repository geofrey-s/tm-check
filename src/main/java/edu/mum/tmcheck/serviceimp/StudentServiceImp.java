package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.repository.StudentRepository;
import edu.mum.tmcheck.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student findByBarcode(String barcode) {
        return studentRepository.findByBarcode(barcode);
    }

    public Student findByStudentRegId(String regId) {
        return studentRepository.findByStudentRegId(regId);
    }

    @Override
    public void create() {

    }

    @Override
    public Student get() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        List<Student> records = new ArrayList<>();
        studentRepository.findAll().forEach(records::add);

        return records;
    }

    @Override
    public Student findByID(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student findByUsername(String username){
        return studentRepository.findByUsername(username);
    }
}

