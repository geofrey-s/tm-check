package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
}
