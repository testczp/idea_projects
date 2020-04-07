package appium.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzepeng on 2020/2/21.
 */
public class ReadData {

    private ReadProperties rp;

    public ReadData(String filepath){
        rp = new ReadProperties(filepath);
    }

    /**
     * 获取测试用例的预期结果
     * @return 返回具体数据
     */
    public String getExceptValue(String key){

        String result = null;
        String[] values = null;
        try{
            values = rp.getValue(key).split(">");
        }catch (Exception ex){
            System.out.println("获取不到预期结果");
        }
        result = values[values.length - 1];
        return result;
    }

    /**
     * 获取测试用例的输入数据
     * @param key
     * @return 返回null或具体数据列表
     */
    public List<String> getTestValue(String key){

        List<String> result = null;
        String[] values = rp.getValue(key).split(">");
        if(values.length > 1){
            result = new ArrayList<>();
            for(int i = 0; i <= values.length - 2; i++){
                result.add(values[i]);
            }
        }
        return result;
    }
}
