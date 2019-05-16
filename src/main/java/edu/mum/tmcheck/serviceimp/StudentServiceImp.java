package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImp {
    @Autowired
    StudentRepository studentRepository;

    public Student findByBarcode(String barcode){
        return studentRepository.findByBarcode(barcode);
    }

    public Student findByStudentRegId(String regId){
        return studentRepository.findByStudentRegId(regId);
    }
}

