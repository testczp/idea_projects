package appium.laucher;

import org.testng.TestNG;
import org.testng.reporters.SuiteHTMLReporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzepeng on 2020/3/15.
 */
public class Laucher {

    public static void main(String[] aregs){
//        HomeTestCase h = new HomeTestCase();

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("testng/testng.xml");
        //suites.add(".\\test-output\\testng-failed.xml");
        testNG.setTestSuites(suites);

        testNG.run();

    }
}
