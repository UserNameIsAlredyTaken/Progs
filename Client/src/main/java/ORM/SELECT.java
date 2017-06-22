package ORM;

import Annotations.Column;
import Annotations.Table;
import items.Whine;

import javax.sql.rowset.JdbcRowSet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by danil on 20.06.2017.
 */
public class SELECT {
    public <T> HashSet<T> getAllObj(Class<T> cl, Statement statement){
        try{
            Table tab = cl.getAnnotation(Table.class);
            String tableName = "public.\""+tab.name()+"\"";
            System.out.println("- Select rows from database table: " + tableName);
            String fullSQL="SELECT * FROM "+tableName+";";
            System.out.println(fullSQL);
            ResultSet resultSet = statement.executeQuery(fullSQL);
            HashSet<T> data = new HashSet<>();
            while(resultSet.next()){
                T DBInstance = cl.newInstance();
                Field[] fields = cl.getFields();
                for(Field field : fields){
                    Column col = field.getAnnotation(Column.class);
                    field.set(DBInstance, resultSet.getObject(col.name()));
                }
                data.add(DBInstance);
            }
            return data;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
