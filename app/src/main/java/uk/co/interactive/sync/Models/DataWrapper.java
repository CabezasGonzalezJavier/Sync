package uk.co.interactive.sync.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by javiergonzalezcabezas on 10/6/15.
 */
public class DataWrapper implements Serializable {

    private ArrayList<Item> item;

    public DataWrapper(ArrayList<Item> data) {
        this.item = data;
    }

    public ArrayList<Item> getItems() {
        return this.item;
    }

}
