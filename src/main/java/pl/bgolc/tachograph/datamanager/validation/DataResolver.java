package pl.bgolc.tachograph.datamanager.validation;

import pl.bgolc.tachograph.datamanager.validation.model.Data;

import java.util.List;

public interface DataResolver {

    public List<Data> getData();

    public void downloadDataFromFile(String pattern);

    public void downloadDataFromDb();
}
