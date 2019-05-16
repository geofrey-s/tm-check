package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Admin;

public interface AdminService {
    public Admin findById(long id);
    public Admin save(Admin admin);
}
