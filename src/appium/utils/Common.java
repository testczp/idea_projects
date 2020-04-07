package appium.utils;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzepeng on 2020/3/17.
 */
public class Common {

    public static boolean isInRange(String[] num, String[] range){
        boolean isIn = false;
        if(range != null && range.length > 1){
            if((Integer.parseInt(range[0]) >= Integer.parseInt(num[0])
                    && Integer.parseInt(range[0]) <= Integer.parseInt(num[1]))
                    || (Integer.parseInt(range[1]) >= Integer.parseInt(num[0])
                    && Integer.parseInt(range[1]) <= Integer.parseInt(num[1]))
                    || (Integer.parseInt(range[0]) < Integer.parseInt(num[0])
                    && Integer.parseInt(range[1]) >= Integer.parseInt(num[0]))
                    || (Integer.parseInt(range[1]) > Integer.parseInt(num[1])
                    && Integer.parseInt(range[0]) <= Integer.parseInt(num[1]))){
                isIn = true;
            }
        }
        return isIn;
    }

    public static long formatDate(){
        long result;
        Date d = new Date();
        result = d.getTime();
        return result;
    }

    public static Map<String, String> getJsonData(String json){

        JSONObject jObj = new JSONObject(json);
        Map<String, Object> tmp = jObj.toMap();
        Map<String, String> result = new HashMap<>();
        result.put("names",String.valueOf(tmp.get("names")));
        result.put("companys",String.valueOf(tmp.get("companys")));
        result.put("hrs",String.valueOf(tmp.get("hrs")));
        result.put("distances",String.valueOf(tmp.get("distances")));
        return result;
    }
}
