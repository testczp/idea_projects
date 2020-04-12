package appium.base;

import appium.server.DevicesNum;
import appium.server.StartAppiumServer;
import appium.utils.Common;
import appium.utils.ReadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenzepeng on 2020/1/17.
 */
public class DriverBase {
    public AndroidDriver<MobileElement> driver;
//    public WebDriver driver;
    private List<String> UUIDList;//设备id列表
    private InitDriver init;
    private ReadProperties rw;

//    private static final Logger log = LogManager.getLogger(DriverBase.class.getName());

    public DriverBase() {
        // 读取appium连接属性
        rw = new ReadProperties(System.getProperty("user.dir") + "/resources/initDriver.properties");
        // 杀死当前正在运行的node进程，然后为当前连接的每台设备启动Appium-Server，返回设备列表的端口列表对象
        Map<Integer, Integer> portMap = StartAppiumServer.start();

        /**
         * 重构UUIDList 去掉"device"
         */
        UUIDList = DevicesNum.locateNum();//获取当前在连的设备id
        for (int i = 0; i < UUIDList.size(); i++) {

            String str = UUIDList.get(i);
            String UUID = str.split("\t")[0];
            UUIDList.remove(i);
            UUIDList.add(UUID);
        }
//        log.info("[Class-DriverBase] 当前连接上Appium-server的设备UUID是：" + UUIDList.toString());

        init = new InitDriver();
        /**
         * 初始化driver
         */
        Set<Entry<Integer, Integer>> entrySet = portMap.entrySet();
        for (Entry<Integer, Integer> entry : entrySet) {

            int a = 0;

            Integer AppiumPort = entry.getKey();

            String UUID = UUIDList.get(a);

//            log.info("[Class-DriverBase] 启动的UUID是:["+UUID+"],启动的Appium端口是：["+AppiumPort+"]");
//            AppiumPort = 4723;

            driver = init.getDriver(UUID, AppiumPort, "app");

            a++;
        }
    }

    /**
     * 封装element方法
     */
    public MobileElement findMobileElement(By by) {

        // 隐式等待
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);

        return (MobileElement)driver.findElement(by);
    }

    /**
     * 封装返回element集合的方法
     * */
    @SuppressWarnings("unchecked")
    public List<MobileElement> findMobileElements(By by) {

        // 隐式等待
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
        return (List<MobileElement>) driver.findElements(by);
    }

    public MobileElement findElementByAndroidUiautomator(String filter){
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
        return (MobileElement)driver.findElementByAndroidUIAutomator(filter);

    }

    public List<MobileElement> findElementsByAndroidUiautomator(String filter){
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
        return (List<MobileElement>)driver.findElementsByAndroidUIAutomator(filter);

    }

    /**
     * 封装closed方法
     */
    public void close() {

        driver.closeApp();
//        driver.close();
    }

    /**
     * 获取APP的宽和高 (获取手机屏幕的最大点坐标)
     */
    public List<Integer> getXY() {

        List<Integer> WidthAndHeight = new ArrayList<>();

        int X = driver.manage().window().getSize().getWidth();
        int Y = driver.manage().window().getSize().getHeight();

        WidthAndHeight.add(X);
        WidthAndHeight.add(Y);

        return WidthAndHeight;
    }

    /**
     * 捕获当前控件在屏幕的坐标
     * @param element 控件元素
     * @return 返回包含当前元素坐标点list列表，null or list object，list分别存放开始和终点的xy坐标值
     */
    public List<Integer> getElementXY(MobileElement element) {
        List<Integer> xy = null;
        try{
            xy = new ArrayList<>();
            xy.add(element.getRect().getX());
            xy.add(element.getRect().getY());
            xy.add(element.getLocation().getX() + element.getRect().getWidth());
            xy.add(element.getRect().getY() + element.getRect().getHeight());
        }catch (Exception ex){
//            log.info("[Class-DriverBase][Method-getElementXY] 获取元素坐标失败");
        }
        return xy;
    }

    /**
     * drivr的滑动 全屏滑动
     */
    public void driverSwipe(int startx, int starty, int endx, int endy) {

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(rw.getValue("implicitlyWait")), TimeUnit.SECONDS);
//        TouchActions action = new TouchActions(driver);
//        action.down(startx, starty);
//        action.move(endx, endy);
//        action.perform();



        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startx, starty)).waitAction().moveTo(PointOption.point(endx, endy)).release();
//        action.press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, endy)).release();
        action.perform();
//        action.release();

    }

    /**
     * 对坐标点位进行长按操作
     */
    public void LongPressPoint(int x, int y) {

//        TouchAction touchAction = new TouchAction(driver);
//        touchAction.longPress(x, y, duration);
//        WebElement noteDelete = driver.findElementByXPath(
//                "//android.widget.ListView[@resource-id=‘android:id/list‘]/android.widget.RelativeLayout[1]");
//        int x = noteDelete.getLocation().getX();
//        int y = noteDelete.getLocation().getY();

        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(x,y)).release().perform();  //长按
        Duration duration =  Duration.ofMillis(2000);

        action.press(PointOption.point(x,y))   //长按和滑动都适用
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(x,y))
                .release()
                .perform();

    }

    /**
     * 对元素进行长按操作
     */
    public void LongPressElement(MobileElement element) {

        TouchActions action = new TouchActions(driver);
        action.longPress(element);
        action.perform();
    }

    /**
     * 封装tap 对坐标进行点击
     */
    public void driverTap(int fingers, int x, int y, int duration) {

//        driver.tap(fingers, x, y, duration);
//        TouchAction action = new TouchAction(driver);
//        action.tap(PointOption.point(x,y));
//        action.perform();
    }

    /**
     * 对元素进行点击
     */
    public void driverTapOnElement(MobileElement element) {

        TouchActions action = new TouchActions(driver);
        action.singleTap(element);
        action.perform();

    }

    public void driverScrollByUiautomator(int sted){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollBackward()");
    }



    public static void main(String[] args) {

//        Iterator<String> set = System.getProperties().stringPropertyNames().iterator();
//        while(set.hasNext()){
//            System.out.println(set.next());
//        }

        System.out.println(System.getProperty("user.dir"));

    }

}
