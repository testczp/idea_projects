package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import appium.utils.Common;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzepeng on 2020/1/19.
 */
public class PageBase {
//    private static final Logger log = LogManager.getLogger(PageBase.class.getName());
    public DriverBase driver;
    private Bys by;

    /**
     * 构造方法 初始化DriverBase对象
     */
    public PageBase(DriverBase driver) {

        this.driver = driver;
    }

    /**
     * element方法
     */
    public MobileElement element(By by) {

        return driver.findMobileElement(by);
    }

    /**
     * element集合的方法
     */
    public List<MobileElement> elements(By by) {

        return driver.findMobileElements(by);
    }

    public MobileElement elementByUiautomator(String filter){

        return driver.findElementByAndroidUiautomator(filter);
    }

    public List<MobileElement> elementsByUiautomator(String filter){

        return driver.findElementsByAndroidUiautomator(filter);
    }

    /**
     * 封装click方法
     */
    public void click(MobileElement element) {

        element.click();
    }

    /**
     * 向指定控件输入数据
     * @param element
     * @param text
     */
    public void inputValue(MobileElement element, String text) {

        try{
            element.sendKeys(new CharSequence[]{text});
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }
    }

    /**
     * 封装sendkeys方法
     */
    public void sendKeys(MobileElement element, String value) {

        element.clear();
        element.sendKeys(value);
//        log.info("[Class-pageBase][Method-sendKeys] "+element.toString()+"元素输入"+value);

    }

    /**
     * 封装滑动的操作
     */
    public List<MobileElement> driverSwipeList(int endy, int maxCount, int swipeCount, By by, int offset) {

//        int count = 0;
        int width;
        List<MobileElement> tmp = null;
        List<MobileElement> list = new ArrayList<>();
        String beforeSource = "";
        String afterSource = "";
        MobileElement element;
        int starty = -1;
        int startx;
        int endx;

        width = this.driver.driver.manage().window().getSize().getWidth();
        startx = width / 2;
        endx = startx;
        tmp = this.driver.findMobileElements(by);

        if(tmp.size() >= maxCount){
//                beforeSource = this.driver.driver.getPageSource();
            for(int i = 0; i < swipeCount; i++) {
                if (i == 0) {
                    list.addAll(tmp);

                }
                starty = tmp.get(0).getLocation().getY() + tmp.get(0).getRect().getHeight() + offset;
                if (beforeSource.equals(afterSource) == false || beforeSource.equals("")) {
                    beforeSource = this.driver.driver.getPageSource();
                    driver.driverSwipe(width / 2, starty, width / 2, endy);
                    afterSource = this.driver.driver.getPageSource();
                    if (beforeSource.equals(afterSource) == false) {
                        tmp = this.driver.findMobileElements(by);
                        element = tmp.get(tmp.size() - 1);
                        list.add(element);
                    }

                } else if (beforeSource.equals(afterSource)) {
                    break;
                }
            }

        }else if(tmp.size() < maxCount){
            list.addAll(tmp);
        }
        if(list.size() == 0){
            list = null;
        }

        return list;

    }

