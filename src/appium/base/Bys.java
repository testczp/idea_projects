package appium.base;

import appium.utils.ReadProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by chenzepeng on 2020/1/19.
 */
public class Bys {
    private ReadProperties properties;
//    private static final Logger log = LogManager.getLogger(Bys.class.getName());

    public Bys(String filePath){

        this.properties = new ReadProperties(filePath);
    }
    /**
     * 静态by方法
     * */
    public By by(String key){

        String value = properties.getValue(key);
        String LocateMethon = value.split(">")[0];
        String LocateEle = value.split(">")[1];
//        if(value.split(">").length == 3){
//            index = Integer.parseInt(value.split(":")[2]);
//        }

        if(LocateMethon.equalsIgnoreCase("id")){
//            log.info("[Class-by][Method-by] 当前定位方式是id,定位元素"+LocateEle);

            return By.id(LocateEle);

        }else if(LocateMethon.contentEquals("name")){

//            log.info("[Class-by][Method-by] 当前定位方式是name,定位元素"+LocateEle);
            return By.name(LocateEle);

        }else if(LocateMethon.contentEquals("xpath")){

//            log.info("[Class-by][Method-by] 当前定位方式是name,定位元素"+LocateEle);
            return By.xpath(LocateEle);

        }else if(LocateMethon.equalsIgnoreCase("className")){

//            log.info("[Class-by][Method-by] 当前定位方式是className,定位元素"+LocateEle);
            return By.className(LocateEle);

        }else if(LocateMethon.equalsIgnoreCase("tagName")){

//            log.info("[Class-by][Method-by] 当前定位方式是tagName,定位元素"+LocateEle);
            return By.linkText(LocateEle);

        }else{

//            log.info("[Class-by][Method-by] 当前定位方式是xpath,定位元素"+LocateEle);
            return By.xpath(LocateEle);
        }

    }

    public String uiautomatorBy(String key){

        String value = properties.getValue(key);
        String LocateMethon = value.split(">")[0];
        String LocateEle = value.split(">")[1];

        if(LocateMethon.equalsIgnoreCase("uiautomator")){
//            log.info("[Class-by][Method-by] 当前定位方式是uiautomator,定位元素"+LocateEle);
            return LocateEle;
        }else{
            return null;
        }
    }
}
