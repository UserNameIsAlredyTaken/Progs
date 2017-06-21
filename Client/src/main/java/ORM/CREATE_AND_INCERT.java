package ORM;

import Annotations.Column;
import Annotations.Table;

import javax.sql.rowset.JdbcRowSet;
import java.lang.reflect.Field;
import java.sql.Statement;

/**
 * Created by danil on 21.06.2017.
 */
public class CREATE_AND_INCERT {
    public void create(JdbcRowSet jrs, Object obj, Statement statement){
        try{
            Class<?> aClass = obj.getClass();
            Table tab = aClass.getAnnotation(Table.class);
            String tableName = "public.\""+tab.name()+"\"";
            System.out.println("- Create table in database table: " + tableName);
            String sqlPart1="CREATE TABLE "+tableName+"(";
            String sqlPart2="";
            String sqlPart3="PRIMARY KEY (";
            Field[] publicFields = aClass.getFields();
            for (Field field : publicFields) {
                Column col = field.getAnnotation(Column.class);
                sqlPart2+="\""+col.name()+"\" "+col.type()+",";
                if(col.isPrKey()){
                    sqlPart3+="\""+col.name()+"\",";
                }
            }
            String fullSQL=sqlPart1+sqlPart2+sqlPart3.substring(0,sqlPart3.length()-1)+"))WITH(OIDS = FALSE); ALTER TABLE "+tableName+" OWNER to postgres;";
            System.out.println(fullSQL);
            statement.executeUpdate(fullSQL);
            INCERT cr=new INCERT();
            cr.insertRow(jrs,obj,statement);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
