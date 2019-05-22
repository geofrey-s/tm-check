package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.serviceimp.IdCardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CardFixture extends BaseFixture {
    final static String SAMPLE_FILENAME = "src/main/java/edu/mum/tmcheck/fixtures/carddata.txt";

    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Override
    public void generate(int size) {
        System.out.println("Generating Card fixture data ...");

        Set<String> barcodedata = new HashSet<>();

        try (Stream<String> stream = Files.lines(Paths.get(SAMPLE_FILENAME).toAbsolutePath())) {
            stream.parallel().map(x -> x.split(",")[0]).forEach(barcodedata::add);

            barcodedata.forEach(x -> {
                Card card = new Card();
                card.setBarcode(x);
                card.setExpiryDate(futureDateByYear(1));
                idCardServiceImp.save(card);
            });
        } catch (IOException e) {
        }
    }

    protected String randomBarcode() {
        return fakeValuesService.bothify("#############");
    }
}
