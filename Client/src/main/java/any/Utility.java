package any;

import java.io.UnsupportedEncodingException;

/**
 * Created by danil on 22.06.2017.
 */
public class Utility {
    public static  String GetLocalizedString(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
