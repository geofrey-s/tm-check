package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.domain.repository.IdCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdCardServiceImp {
    @Autowired
    IdCardRepository idCardRepository;

    public Card save(Card card) {
        return idCardRepository.save(card);
    }

    public Card findByBarcode(String barcode) {
        return idCardRepository.findByBarcode(barcode);
    }
}
