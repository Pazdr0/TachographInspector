package pl.bgolc.tachograph.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.bgolc.tachograph.data.exceptions.DateTransformException;
import pl.bgolc.tachograph.data.exceptions.TimeTransformException;
import pl.bgolc.tachograph.operations.MonthConvert;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
 * Class for downloading dataList either from file or database
 * and sorting it
 * */
@Service
class DataResolverImpl implements DataResolver {

//    INSTANCE;

	private Logger log = LoggerFactory.getLogger(DataResolverImpl.class);

    private List<Data> dataList;

    /*
    * Constructor
    * */
    private DataResolverImpl() {
        dataList = new ArrayList<Data>();
    }    

    /*
    * Getters
    * */
/*    public static DataResolverImpl getInstance() {
    	return DataResolverImpl.INSTANCE;
    }*/

    @Override
    public List<Data> getDataList() {
        return dataList;
    }


    /*
     * Method downloads String from file and reorders it to Data class
     * */
    @Override
    public void downloadDataFromFile(int driverId, MultipartFile multipartFile) {
        dataList = new ArrayList<Data>();
        String splitter = ";";
        String newLine = "";
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = multipartFile.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while((newLine = bufferedReader.readLine()) != null) {
                Data tempData = new Data(driverId);

                String[] line = newLine.split(splitter);
                if(!line[0].equals("data")) {
                    tempData.setLocalDate(transformStringToLocalDate(line[0]));
                    tempData.setActivity(line[1]);
                    tempData.setStartTime(transformStringToLocalTime(line[2]));
                    tempData.setEndTime(transformStringToLocalTime(line[3]));
                    tempData.setTimeSpent(transformStringToLocalTime(line[4]));
                    dataList.add(tempData);
                }
            }
        } catch (DateTransformException ex) {
            log.error("Error transforming String to LocalDate object: " + ex.getMessage());
        } catch (TimeTransformException ex) {
            log.error("Error transforming String to LocalTime object: " + ex.getMessage());
        } catch (IOException ex) {
            log.error("IOException: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("String transforming exception: " + ex.getMessage());
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
     * Method transforming String to LocalDate
     * */
    private LocalDate transformStringToLocalDate(String date) throws DateTransformException {
        String splitter = " ";
        String[] newDate = date.split(splitter);

        LocalDate localDate = LocalDate.of(1,1,1);

        try {
            localDate = LocalDate.of(Integer.parseInt(newDate[3]), MonthConvert.convert(newDate[1]), Integer.parseInt(newDate[2]));
        } catch (Exception ex) {
            throw new DateTransformException();
        }

        return localDate;
    }

    /*
     * Method transforming String to LocalTime
     * */
    private LocalTime transformStringToLocalTime(String time) throws TimeTransformException {
        String splitter = ":";
        String[] newTime = time.split(splitter);
        if (newTime[0].equals("24")) {
            newTime[0] = "23";
            newTime[1] = "59";
        }

        LocalTime localTime;

        try {
            localTime = LocalTime.of(Integer.parseInt(newTime[0]), Integer.parseInt(newTime[1]));
        } catch (Exception ex) {
            throw new TimeTransformException(ex.getMessage());
        }

        return localTime;
    }

}
