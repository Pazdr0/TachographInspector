package pl.bgolc.tachograph.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.service.UserService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*Klasa pobierajaca dane z bazy lub pliku*/
public class DataResolver {

    private final Logger log = LoggerFactory.getLogger(DataResolver.class);

    @Autowired
    private UserService userService;

    /*Method downloads String from file and reorders it to Data class*/
    private ArrayList<Data> dowloadFromFile(String pattern) {
        ArrayList<Data> data = new ArrayList<Data>();

        String splitter = ";";
        String newLine = "";

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(pattern));

            int i = 0;

            while((newLine = bufferedReader.readLine()) != null) {
                Data tempData = new Data();
                String[] line = newLine.split(splitter);
                tempData.setDate(line[0]);
                tempData.setActivity(line[1]);
                tempData.setFrom(line[2]);
                tempData.setTo(line[3]);
                tempData.setTimeSpent(line[4]);
                data.add(tempData);
            }
        } catch (FileNotFoundException ex) {
            log.error("File not found exception: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Data error" + ex.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.error("Error closing reader: " + ex.getMessage());
                }
            }
        }
        return (data);
    }

    /*Uses data from downloadFromFile method and deletes unwanted characters*/
    protected ArrayList<Data> dowloadDataFromFile(String pattern) {
//        pattern = "C:\\Users\\Bartek\\Documents\\Dokumenty\\PKK_cze_2017.csv";

        ArrayList<Data> newData = new ArrayList<Data>();
        ArrayList<Data> oldData = dowloadFromFile(pattern);

        for (Data temp : oldData) {
            if (!temp.getDate().equals("data")) {
                newData.add(temp);
            }
        }

        return newData;
    }

    protected ArrayList<Data> downloadDataFromDb() {
        ArrayList<Data> newData = new ArrayList<Data>();

        //TODO Pobrac dane z bazy i przeformowac je do listy <Data>

        return newData;
    }

    public void readData() {
        ArrayList<Data> data = new ArrayList<Data>();
        data = dowloadFromFile("C:\\Users\\Bartek\\Documents\\Dokumenty\\PKK_cze_2017.csv");

        for (Data tempData : data) {
            log.info("Aktywnosc: " + tempData.getActivity());
        }
        for (Data tempData : data) {
            log.info("Data: " + tempData.getDate());
        }
        for (int i=0; i<data.size(); i++) {
            log.info("Czas: " + data.get(i).getTimeSpent());
        }
    }
}
