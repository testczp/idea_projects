package appium.cases;

import appium.asserts.Assertion;
import appium.base.DriverBase;
import appium.buss.HomeBuss;
import appium.utils.ReadData;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzepeng on 2020/1/19.
 */
@Listeners(appium.listener.AssertListener.class)
public class HomeTestCase extends CaseBase {

    private DriverBase driver;
    private HomeBuss hb;
    private ReadData rd;

    public HomeTestCase() {

        this.driver = initDriver();
        hb = new HomeBuss(driver);
        rd = new ReadData("datas/homedata.properties");

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
    public void SearchTest() {

        //返回top10的话题列表
        int count = hb.homeSearch("homeSearch001");
//        Assert.assertEquals(searchTips,"请输入职位或公司1");
        int expected = Integer.parseInt(rd.getExceptValue("homeSearch001"));
        Assertion.verifyEquals(count,expected);
//        if(Assertion.flag == false){
//            System.out.println("用例验证不通过，期望值为请输入职位或公司，实际值为" + searchTips);
//        }

    }


}
