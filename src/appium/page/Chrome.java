package appium.page;

import appium.base.DriverBase;
import appium.utils.ZoomUtil;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.Setting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.imagecomparison.*;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.TimeUnit;


/**
 * Created by chenzepeng on 2020/3/28.
 */
public class Chrome{

    private DriverBase driver;

    public Chrome(){

        driver = new DriverBase();
    }

    public void testWeb(){

        driver.driver.manage().timeouts().implicitlyWait(Integer.parseInt("15"), TimeUnit.SECONDS);

//        MobileElement bn = (MobileElement) driver.driver.findElementByXPath("//android.widget.TextView[contains(@text,'若泉膜力泉')]" +
//                "/../../android.widget.ImageView");
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
//        singleTap(bn);
        MobileElement t = (MobileElement)driver.driver.findElementByXPath("//*[@text='李敏君']/../android.widget.ImageView");
        t.click();
        MobileElement t2 = (MobileElement)driver.driver.findElementByXPath("//android.view.View[contains(@text,'封面')]");
        t2.click();
        MobileElement t3 = (MobileElement)driver.driver.findElementByXPath("//*[@resource-id='com.dark.focus:id/id_gridView']" +
                "/android.widget.RelativeLayout[2]/android.widget.ImageView");
        t3.click();
//        zoomInOrOut();
        try {
            new ZoomUtil().ZoomInAndOut(driver.driver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(t.getText());
//        longPress(t);
//        moveByTap(300, 1400, 300, 200);
//        byte[] screenshot = encoder.encode(driver.driver.getScreenshotAs(OutputType.BYTES));
//        File f = new File("/Users/chenzepeng/Documents/老文件/output2.jpg");

//        byte[] originalImg = new byte[0];
//        try {
//            originalImg = encoder.encode(Files.readAllBytes(f.toPath()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String result = driver.driver.executeScript("mobile:batteryInfo").toString();
//        driver.driver.executeScript("mobile:viewportScreenshot");
//        driver.driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, Duration.ofSeconds(5));
//        System.out.println("开始等待。。。。");
//        String pkg = driver.driver.getCurrentPackage();
//        String activity = driver.driver.currentActivity();
//        System.out.println(pkg + "--" + activity);
//        driver.driver.resetApp();
//        driver.driver.rotate(new DeviceRotation(10, 10, 10));

//        driver.driver.pressKey(new KeyEvent().withKey(AndroidKey.APP_SWITCH));
//        driver.driver.toggleAirplaneMode();
//        driver.driver.lockDevice();
//        driver.driver.toggleData();
//        String time = driver.driver.getDeviceTime();
//        long density = driver.driver.getDisplayDensity();
//        String id = "android:id/header_text";
//        clickNotification(id, 3);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.driver.unlockDevice();
//        driver.driver.closeApp();
//        driver.driver.terminateApp("com.hpbr.bosszhipin");
        driver.close();

    }

    public static void main(String[] args){
        Chrome c = new Chrome();
        c.testWeb();
        System.out.println(System.getProperty("user.dir"));
    }

    /**
     * 放大缩小图片
     */
    public void zoomInOrOut(){
        MultiTouchAction actions = new MultiTouchAction(driver.driver);
        TouchAction action1 = new TouchAction(driver.driver);
//        TouchAction action2 = new TouchAction(driver.driver);
        action1.press(PointOption.point(357,836)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
                .moveTo(PointOption.point(500,419)).release();

//        action2.press(PointOption.point(644,1296)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000)))
//                .moveTo(PointOption.point(875,1616)).release();
//        actions.add(action1).add(action2).perform();

    }

    /**
     * 实现长按
     * @param
     */
    public void longPress(MobileElement m){
        TouchAction action = new TouchAction(driver.driver);
        int x = m.getCenter().getX() / 2;
        int y = m.getCenter().getY();
//        action.longPress(new LongPressOptions().withDuration(Duration.ofSeconds(5)).withPosition(PointOption.point(x,y)));
        action.longPress(PointOption.point(x,y));
        action.perform();
    }

    /**
     * 根据坐标点击
     * @param m
     */
    public void singleTap(MobileElement m){
        TouchAction action = new TouchAction(driver.driver);
        int x = m.getCenter().getX();
        int y = m.getCenter().getY();
        action.tap(PointOption.point(x, y));
        action.perform();
    }

    /**
     * 等待推送过来后，点击查看推送内容
     * @param id
     * @param duration
     */
    public void clickNotification(String id, int duration){

        WebDriverWait wait = new WebDriverWait(driver.driver,duration);
        driver.driver.openNotifications();
        if(wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id))) != null){

            MobileElement m = (MobileElement)driver.driver.findElementsById(id).get(0);
            System.out.println(m.getText());
            m.click();
            MobileElement me2 = (MobileElement)driver.driver.findElementsById("android:id/text").get(0);
            me2.click();
        }else{
            System.out.println("There aren't the elements on screen.");
        }

    }

