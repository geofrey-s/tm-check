package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    public Admin findById(long id);
    public Admin save(Admin admin);
}
