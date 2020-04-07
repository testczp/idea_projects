package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by chenzepeng on 2020/3/8.
 */
public class SearchResultPage extends PageBase {

//    private static final Logger log = LogManager.getLogger(SearchResultPage.class.getName());

    public SearchResultPage(DriverBase driver){
        super(driver);
        this.by = new Bys("resources/searchResultPage.properties");
    }

    private List<MobileElement> jobs;
    private List<MobileElement> allJobs;
    private Bys by;
    private MobileElement jobCard;
    private MobileElement jobDivide;
    private MobileElement searchView;
    private MobileElement searchType;

    public List<MobileElement> getAllJobs() {
        return allJobs;
    }

    public MobileElement getJobCard(){
        try{
            jobCard = elements(by.by("jobCard")).get(0);
        }catch (Exception ex){

        }
        return jobCard;
    }

    public MobileElement getJobDivide(){
        try{
            jobDivide = elements(by.by("jobDivide")).get(0);
        }catch (Exception ex){

        }
        return jobDivide;
    }

    public MobileElement getSearchView(){
        try{
            this.searchView = element(by.by("searchView"));
//            for(MobileElement m : list){
//                System.out.println(m.getSize().getHeight());
//            }
//            this.searchView = list.get(list.size() - 1);
//            log.info("[Class-homePage][Method-isAllowed] note元素为"+searchView.toString());
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }

        return searchView;
    }

    public MobileElement getSearchType(){
        try{
            this.searchType = element(by.by("searchType"));
//            log.info("[Class-homePage][Method-isAllowed] note元素为"+searchType.toString());
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }

        return searchType;
    }

    /**
     * 获取主页搜索结果列表的职位
     * @return null or job object
     */
    public List<MobileElement> getJobs(){

        try{
            this.jobs = elements(by.by("jobs"));
//            log.info("[Class-homePage][Method-top10TopicsNo] jobs元素为"+jobs.toString());
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-top10TopicsNo] jobs未定位到元素");
        }
        return jobs;

    }

    public int getViewBottomHight(){
        int bottomHight = 0;
        bottomHight = this.getElementXY(this.getSearchView()).get(3);
        return bottomHight;
    }

    public int getSearchTypeBottomHight(){

        int bottomHight = 0;
        bottomHight = this.getElementXY(this.getSearchType()).get(3);
        return bottomHight;

    }

    public void swipeListPage(int count){

        int starty = this.getSearchType().getLocation().getY() + this.getSearchType().getRect().getHeight();
        int divideHeight = this.getJobDivide().getRect().getHeight();
        int maxCount = this.getListCountByPage();
        if(count > 0){
            allJobs = this.driverSwipeList(starty, maxCount, count, by.by("jobCard"),divideHeight);
        }else{
            System.out.println("空列表无法滑动！");
        }
    }

    public int getListCountByPage(){
        int count = 0;
        MobileElement m = this.getJobCard();
        if(m != null){
            int listBottomHeight = this.getViewBottomHight();
            int listY = this.getSearchView().getLocation().getY();
            int listHeight = listBottomHeight - listY + 1;
            int jcHeight = m.getRect().getHeight();
            int divideHeight = this.getJobDivide().getRect().getHeight();
            int singleHeight = jcHeight + divideHeight;
            double t = (double) listHeight / singleHeight;
            double t1 = Math.ceil(t);
            count = new Double(t1).intValue();
        }
        return count;
    }
}
