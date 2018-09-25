package pl.bgolc.tachograph.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataResolver {

    private final Logger log = LoggerFactory.getLogger(DataResolver.class);

    public ArrayList<Data> resovlveData(String pattern) {
        ArrayList<Data> data = new ArrayList<Data>();

        String splitter = ";";
        String newLine = "";

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(pattern));

            Data tempData = new Data();
            while((newLine = bufferedReader.readLine()) != null) {
                String[] line = newLine.split(splitter);
                tempData.setData(line[0]);
                tempData.setActivity(line[1]);
                tempData.setFrom(line[2]);
                tempData.setTo(line[3]);
                tempData.setTimeSpent(line[4]);
                log.info("Od: " + line[2]);
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
        return data;
    }

    public void readData() {
        ArrayList<Data> data = resovlveData("C:\\Users\\Bartek\\Documents\\Dokumenty\\PKK_cze_2017.csv");

        for (Data tempData : data) {
            log.info("Aktywnosc: " + tempData.getActivity());
        }
        for (Data tempData : data) {
            log.info("Data: " + tempData.getData());
        }
    }
}
