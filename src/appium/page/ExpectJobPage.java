package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import appium.utils.Regular;
import io.appium.java_client.MobileElement;

import java.util.List;

/**
 * Created by chenzepeng on 2020/3/14.
 */
public class ExpectJobPage extends PageBase{

    private Bys by;
    private MobileElement education;
    private MobileElement money;
    private MobileElement experience;
    private MobileElement btnConfirm;
    private String[] m;

    public String[] getMin(){
        return m;
    }

    public MobileElement getEducation() {
        try{
            this.education = element(by.by("education"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return education;
    }

    public void setEducation(MobileElement education) {
        this.education = education;
    }

    public void setMoney() {
        try{
            this.money = element(by.by("money"));
            List<String> tmp = Regular.matchGroupByPattern("(\\d+)-(\\d+).+", money.getText());
            this.m = tmp.toArray(new String[tmp.size()]);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
//        return money;
    }

    public MobileElement getMoney() {
        return this.money;
    }

    public MobileElement getExperience() {
        try{
            this.experience = elementByUiautomator(by.uiautomatorBy("experience"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return experience;
    }

    public void setExperience(MobileElement experience) {
        this.experience = experience;
    }

    public MobileElement getBtnConfirm() {
        try{
            this.btnConfirm = element(by.by("btnConfirm"));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return education;
    }

    /**
     * 构造方法 初始化DriverBase对象
     *
     * @param driver
     */
    public ExpectJobPage(DriverBase driver) {
        super(driver);
        by = new Bys("resources/expectJob.properties");
    }

    public void clickEducation(){
        try{
            this.getEducation();
            this.education.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickMoney(){
        try{
            this.setMoney();
            this.money.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickExperience(){
        try{
            this.getExperience();
            this.experience.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void clickBtnConfirm(){
        try{
            this.getBtnConfirm();
            this.btnConfirm.click();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }




}
