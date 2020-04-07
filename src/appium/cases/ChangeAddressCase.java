package appium.cases;

import appium.asserts.Assertion;
import appium.base.DriverBase;
import appium.buss.ChangeAddressBuss;
import appium.utils.Common;
import appium.utils.ReadData;
import org.testng.annotations.*;

import java.util.Map;

/**
 * Created by chenzepeng on 2020/3/20.
 */
@Listeners(appium.listener.AssertListener.class)
public class ChangeAddressCase extends CaseBase{

    private ReadData rd;
    private ChangeAddressBuss cab;
    private DriverBase driver;

    public ChangeAddressCase(){
        rd = new ReadData("datas/changeAddress.properties");
        driver = initDriver();
        cab = new ChangeAddressBuss(driver);
    }

    @BeforeMethod
    public void beforeTestMethod(){
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void AfterTestMethod(){
        System.out.println("AfterMethod");

    }

    @AfterTest
    public void AfterTest(){
        System.out.println("AfterTest");
        driver.close();

    }

    @Test
    public void testChangeAddress(){
        Map<String,String> acture = cab.changeAddress(rd.getTestValue("changeAddress001").get(0));
        String except = rd.getExceptValue("changeAddress001");
        Map<String, String> mExcept = Common.getJsonData(except);
        Assertion.verifyEquals(acture,mExcept);
    }
}