    /**
     * 上滑页面直到底部
     */
    public void swipup(){
        int width = driver.driver.manage().window().getSize().getWidth();
        int height = driver.driver.manage().window().getSize().getHeight();

        String before = "";
        String after = "";
        while(before.equals(after) == false || (before.equals("") && after.equals(before))){
            before = driver.driver.getPageSource();
            driver.driverSwipe(width / 2, height / 5 * 3, width / 2, height / 5 * 1);
            after = driver.driver.getPageSource();
        }
    }

    /**
     * 上滑操作
     */
    public void up(){
        int width = driver.driver.manage().window().getSize().getWidth();
        int height = driver.driver.manage().window().getSize().getHeight();

        driver.driverSwipe(width / 2, height / 5 * 3, width / 2, 100);
    }
    /**
     * 验证toast提示
     * @param key
     * @return
     */
    public boolean testToast(String key){
        boolean isPass = false;
        WebDriverWait wait = new WebDriverWait(driver.driver,5);
        try{
            if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'"+ key + "')]"))) != null){
                System.out.println("捕获成功！");
                isPass = true;
            }
        }catch (Exception ex){
            System.out.println("捕获失败！");
        }
        return isPass;
    }

    /**
     * 比较两张图片的相似程度，可以通过getScore方法获取相似分数（0.0-1.0）
     * @param screenshot
     * @param originalImg
     * @return
     */
    public SimilarityMatchingResult getScoreOnImages(byte[] screenshot, byte[] originalImg){
        SimilarityMatchingResult result = null;
        result = driver.driver.getImagesSimilarity(screenshot, originalImg, new SimilarityMatchingOptions()
                .withEnabledVisualization());
        return result;
    }

    /**
     * 判断两张图片是否有相似区域,无相似区域则返回null（未添加异常处理则直接抛出异常）；
     * @param screenshot
     * @param originalImg
     * @return
     */
    public OccurrenceMatchingResult getSameOnImages(byte[] screenshot, byte[] originalImg){
        OccurrenceMatchingResult result = null;
        try{
            result = driver.driver.findImageOccurrence(screenshot, originalImg, new OccurrenceMatchingOptions()
                    .withEnabledVisualization());
        }catch (Exception ex){
            return null;
        }
        return result;
    }

    /**
     * 比较两张图片是否相同
     * @param screenshot
     * @param originalImg
     * @return
     */
    public FeaturesMatchingResult imagesCompare(byte[] screenshot, byte[] originalImg, int factor){
        FeaturesMatchingResult result = driver.driver
                .matchImagesFeatures(screenshot, originalImg, new FeaturesMatchingOptions()
                        .withDetectorName(FeatureDetector.ORB)
                        .withGoodMatchesFactor(factor)
                        .withMatchFunc(MatchingFunction.BRUTE_FORCE_HAMMING)
                        .withEnabledVisualization());
        return result;
    }

    /**
     * 传入一个控件并在当前页面截取该控件
     * @param element
     * @return
     */
    public boolean getElementPicture(MobileElement element){

        boolean isSuccess = false;
        int x = element.getRect().getX();
        int y = element.getRect().getY();
        int height = element.getRect().getHeight();
        int width = element.getRect().getWidth();
        BufferedImage img = null;
        File picture = ((TakesScreenshot)driver.driver).getScreenshotAs(OutputType.FILE);
        try {
            img = ImageIO.read(picture);
            BufferedImage elePicture = img.getSubimage(x,y,width,height);
            ImageIO.write(elePicture,"png",picture);
            FileUtils.copyFile(picture,new File("/Users/chenzepeng/Documents/老文件/output.jpg"));
            isSuccess = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * 图片转化为字节
     * @param path
     * @return
     */
    public byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * 字节转化为图片
     * @param data
     * @param path
     */
    public void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    /**
     * 图片转化为字节
     * @param img
     * @return
     * @throws Exception
     */
    public byte[] fileToByte(File img) throws Exception {
        byte[] bytes = new byte[0];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
            System.err.println(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    /**
     * 字节转化为图片
     * @param bytes
     * @param path
     * @throws Exception
     */
    public void ByteToFile(byte[] bytes, String path)throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            File w2 = new File(path);//可以是jpg,png,gif格式
            ImageIO.write(bi1, "png", w2);//不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }
}
