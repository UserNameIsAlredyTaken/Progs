package ORM;

import Annotations.Column;
import Annotations.Table;

import javax.sql.rowset.JdbcRowSet;
import java.lang.reflect.Field;
import java.sql.Statement;

/**
 * Created by danil on 20.06.2017.
 */
public class INCERT {
    public void insertRow(JdbcRowSet jrs, Object obj, Statement statement){
        try{
            Class<?> aClass = obj.getClass();
            Table tab = aClass.getAnnotation(Table.class);
            String tableName = "public.\""+tab.name()+"\"";
            System.out.println("- Insert row into database table: " + tableName);
            String sqlPart1="INSERT INTO "+tableName+"(";
            String sqlPart2="";
            String sqlPart3="";
            Field[] publicFields = aClass.getFields();
            for (Field field : publicFields) {
                Column col = field.getAnnotation(Column.class);
                sqlPart2+="\""+col.name()+"\",";
                if(col.type().equals("text")){
                    sqlPart3+="'"+field.get(obj).toString()+"',";
                }else{
                    sqlPart3+=field.get(obj).toString()+",";
                }
            }
            String fullSQL=sqlPart1+sqlPart2.substring(0,sqlPart2.length()-1)+") VALUES ("+sqlPart3.substring(0,sqlPart3.length()-1)+");";
            System.out.println(fullSQL);
            statement.executeUpdate(fullSQL);
            }catch(Exception e){
            System.out.println(e);
        }
    }
}