    public Map<String, String> driverScroll(int endy, int maxCount, int swipeCount, String[] bys, int offset, String file) {

        int width;
        Map<String, String> result = new HashMap<>();
        List<MobileElement> tmp = null;
        String beforeSource = "";
        String afterSource = "";
        by = new Bys(file);
        double starty = -1;
        String tmp2 = "";

        width = this.driver.driver.manage().window().getSize().getWidth();

        for(int i = 0; i < bys.length; i++){
            tmp = this.driver.findElementsByAndroidUiautomator(by.uiautomatorBy(bys[i]));
            if(tmp.size() > 0){
                for(int j = 0; j < tmp.size() - 1; j++){
                    tmp2 = tmp2 +"," + tmp.get(j).getText();
                }
                result.put("" + i,tmp2.substring(1));
                tmp2 = "";
            }else{
                System.out.println("第" + i + "次循环捕获不到对象！");
                return null;
            }

        }

        if(tmp.size() >= maxCount){
//                beforeSource = this.driver.driver.getPageSource();
            for(int i = 0; i < swipeCount; i++) {
                starty = tmp.get(0).getRect().getY() + tmp.get(0).getRect().getHeight() + offset;
                if (beforeSource.equals(afterSource) == false || beforeSource.equals("")) {
                    beforeSource = this.driver.driver.getPageSource();
                    driver.driverSwipe(width / 2, new Double(starty).intValue(), width / 2, endy);
                    afterSource = this.driver.driver.getPageSource();
                    if (beforeSource.equals(afterSource) == false) {
                        for(int j = 0; j < bys.length; j++){
                            int no = new Double(Math.ceil(starty / new Integer(tmp.get(0).getRect().getHeight()).doubleValue())).intValue();
                            tmp = this.driver.findElementsByAndroidUiautomator(by.uiautomatorBy(bys[j]));
                            for(int k = tmp.size() - 1; k > tmp.size() - 1 - no; k--){
                                MobileElement ele = tmp.get(k - 1);
                                result.put("" + j,result.get("" + j) + "," + ele.getText());
                            }

                        }
                    }

                } else if (beforeSource.equals(afterSource)) {
                    break;
                }
                if(i == swipeCount - 1){

                }
            }

        }
        if(result.size() == 0){
            result = null;
        }

        return result;

    }

    public void driverScrollByUiautomator(int sted){
        driver.driverScrollByUiautomator(sted);
    }

    /**
     * 封装获取APP高度 宽度的操作
     *
     * @return List集合长度为2*n 基数表示X轴宽度； 偶数表示Y轴 高度
     */
    public List<Integer> getWidthAndHeight() {

//        log.info("[Class-pageBase][Method-getWidthAndHeight] 获取到分辨率为："+driver.getXY().toString());

        return driver.getXY();
    }

    /**
     * 关闭APP
     */
    public void closeAPP() {

        driver.close();
//        log.info("[Class-pageBase][Method-closeAPP] 关闭APP，结束当前执行...");
    }

    /**
     * 对APP坐标的长按操作
     */
    public void LongPressPoint(int x, int y, int duration) {

        driver.LongPressPoint(x, y);
//        log.info("[Class-pageBase][Method-LongPressPoint] 对坐标["+x+","+y+"]进行长按操作，延时"+duration+"毫秒");
    }

    /**
     * 对元素进行长按操作
     */
    public void LongPressElement(MobileElement element, int duration) {

        driver.LongPressElement(element);
//        log.info("[Class-pageBase][Method-LongPressPoint] 对元素"+element.toString()+"进行长按操作，延时"+duration+"毫秒");
    }

    /**
     * 对坐标进行点击
     */
    public void tapPoint(int fingers, int x, int y, int duration) {

        driver.driverTap(fingers, x, y, duration);
//        log.info("[Class-pageBase][Method-LongPressPoint] "+fingers+"根手指对坐标["+x+","+y+"]进行点击操作，延时"+duration+"毫秒");
    }

    /**
     * 对元素进行点击操作
     */
    public void tapElement(MobileElement element) {

        driver.driverTapOnElement(element);
//        log.info("[Class-pageBase][Method-LongPressPoint] "+fingers+"根手指对元素"+element.toString()+"进行点击操作，延时"+duration+"毫秒");
    }

    public List<Integer> getElementXY(MobileElement element){
        List<Integer> elementXY = driver.getElementXY(element);
        return elementXY;
    }

    public void getScreenshot(String caseId){

        final String shotPath = System.getProperty("user.dir") + "/shots/" + caseId + "/" + Common.formatDate() + ".jpg";
        try {
            File scrFile = ((TakesScreenshot)driver.driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(shotPath);
            FileUtils.copyFile(scrFile, targetFile);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * 获取多个元素的文本
     * @param elements
     * @return
     */
    public String getText(List<MobileElement> elements){
        String text = null;
        if(elements != null && elements.size() > 0){
            for(MobileElement i : elements){
                text = text + "," +  i.getText();
            }
            text.substring(1);
        }else{
            text = null;
        }

        return text;
    }

    /**
     * 获取单个元素文本
     * @param element
     * @return
     */
    public String getText(MobileElement element){
        String text = null;
        if(element != null){
            text = element.getText();
        }
        return text;
    }


}
