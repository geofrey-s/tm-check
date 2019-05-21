package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Card;

import java.util.List;

public interface IdCardService {
    public void create();
    //    public void update();
    //public IdCard get();
//    public void save();
    public Card findById(long id);
    public List<Card> findAll();
}
