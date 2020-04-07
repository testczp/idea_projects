package appium.buss;

import appium.base.DriverBase;
import appium.page.ExpectJobPage;
import appium.page.HomePage;
import appium.utils.Common;
import appium.utils.Regular;
import io.appium.java_client.MobileElement;

import java.util.List;

/**
 * Created by chenzepeng on 2020/3/14.
 */
public class ExpectJobBuss {

    private ExpectJobPage eJP;
    private HomePage hp;

    public ExpectJobBuss(DriverBase driver){
        eJP = new ExpectJobPage(driver);
        hp = new HomePage(driver);
    }

    public int filterJob(){

        int count = 0;
        eJP.clickEducation();
        eJP.clickMoney();
        eJP.clickExperience();
        eJP.getScreenshot("setJobFilter001");
        eJP.clickBtnConfirm();
        List<MobileElement> list = hp.getSalarys();
        String expression = "(\\d+)-(\\d+).+";
        List<String> range;
        String[] m1 = eJP.getMin();
        String[] tmp;
        if(list.size() > 0){
            for(MobileElement m : list){
                range = Regular.matchGroupByPattern(expression, m.getText());
                if(range != null){

                    if(range.size() == 1){
                        range.add(range.get(0));
                    }
                    try {
                        tmp = range.toArray(new String[range.size()]);
                        if(Common.isInRange(m1, tmp)){
                            count++;
                        }
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
        eJP.getScreenshot("setJobFilter001");

        return count;
    }
}
