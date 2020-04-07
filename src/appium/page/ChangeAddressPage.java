package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import appium.utils.ReadProperties;
import io.appium.java_client.MobileElement;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by chenzepeng on 2020/3/19.
 */
public class ChangeAddressPage extends PageBase{

    private Bys by;
    private MobileElement address;
    private MobileElement key;
    private MobileElement ok;



    /**
     * 构造方法 初始化DriverBase对象
     *
     * @param driver
     */
    public ChangeAddressPage(DriverBase driver) {
        super(driver);
        by = new Bys("resources/changeAddress.properties");


    }

    public MobileElement getAddress(){
        try{
            address = elementByUiautomator(by.uiautomatorBy("address"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return address;
    }



    public MobileElement getKey(){
        try{
            key = elementByUiautomator(by.uiautomatorBy("key"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return key;
    }

    public MobileElement getOk(){
        try{
            ok = elementByUiautomator(by.uiautomatorBy("ok"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return ok;
    }

    public void inputAddress(String text){
        try{
            getAddress();
            address.click();
            address.setValue(text);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickKey(){
        try{
            getKey();
            System.out.println(key.getText() + "-" + key.getAttribute("classname"));
//            tapElement(key);
            key.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickOk(){
        try{
            getOk();
            ok.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}
