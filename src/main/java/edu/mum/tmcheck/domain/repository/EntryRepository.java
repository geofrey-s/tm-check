package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    public List<Entry> findAllByIdNot(long id);
}
