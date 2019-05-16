package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.serviceimp.IdCardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CardFixture extends BaseFixture {
    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            Card card = new Card();
            card.setBarcode(randomBarcode());
            card.setExpiryDate(futureDateByYear(1));

            idCardServiceImp.save(card);
        }
    }

    protected String randomBarcode(){
        return fakeValuesService.bothify("#############");
    }
}
