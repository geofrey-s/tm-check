package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.domain.repository.IdCardRepository;
import edu.mum.tmcheck.services.IdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdCardServiceImp implements IdCardService {
    @Autowired
    IdCardRepository idCardRepository;

    public Card save(Card card) {
        return idCardRepository.save(card);
    }

    public Card findByBarcode(String barcode) {
        return idCardRepository.findByBarcode(barcode);
    }

    @Override
    public void create() {

    }

    @Override
    public Card findById(long id) {
        return idCardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> findAll() {
        List<Card> records = new ArrayList<>();
        idCardRepository.findAll().forEach(records::add);

        return records;
    }
}
