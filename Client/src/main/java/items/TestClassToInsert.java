package items;

import Annotations.Column;
import Annotations.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Created by danil on 21.06.2017.
 */
@Table(name="TestClassToInsert")
public class TestClassToInsert {
    @Column(name="SomeText",type="text",isPrKey = true)
    public String SomeText = "tekstic";
    @Column(name="SomeBigInt",type="bigint",isPrKey = true)
    public int SomeBigInt = 919;
    @Column(name="SomeBoolean",type="boolean",isPrKey = false)
    public boolean SomeBoolean = false;
}
