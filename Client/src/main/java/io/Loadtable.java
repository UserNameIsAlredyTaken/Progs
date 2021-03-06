package io;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import items.FoodResidus;
import serealize.XMLworker;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vladp on 09.05.2017.
 */
public class Loadtable extends Thread{
    private ObservableList<FoodResidus> data;
    private TableView table;
    private String path;
    public Loadtable(String str, ObservableList<FoodResidus> data, TableView table, String path){
        super(str);
        this.data=data;
        this.table=table;
        this.path=path;
    }
    public void run() {
        try {
            HashSet<FoodResidus> set = XMLworker.getCollection(path);
            Iterator<FoodResidus> iterator = set.iterator();
            data.clear();
            while (iterator.hasNext()) {
                data.add(iterator.next());
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
