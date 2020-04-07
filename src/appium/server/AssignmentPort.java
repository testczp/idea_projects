package appium.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenzepeng on 2020/1/17.
 */
public class AssignmentPort {
    /**
     * 分配端口：只做本机分配  Appium-server在远程机器存在日志收集的问题
     * */
    public static Map<Integer,Integer> port(){

        Map<Integer,Integer> map = new HashMap<>();
        int numbers = DevicesNum.locateNum().size();
        //随机产生端口 根据Device状态的设备
        if(numbers>0){
            while(map.size() < numbers){

                int a = (int)(Math.random()*(9999-7000+1))+7000;//产生1000-9999的随机数

                //检测端口是否被占用
                if(!IsPortBeUsed.isLocatePortUsed(a) && !IsPortBeUsed.isLocatePortUsed(a+1)){

                    map.put(a, a+1);
                }
            }
        }
        return map;
    }
}
