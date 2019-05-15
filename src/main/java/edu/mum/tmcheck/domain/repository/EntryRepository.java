package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
}
