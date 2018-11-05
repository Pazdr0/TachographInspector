package pl.bgolc.tachograph.data.temporary;

import java.util.List;

public interface DataResolver {

    public List<pl.bgolc.tachograph.data.Data> getData();

    public void downloadDataFromFile(String pattern);

    public void downloadDataFromDb();
}
