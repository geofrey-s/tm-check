package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.serviceimp.IdCardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class CardFixture extends BaseFixture {
    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Override
    public void generate(int size)
    {
        File file = new File("/home/nth/Downloads/WAAProjectFinal/tm-check/src/main/java/edu/mum/tmcheck/fixtures/carddata.txt");
        Set<String> barcodedata = new HashSet<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] spllitedtext =  line.split(",");
                barcodedata.add(spllitedtext[0]);
                System.out.println(spllitedtext[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Stream<String> stream = Files.lines(Paths.get("/home/nth/Downloads/WAAProjectFinal/tm-check/src/main/java/edu/mum/tmcheck/fixtures/carddata.txt")))
        {
            barcodedata = stream.parallel().map(x -> x.split(",")[0]).collect(Collectors.toSet());

        } catch (IOException e) {
        }

        int count = 0;
        System.out.println(barcodedata);
        barcodedata.forEach(x -> {
            Card card = new Card();
            card.setBarcode(x);
            card.setExpiryDate(futureDateByYear(1));
            idCardServiceImp.save(card);
            System.out.println(x  + "logggggg");
        });
    }

    protected String randomBarcode() {
        return fakeValuesService.bothify("#############");
    }
}
