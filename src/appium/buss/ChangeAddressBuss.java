package appium.buss;

import appium.base.DriverBase;
import appium.page.ChangeAddressPage;
import appium.page.HomePage;
import appium.utils.ReadData;
import io.appium.java_client.MobileElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzepeng on 2020/3/20.
 */
public class ChangeAddressBuss {

    public ChangeAddressBuss(DriverBase driverBase){
        cap = new ChangeAddressPage(driverBase);
        hp = new HomePage(driverBase);

    }

    private ChangeAddressPage cap;
    private HomePage hp;

    public Map<String, String> changeAddress(String text){
        hp.clickNearby();
//        hp.getScreenshot("changeAddress001");
        hp.clickAddress();
//        hp.getScreenshot("changeAddress001");
        cap.inputAddress(text);
//        cap.getScreenshot("changeAddress001");
        cap.clickKey();
//        cap.getScreenshot("changeAddress001");
        cap.clickOk();
//        String names = hp.getJobNameText();
//        String hrs = hp.getHrText();
//        String companys = hp.getCompanyText();
//        String distances = hp.getDistanceText();
        String[] keys = new String[]{"jobName","hr","company","distance"};
        Map<String,String> tmp = hp.scroll(2, keys);
        Map<String, String> results = new HashMap<>();
        if(tmp != null){

            results.put("names",tmp.get("jobName"));
            results.put("hrs",tmp.get("hr"));
            results.put("companys",tmp.get("company"));
            results.put("distances",tmp.get("distance"));
        }else{
            return null;
        }

//        cap.getScreenshot("changeAddress001");
//        hp.scrollByUiautomator(2);
        return results;
    }
}
