package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Faculty;
import edu.mum.tmcheck.domain.repository.FacultyRepository;
import edu.mum.tmcheck.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImp implements FacultyService {
    @Autowired
    FacultyRepository facultyRepository;

    public Faculty findById(long id){
        return facultyRepository.findById(id).get();
    }

    @Override
    public void create() {

    }

    @Override
    public Faculty get() {
        return null;
    }

    public Faculty save(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFirst() {
        return facultyRepository.getFirst();
    }
}
