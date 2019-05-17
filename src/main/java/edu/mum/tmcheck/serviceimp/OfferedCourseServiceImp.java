package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.domain.repository.OfferedCourseRepository;
import edu.mum.tmcheck.services.OfferedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferedCourseServiceImp implements OfferedCourseService {
    @Autowired
    OfferedCourseRepository offeredCourseRepository;

    @Override
    public void create() {}

    @Override
    public OfferedCourse get() {
        return null;
    }

    @Override
    public OfferedCourse save(OfferedCourse instance) {
        return offeredCourseRepository.save(instance);
    }
}
