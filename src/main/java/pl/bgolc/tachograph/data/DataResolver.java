package pl.bgolc.tachograph.data;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface DataResolver {

    public List<pl.bgolc.tachograph.data.Data> getDataList();

    public void downloadDataFromFile(int driverId, MultipartFile multipartFile);
}
