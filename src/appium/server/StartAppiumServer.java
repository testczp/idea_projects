package appium.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by chenzepeng on 2020/1/17.
 */
public class StartAppiumServer {
//    private static final Logger log = LogManager.getLogger(StartAppiumServer.class.getName());

    public static Map<Integer, Integer> start() {
        int i = 0 ;

        try {
            if(System.getProperty("os.name").contains("indow")){

                ExecuteCommand.executed("taskkill /f /t /im node.exe", false, 0);
                //log.info("[Class-startAppiumServer][Method-Map] 清除node进程成功");
//                log.info("[Class-startAppiumServer][Method-Map] 清除node进程成功");
            }else{

                ExecuteCommand.executed("killall -9 node", false, 0);
            }
        } catch (Exception e1) {
            //log.info("[startAppiumServer] 清除node进程失败");
//            log.info("[Class-startAppiumServer][Method-Map] 清除node进程失败");
            e1.printStackTrace();
        }

        Map<Integer, Integer> port = AssignmentPort.port();//随机生成端口号

        List<String> list = DevicesNum.locateNum();//连接设备的信息列表

        //log.info("[startAppiumServer] 开始启动Appium-Server");
//        log.info("[Class-startAppiumServer][Method-Map] 开始启动Appium-Server");

        //System.out.println(System.getProperty("user.dir"));

        Set set = port.entrySet();

        if (port.size() == 0) { // map集合长度为0 防护衣启动失败

            //log.info("[startAppiumServer] 启动Appium-Server失败：未获取到分配的端口");
//            log.info("[Class-startAppiumServer][Method-Map] 启动Appium-Server失败：未获取到分配的端口");

        } else {

            for (Iterator iter = set.iterator(); iter.hasNext();) {

                Map.Entry entry = (Map.Entry) iter.next();
                Integer key = (Integer) entry.getKey();
                Integer value = (Integer) entry.getValue();

                String udid = list.get(i).split("\t")[0];//list-设备数量，uuid-设备id
                i = i++;  //没循环一次 i自加1
                //启动Appium-Server appium -p 5000-bp 6000-U udid
                try {
                    Date dNow = new Date( );
                    SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
                    String logStr = null;
                    if(System.getProperty("os.name").contains("indow")){
                        logStr = System.getProperty("user.dir")+"\\logs\\"+ft.format(dNow)+"-"+udid+".log";
                    }else{
                        logStr = System.getProperty("user.dir")+"/logs/"+ft.format(dNow)+"-"+udid+".log";
                    }
//                    String cmd = "appium -p "+key+" -bp "+value+" -U "+udid +">"+logStr;
//                    String cmd = "appium -p "+key+" -bp "+value+" -U "+udid
//                            + " --chromedriver-executable /Users/chenzepeng/Documents/driver/chromedriver";
                    String cmd = "appium -p "+key+" -bp "+value+" -U "+udid;
                    //启动Appium-Server 并且延时15S
                    //log.info("[Class-startAppiumServer][Method-Map] Appium Server启动命令："+cmd);
                    //log.info("[Class-startAppiumServer][Method-Map] Appium Server启动在【"+key+"】端口");
                    //log.info("[startAppiumServer] Appium Server启动在【"+key+"】端口");
                    ExecuteCommand.executed(cmd, false, 10);
                    //log.info("[Class-startAppiumServer][Method-Map] Appium Server启动延时结束");
                } catch (Exception e) {
                    //log.info("[startAppiumServer] Appium Server启动异常");
//                    log.info("[Class-startAppiumServer][Method-Map] Appium Server启动异常");
                    RuntimeException exception = new RuntimeException();
                    exception.printStackTrace();
                }
            }
        }
        //log.info("[startAppiumServer] Appium-Server启动完成");
//        log.info("[Class-startAppiumServer][Method-Map] Appium-Server启动完成");
        return port;
    }
}
