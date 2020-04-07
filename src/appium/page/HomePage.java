package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzepeng on 2020/1/19.
 */
public class HomePage extends PageBase {

//    private static final Logger log = LogManager.getLogger(HomePage.class.getName());


    private MobileElement btnSearch = null;
    private Bys by;//具体的定位操作类
    private MobileElement nearby;
    private MobileElement address;
    private List<MobileElement> jobName;
    private List<MobileElement> hr;
    private List<MobileElement> distance;
    private List<MobileElement> company;
    private List<MobileElement> salarys;
    private List<MobileElement> jobs;
    private MobileElement jobExpect;
    private MobileElement divide;
    private MobileElement filter;
    private MobileElement displayPanel;

    public List<MobileElement> getJobs() {
        try{
            jobs = elementsByUiautomator(by.uiautomatorBy("jobs"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return jobs;
    }

    public MobileElement getFilter(){
        try{
            filter = elementByUiautomator(by.uiautomatorBy("filter"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return filter;
    }

    public MobileElement getDisplayPanel(){
        try{
            displayPanel = elementByUiautomator(by.uiautomatorBy("displayPanel"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return displayPanel;
    }

    public MobileElement getDivide() {
        try{
            divide = elementByUiautomator(by.uiautomatorBy("divide"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return divide;
    }

    public String getJobNameText(){

        getJobName();
        String texts = this.getText(jobName);
        return texts;
    }

    public String getHrText(){

        getHr();
        String texts = this.getText(hr);
        return texts;
    }

    public String getCompanyText(){

        getCompany();
        String texts = this.getText(company);
        return texts;
    }

    public String getDistanceText(){

        getDistance();
        String texts = this.getText(distance);
        return texts;
    }

    public List<MobileElement> getJobName() {
        try{
            jobName = elementsByUiautomator(by.uiautomatorBy("jobName"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return jobName;
    }

    public List<MobileElement> getHr() {
        try{
            hr = elementsByUiautomator(by.uiautomatorBy("hr"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return hr;
    }

    public List<MobileElement> getCompany() {
        try{
            company = elementsByUiautomator(by.uiautomatorBy("company"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return company;
    }

    public List<MobileElement> getDistance() {
        try{
            distance = elementsByUiautomator(by.uiautomatorBy("distance"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return distance;
    }

    public List<MobileElement> getSalarys() {
        try{
            salarys = elements(by.by("salary"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return salarys;
    }

    public MobileElement getNearby() {
        try{
            nearby = elementByUiautomator(by.uiautomatorBy("nearby"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return nearby;
    }

    public MobileElement getAddress() {
        try{
            address = elementsByUiautomator(by.uiautomatorBy("address")).get(2);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return address;
    }

    public MobileElement getJobExpect() {
        try{
            jobExpect = element(by.by("expectJob"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return jobExpect;
    }

    public void setJobExpect(MobileElement jobExpect) {
        this.jobExpect = jobExpect;
    }

    /**
     * 点击主页的筛选按钮
     */
    public void clickExpectJob(){
        try{
            this.jobExpect.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public void clickNearby(){
        try{
            getNearby();
            this.nearby.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickAddress(){
        try{
            getAddress();
            this.address.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    public HomePage(DriverBase driver) {
        super(driver);
        this.by = new Bys("resources/homePage.properties");
    }

    /**
     * 进入Home页面获取搜索控件
     * */
    public MobileElement btnSearch(){
        try{

            btnSearch = elements(by.by("search")).get(1);
//            log.info("[Class-homePage][Method-isAllowed] note元素为"+btnSearch.toString());

        }catch(Exception e){

//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }

        return btnSearch;
    }

    /**
     * 点击"搜索"控件
     */
    public void clickSearch() {

        this.btnSearch();

        if (this.btnSearch == null) {

//            log.info("[Class-HomeHandle][Method-clickAllowed] isAllowed元素为空");

        } else {

            this.click(btnSearch);
        }
    }

    public Map<String,String> scroll(int count, String[] keys){
        Map<String,String> result = new HashMap<>();
        getFilter();
        getDisplayPanel();
        int t1 = filter.getRect().getY();
        int t2 = filter.getRect().getHeight();
        int endy = filter.getRect().getY() + filter.getRect().getHeight();
        double displayHeight = displayPanel.getRect().getHeight();
        double jobHeight = getJobs().get(0).getRect().getHeight();
//        double divideHeight = getDivide().getRect().getHeight();
        double tmp = displayHeight / jobHeight;
        int maxCount = new Double(Math.ceil(tmp)).intValue();

        result = driverScroll(endy,maxCount,count,keys,0,"resources/homePage.properties");

        return result;
    }


}

