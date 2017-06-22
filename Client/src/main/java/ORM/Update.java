package ORM;

import Annotations.Column;
import Annotations.Table;

import javax.sql.rowset.JdbcRowSet;
import java.lang.reflect.Field;
import java.sql.Statement;

/**
 * Created by danil on 21.06.2017.
 */
public class UPDATE {
    public void updateRow(JdbcRowSet jrs, Object oldObj,Object newObj, Statement statement){
        try{
            Class<?> oldClass = oldObj.getClass();
            Class<?> newClass = oldObj.getClass();
            Table tab = oldClass.getAnnotation(Table.class);
            String tableName = "public.\""+tab.name()+"\"";
            System.out.println("- Insert row into database table: " + tableName);
            String sqlPart1="UPDATE "+tableName+" SET ";
            String sqlPart2="";
            String sqlPart3="";
            Field[] newFields = newClass.getFields();
            for (Field field : newFields) {
                Column col = field.getAnnotation(Column.class);
                if(col.type().equals("text")){
                    sqlPart2+="\""+col.name()+"\"='"+field.get(newObj).toString()+"',";
                }else{
                    sqlPart2+="\""+col.name()+"\"="+field.get(newObj).toString()+",";
                }
            }
            Field[] conditions = oldClass.getFields();
            for (Field field : conditions) {
                Column col = field.getAnnotation(Column.class);
                if(col.isPrKey()){
                    if(col.type().equals("text")){
                        sqlPart3+="\""+col.name()+"\"='"+field.get(oldObj).toString()+"' AND ";
                    }else{
                        sqlPart3+="\""+col.name()+"\"="+field.get(oldObj).toString()+" AND ";
                    }
                }
            }
            String fullSQL=sqlPart1+sqlPart2.substring(0,sqlPart2.length()-1)+" WHERE "+sqlPart3.substring(0,sqlPart3.length()-5)+";";
            System.out.println(fullSQL);
            statement.executeUpdate(fullSQL);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
