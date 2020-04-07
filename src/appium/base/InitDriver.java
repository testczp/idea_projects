package appium.base;

import appium.utils.ReadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import javafx.scene.web.WebView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by chenzepeng on 2020/1/17.
 */
public class InitDriver {
    private ReadProperties rw;
    private AndroidDriver<MobileElement> driver;
//    private static final Logger log = LogManager.getLogger(InitDriver.class.getName());

    public InitDriver() {

        rw = new ReadProperties("/Users/chenzepeng/Documents/idea_projects/resources/initDriver.properties");
    }

    /**
     * 返回AndroidDriver
     *
     * @boolean:isAppInstalled false:表示app未安装 true：表示APP已经安装
     */
    public AndroidDriver getDriver(String UUID, int AppiumPort, String os) {

        String IP = rw.getValue("ServerIP");

        File app = null;
        boolean isAppInstalled = true;
        DesiredCapabilities caps = new DesiredCapabilities();

        if(os.equals("app")){
            if(rw.getValue("isAPPInstalled").equalsIgnoreCase("true")){

                isAppInstalled = false;
//            log.info("[Class-initDriver][Method-getDriver] 当前APP已安装");
                //log.info("[Class-initDriver][Method-getDriver] 当前APP已安装");
            }

            //log.info("[Class-initDriver][Method-getDriver] 调用‘initDriver’方法");
//        log.info("[Class-initDriver][Method-getDriver] 调用‘initDriver’方法");
            if (isAppInstalled) {

                app = new File(rw.getValue("APPPath"));
//            log.info("[Class-initDriver][Method-getDriver] 当前APP未安装,APP路径"+rw.getValue("APPPath"));
                //log.info("[Class-initDriver][Method-getDriver] 当前APP未安装,APP路径"+rw.getValue("APPPath"));
            }

            //log.info("[Class-initDriver][Method-getDriver] 创建DesiredCapabilities对象");
//        log.info("[Class-initDriver][Method-getDriver] 创建DesiredCapabilities对象");
            if (isAppInstalled) {

                caps.setCapability(MobileCapabilityType.APP, app.getAbsoluteFile());
//            log.info("[Class-initDriver][Method-getDriver] 正在安装APP,请稍后...");
            }
            // IOS需要填写正确的DeviceName Andriod随便写 但是不能null
//        log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-DeviceName："+rw.getValue("platformName"));
            caps.setCapability("platformName", rw.getValue("platformName"));

//        log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-appPackage"+rw.getValue("appPackage"));
            caps.setCapability("appPackage", rw.getValue("appPackage"));
            // 要启动的应用的起始activity
//        log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-appActivity"+rw.getValue("appActivity"));
            caps.setCapability("appActivity", rw.getValue("appActivity"));
            // resetKeyBoard是执行完测试后将设备的输入法重置回原有的输入法
            //caps.setCapability("unicodeKeyBoard", true);
            //caps.setCapability("resetKeyBoard", true);
            // 不对app进行重签名，因为有的app在重签名之后无法使用
            //caps.setCapability("noSign", true);
            // 设置session的超时时间
//        log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-session超时时间600S");
//        caps.setCapability("newCommandTimeout", 600);
//        log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-udid超时时间-->"+UUID);
            caps.setCapability("deviceName", UUID);
            caps.setCapability("noReset",true);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            //log.info("[Class-initDriver][Method-getDriver] 设置DesiredCapabilities参数-deviceReadyTimeout超时时间60S");
            //caps.setCapability("deviceReadyTimeout",60);
        }else if(os.equals("web")){
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, UUID);
            caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");


        }else if(os.equals("hybrid")){

        }else{
            return null;
        }

        if (rw.getValue("OSType").equalsIgnoreCase("Android")) {

            try {
//                log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver,启动在【"+AppiumPort+"】端口,UUID是：【"+UUID+"】...");
                String url = "http://"+IP+":" + AppiumPort + "/wd/hub";
                driver = new AndroidDriver(new URL(url), caps);

//                WebDriverWait wait = new WebDriverWait(driver,2);
//                Set<String> contextNames = driver.getContextHandles();
//                for (String contextName : contextNames) {
//                    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
//                }

//                WebDriver driver = new RemoteWebDriver(DesiredCapabilities.android());
//                log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver成功...");
            } catch (MalformedURLException e) {
//                log.info("[Class-initDriver][Method-getDriver] 启动Andriod Driver异常...");
                e.printStackTrace();
            }

        }

//		else {
//
//			try {
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver...");
//				driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:" + AppiumPort + "/wd/hub"), caps);
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver成功...");
//			} catch (MalformedURLException e) {
//				log.info("[Class-initDriver][Method-getDriver] 启动IOS Driver异常...");
//				e.printStackTrace();
//			}
//		}
        return driver;
    }
}
