package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {
    public Admin findById(long id);
}
