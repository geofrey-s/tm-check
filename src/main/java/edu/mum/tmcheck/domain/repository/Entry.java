package edu.mum.tmcheck.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Entry extends CrudRepository<Entry, Long> {
}
