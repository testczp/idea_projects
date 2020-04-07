package appium.asserts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzepeng on 2020/1/19.
 */
@Listeners(appium.listener.AssertListener.class)
public class Assertion {
//    private static final Logger log = LogManager.getLogger(Assertion.class.getName());

    public static boolean flag = true;

    public static List<Error> errors = new ArrayList<Error>();
    /**
     * 断言实际与预期是否相等
     * */
    public static void verifyEquals(Object actual, Object expected){

        try{
            Assert.assertEquals(actual, expected);
            System.out.println("实际数据为" + actual + ",期望数据为" + expected);
//            log.info("[Class-Assertion][Method-verifyEquals] 实际数据："+actual.toString()+" 与预期数据："+expected.toString()+"相等");

        }catch(Error e){

//            log.error("[Class-Assertion][Method-verifyEquals] 实际与预期比不相等。实际为" + actual + "，预期为" + expected);
            System.out.println("实际与预期比不相等。实际为" + actual + "，预期为" + expected);
            errors.add(e);
            flag = false;
        }
    }
    /**
     * 断言实际与预期是否相等 断言失败并输出message信息
     * */
    public static void verifyEquals(Object actual, Object expected, String message){
        try{
            Assert.assertEquals(actual, expected, message);
//            log.info("[Class-Assertion][Method-verifyEquals] 实际数据："+actual.toString()+" 与预期数据："+expected.toString()+"相等");

        }catch(Error e){
//            log.error("[Class-Assertion][Method-verifyEquals] 实际与预期比不相等，输出信息："+message);
            errors.add(e);
            flag = false;
        }
    }
    /**
     * 断言实际与预期是否为null
     * */
    public static void verifyNulls(boolean actual, boolean expected){

        try{

            Assert.assertEquals(actual, expected);
//            log.info("[Class-Assertion][Method-verifyNulls] 实际数据："+actual+" 与预期数据："+expected+"相等");

        }catch(Error e){
//            log.error("[Class-Assertion][Method-verifyNulls] 实际与预期比不相等");
            errors.add(e);
            flag = false;
        }
    }
    /**
     * 断言实际与预期是否为null，断言失败 输出message信息
     * */
    public static void verifyNulls(boolean actual, boolean expected , String msg){

        try{

            Assert.assertEquals(actual, expected, msg);
//            log.info("[Class-Assertion][Method-verifyNulls] 实际数据："+actual+" 与预期数据："+expected+"相等");

        }catch(Error e){

//            log.error("[Class-Assertion][Method-verifyNulls] 实际与预期比不相等，输出信息："+msg);
            errors.add(e);
            flag = false;
        }
    }

    /**
     * 断言实际String是否包含与预期String
     * */
    public static void verifyActualCotainers(String actual, String expected){

        boolean flag = false;

        if(expected.contains(actual)){

            flag = true;
        }

        try{

            Assert.assertEquals(flag, true);
//            log.info("[Class-Assertion][Method-verifyActualCotainers] 实际数据："+flag+" 与预期数据：true相等");

        }catch(Error e){

//            log.error("[Class-Assertion][Method-verifyActualCotainers] 实际与预期比不相等，输出信息：");
            errors.add(e);
            flag = false;
        }
    }
    /**
     * 断言实际String是否包含与预期String
     * */
    public static void verifyExpectedCotainers(String actual, String expected){

        boolean flag = false;

        if(actual.contains(expected)){

            flag = true;
        }
        try{

            Assert.assertEquals(true, flag);
//            log.info("[Class-Assertion][Method-verifyActualCotainers] 实际数据包含了预期数据 断言成功");

        }catch(Error e){

//            log.error("[Class-Assertion][Method-verifyActualCotainers] 实际数据没有包含预期数据 断言失败");
            errors.add(e);
            flag = false;
        }
    }
}
