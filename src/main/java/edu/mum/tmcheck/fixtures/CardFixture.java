package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.serviceimp.IdCardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CardFixture extends BaseFixture {
    final static String SAMPLE_FILENAME = "src/main/java/edu/mum/tmcheck/fixtures/carddata.txt";

    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Override
    public void generate(int size)
    {

        String filename = Paths.get("src/main/java/edu/mum/tmcheck/fixtures/carddata.txt").toAbsolutePath().toString();
        File file = new File(filename);

//        String filename = Paths.get(SAMPLE_FILENAME).toAbsolutePath().toString();
//        File file = new File(filename);

        Set<String> barcodedata = new HashSet<>();

//        try {
//            Scanner sc = new Scanner(file);
//            while (sc.hasNextLine())
//            {
//                String line = sc.nextLine();
//                String[] spllitedtext =  line.split(",");
//                barcodedata.add(spllitedtext[0]);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        try (Stream<String> stream = Files.lines(Paths.get(SAMPLE_FILENAME).toAbsolutePath())) {
            barcodedata = stream.parallel().map(x -> x.split(",")[0]).collect(Collectors.toSet());
        } catch (IOException e) {}

        int count = 0;
        barcodedata.forEach(x -> {
            Card card = new Card();
            card.setBarcode(x);
            card.setExpiryDate(futureDateByYear(1));
            idCardServiceImp.save(card);
        });
    }

    protected String randomBarcode() {
        return fakeValuesService.bothify("#############");
    }
}
