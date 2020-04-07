package appium.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenzepeng on 2020/3/16.
 */
public class Regular {

    public static List<String> matchGroupByPattern(String expression, String text){

        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(text);
        int count = m.groupCount();
        List<String> values = new ArrayList<>();
        if(m.matches()){
            for(int i = 0; i < count; i++){
                values.add(m.group(i + 1));
            }
        }
        if(values.size() <= 0){
            values = null;
        }
        return values;
    }
}
