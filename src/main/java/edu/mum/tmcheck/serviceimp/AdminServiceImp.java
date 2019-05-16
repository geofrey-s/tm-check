package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Admin;
import edu.mum.tmcheck.domain.repository.AdminRepository;
import edu.mum.tmcheck.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin findById(long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }
}
