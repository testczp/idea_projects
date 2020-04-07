package appium.handle;

import appium.base.DriverBase;
import appium.page.HomePage;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzepeng on 2020/1/19.
 */
public class HomeHandle {

    //页面元素的实体类，负责获取元素控件对象
    private HomePage hp;
//    private static final Logger log = LogManager.getLogger(HomeHandle.class.getName());

    public HomeHandle(DriverBase driver) {

        hp = new HomePage(driver);
    }

//    /**
//     * 点击"允许"授权操作
//     */
//    public void clickAllowed() {
//
//        MobileElement allowed = hp.isAllowed();
//
//        if (allowed == null) {
//
//            log.info("[Class-HomeHandle][Method-clickAllowed] isAllowed元素为空");
//
//        } else {
//
//            hp.click(allowed);
//        }
//    }





    /**
     * 向下滑动的操作
     * */
//    public void Swipe(){
//
//        List<Integer> widthAndHeight = hp.getWidthAndHeight();
//        int X = widthAndHeight.get(0);
//        int Y = widthAndHeight.get(1);
//        log.info("[Class-HomeHandle][Method-Swipe] 获取到的屏幕分辨率为["+X+"X"+Y+"]");
//
//        //滑动Y轴 从(8/10)*Y 滑动到 (2/10)*Y
//        log.info("[Class-HomeHandle][Method-Swipe] 滑动坐标从["+X/2+" "+5*Y/10+"]-->["+X/2+" "+3*Y/10+"]");
//        hp.driverSwipe(X/2, 5*Y/10, X/2, 3*Y/10,4);
//    }
}
