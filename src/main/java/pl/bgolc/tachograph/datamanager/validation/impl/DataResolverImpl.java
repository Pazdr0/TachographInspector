package pl.bgolc.tachograph.datamanager.validation.impl;

import pl.bgolc.tachograph.datamanager.validation.model.Data;
import pl.bgolc.tachograph.datamanager.validation.DataResolver;
import pl.bgolc.tachograph.datamanager.validation.operations.MonthConvert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/*
 * Class for downloading data either from file or database
 * and sorting it
 * */
public enum DataResolverImpl implements DataResolver {

	INSTANCE;
	
    //TODO dodac zapisywanie danych do bazy w metodzie downloadDataFromFile
    private List<Data> data;

    private DataResolverImpl() {
        data = new ArrayList<Data>();
    }    
    
    public static DataResolverImpl getInstance() {
    	return DataResolverImpl.INSTANCE;
    }
    
    /*
     * Get method for downloaded data array list
     * */
    @Override
    public List<Data> getData() {
        return data;
    }
    
    /*
     * Method downloads String from file and reorders it to Data class
     * */
    private void downloadFromFile(String pattern) {
        String splitter = ";";
        String newLine = "";
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(pattern));

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
            System.err.println("File not found exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Data error" + ex.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    System.err.println("Error closing reader: " + ex.getMessage());
                }
            }
        }
    }

    /*
     * Uses data from downloadFromFile method and deletes unwanted characters
     * */
    @Override
    public void downloadDataFromFile(String pattern) {
        ArrayList<Data> newData = new ArrayList<Data>();
        downloadFromFile(pattern);

        for (Data temp : data) {
            if (!temp.getDate().equals("data")) {
                temp.setLocalDate(transformStringToLocalDateEnum(temp.getDate()));
                newData.add(temp);
            }
        }
        data = newData;
    }

    /*
     * Method to download data from database
     * */
    public void downloadDataFromDb() {
        ArrayList<Data> newData = new ArrayList<Data>();

        //TODO Pobrac dane z bazy i przeformowac je do listy <Data>
    }

    /*
     * Method transforming String to LocalDate
     * to a LocalDate object
     * */
    private LocalDate transformStringToLocalDateEnum(String date) {
        String splitter = " ";
        String[] newDate = date.split(splitter);

        LocalDate localDate = LocalDate.of(Integer.parseInt(newDate[3]), MonthConvert.convert(newDate[1]), Integer.parseInt(newDate[2]));
        
        return localDate;
    }

    
    /*    public static LocalDate transformStringToLocalDate(String date) {
    String splitter = " ";
    String[] newDate = date.split(splitter);

    LocalDate localDate = LocalDate.of(Integer.parseInt(newDate[3]), MonthConverter.convert(newDate[1]), Integer.parseInt(newDate[2]));
    
    return localDate;
}*/
    
/*    
     * Metoda do usuniecia
     * 
    public void readData() {
        downloadDataFromFile("C:\\Users\\Bartek\\Documents\\Dokumenty\\PKK_cze_2017.csv");
        for (Data tempData : data) {
            System.out.println(tempData.getCalendarDate().get(Calendar.YEAR) + " " + (tempData.getCalendarDate().get(Calendar.MONTH) + 1) + " " +
                    tempData.getCalendarDate().get(Calendar.DAY_OF_MONTH) + ", Day of week: " + tempData.getCalendarDate().get(Calendar.DAY_OF_WEEK));
        }
        for (Data tempData : data) {
            System.out.println(tempData.getLocalDate().getYear() + " " + tempData.getLocalDate().getMonth() + " " +
                    tempData.getLocalDate().getDayOfMonth() + ", Day of week: " + tempData.getLocalDate().getDayOfWeek());
        }
    }*/
}
