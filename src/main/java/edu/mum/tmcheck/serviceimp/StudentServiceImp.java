package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Student;
<<<<<<< HEAD
import edu.mum.tmcheck.services.StudentService;
import org.springframework.stereotype.Service;

@Service
class StudentServiceImp implements StudentService {

    @Override
    public void create() {

    }

    @Override
    public Student get() {
        return null;
    }
=======
import edu.mum.tmcheck.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StudentServiceImp {
    @Autowired
    StudentRepository studentRepository;
>>>>>>> c78e0254c13b0fa373f23bde0537e8b11256a85a

    public Student findByBarcode(String barcode){
        return studentRepository.findByBarcode(barcode);
    }

    public Student findByStudentRegId(String regId){
        return studentRepository.findByStudentRegId(regId);
    }
}

