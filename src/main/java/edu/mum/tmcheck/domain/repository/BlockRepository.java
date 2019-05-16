package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    public Block getTopByIdIsTrue();

    @Query(value = "SELECT * FROM block b WHERE true LIMIT 1", nativeQuery = true)
    public Block getFirst();
}
