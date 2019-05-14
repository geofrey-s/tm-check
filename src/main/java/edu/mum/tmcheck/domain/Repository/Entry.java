package edu.mum.tmcheck.domain.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Entry extends CrudRepository<Entry, Long> {
}
