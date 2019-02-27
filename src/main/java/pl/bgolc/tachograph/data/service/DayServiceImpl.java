package pl.bgolc.tachograph.data.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.data.model.Day;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DayServiceImpl implements DayService {

    private Logger log = LoggerFactory.getLogger(DayServiceImpl.class);

    private DataService dataService;

    @Autowired
    public DayServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public List<Day> getDayList(int id) {
        return sortDataIntoDays(dataService.findByDriverId(id));
    }

    @Override
    public List<Day> getDayList(String since, String to, int id) {
        return sortDataIntoDays(dataService.findDataSinceTo(since, to, id));
    }

    private List<Day> sortDataIntoDays(List<Data> dataList) {
        List<Day> dayList = new ArrayList<Day>();

        if (dataList.isEmpty()) {
            log.info("There is no data in chosen period");
        } else {
            while (!dataList.isEmpty()) {
                List<Data> tempDataList = new ArrayList<>();
                tempDataList.add(dataList.get(0));

                for (int i = 1; i < dataList.size(); i++) {

                    if (tempDataList.get(0).compareTo(dataList.get(i)) == 0) {
                        tempDataList.add(dataList.get(i));
                    }

                }
                Day day = new Day();
                day.setLocalDate(tempDataList.get(0).getLocalDate());
                day.setDataList(tempDataList);
                dayList.add(day);
                dataList.removeAll(tempDataList);
            }
        }

        return dayList;
    }
}