package pl.bgolc.tachograph.data.service;

import org.springframework.web.multipart.MultipartFile;
import pl.bgolc.tachograph.data.model.Data;

import java.util.List;

public interface DataResolver {

    public List<Data> getDataList();

    public void downloadDataFromFile(int driverId, MultipartFile multipartFile);
}
