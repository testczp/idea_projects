package appium.cases;

import appium.asserts.Assertion;
import appium.base.DriverBase;
import appium.buss.ExpectJobBuss;
import appium.buss.HomeBuss;
import appium.utils.ReadData;
import org.apache.logging.log4j.core.util.FileUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by chenzepeng on 2020/3/14.
 */
@Listeners(appium.listener.AssertListener.class)
public class ExpectJobCase {

    private DriverBase driver;
    private HomeBuss hb;
    private ExpectJobBuss ejb;
    private ReadData rd;

    public ExpectJobCase(){
        driver = new DriverBase();
        hb = new HomeBuss(driver);
        ejb = new ExpectJobBuss(driver);
        rd = new ReadData("datas/expectJobCase.properties");
    }

    @BeforeTest
    public void beforeTest(){
        hb.clickExpectJob();
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

    @Test
    public void addExpectJob(){
        int result = ejb.filterJob();
        int expect = Integer.parseInt(rd.getExceptValue("setJobFilter001"));
        Assertion.verifyEquals(result,expect);


    }
}
