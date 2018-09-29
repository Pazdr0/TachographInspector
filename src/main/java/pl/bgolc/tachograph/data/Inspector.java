package pl.bgolc.tachograph.data;

import pl.bgolc.tachograph.data.model.Data;

import java.util.ArrayList;

/*Class validating drivers working time*/
public class Inspector {

    public void checkData() {

        ArrayList<Data> data = new DataResolver().dowloadDataFromFile("C:\\Users\\Bartek\\Documents\\Dokumenty\\PKK_cze_2017.csv");


    }
}
