package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdCardRepository extends CrudRepository<Card, Long> {
    public Card findByBarcode(String barcode);
}
