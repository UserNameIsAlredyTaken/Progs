/**
 * Created by danil on 16.02.2017.
 */
package items;

import java.time.OffsetDateTime;
import java.util.Date;

public class Whine extends FoodResidus{

    public Whine(int wheight){
        this.dateTime = OffsetDateTime.now();
        long epochMilli = dateTime.toInstant().toEpochMilli();
        this.date = new Date(epochMilli);
        this.name="whine";
        this.wheight=wheight;
    }
    public Whine(String name){
        this.dateTime = OffsetDateTime.now();
        long epochMilli = dateTime.toInstant().toEpochMilli();
        this.date = new Date(epochMilli);
        this.name=name;
        this.wheight=50;
    }
    public Whine(String name,int wheight){
        this.dateTime = OffsetDateTime.now();
        long epochMilli = dateTime.toInstant().toEpochMilli();
        this.date = new Date(epochMilli);
        this.name=name;
        this.wheight=wheight;
    }

}