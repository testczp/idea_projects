package appium.buss;

import appium.base.DriverBase;
import appium.page.HomePage;
import appium.page.SearchKeysPage;
import appium.page.SearchResultPage;
import appium.utils.ReadData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by chenzepeng on 2020/1/19.
 */
public class HomeBuss {
    //页面的动作类
    private HomePage hp;
    private SearchKeysPage sp;
    private SearchResultPage srp;
    private ReadData rd;
//    private static final Logger log = LogManager.getLogger(HomeBuss.class.getName());

    public HomeBuss(DriverBase driver) {

        hp = new HomePage(driver);
        sp = new SearchKeysPage(driver);
        rd = new ReadData("datas/homedata.properties");
        srp = new SearchResultPage(driver);
    }

    /**
     * 点击home页面搜索按钮
     */
    public int homeSearch(String key) {
//        Map<Integer, String> jobs = new HashMap<>();
        int count = 0;
        hp.clickSearch();
        String key1 = rd.getTestValue(key).get(0);
        sp.inputSearchKey(key1);
        sp.clickSearchKey(key1);
//        int jobHeight = sp.getElementXY(sp.getJobCard()).get(3) - sp.getElementXY(sp.getJobCard()).get(1);
//        int divideHeight = sp.getElementXY(sp.getJobDivide()).get(3) - sp.getElementXY(sp.getJobDivide()).get(1);
//        int height1 = sp.getViewBottomHight()-jobHeight - divideHeight;
//        int height2 = sp.getSearchTypeBottomHight();
//        count = hp.swipeUp(height1, height2,8);
//        count = hp.getJobs().size();
//        MobileElement searchTxt = sp.getTxtSearch();
        srp.swipeListPage(4);
        count = srp.getAllJobs().size();

        System.out.println("执行完毕！");


        return count;
    }

    public void clickExpectJob(){
        hp.getJobExpect();
        hp.getScreenshot("setJobFilter001");
        hp.clickExpectJob();
    }



}
