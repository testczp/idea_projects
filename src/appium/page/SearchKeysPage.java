package appium.page;

import appium.base.Bys;
import appium.base.DriverBase;
import appium.utils.ReadProperties;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by chenzepeng on 2020/2/15.
 */
public class SearchKeysPage extends PageBase {

//    private static final Logger log = LogManager.getLogger(SearchKeysPage.class.getName());

    private MobileElement txtSearch;
    private MobileElement searchKey;


    private Bys by;//具体的定位操作类
//    private ReadData rd;
    private ReadProperties rp;

    /**
     * 构造方法 初始化DriverBase对象
     *
     * @param driver
     */
    public SearchKeysPage(DriverBase driver) {
        super(driver);
        this.by = new Bys("resources/searchKeysPage.properties");
//        rd = new ReadData("datas/homedata.properties");
    }


    /**
     * 获取搜索文本框对象
     * @return
     */
    public MobileElement getTxtSearch() {

        try{
            this.txtSearch = element(by.by("txtSearch"));
//            log.info("[Class-homePage][Method-isAllowed] note元素为"+txtSearch.toString());
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }

        return txtSearch;
    }

    /**
     * 通过比对首页传入的关键字，返回对应的搜索关键字元素
     * @return null或者对应的元素控件
     */
    public MobileElement getSearchKey(String text){
        List<MobileElement> keys;
        try{
            keys = elements(by.by("searchKey"));
            for(int i = 0; i < keys.size(); i++){
                if(keys.get(i).getText().equals(text)){
                    this.searchKey = keys.get(i);
                    break;
                }
            }
        }catch (Exception ex){
//            log.info("getSearchKey error");
        }
        return searchKey;
    }

    /**
     * 向搜索文本框输入数据
     * @param text
     */
    public void inputSearchKey(String text) {

        try{
            this.txtSearch = element(by.by("txtSearch"));
//            log.info("[Class-homePage][Method-isAllowed] note元素为"+txtSearch.toString());
            inputValue(txtSearch, text);
        }catch (Exception ex){
//            log.info("[Class-homePage][Method-isAllowed] 未定位到元素");
        }
    }

    /**
     * 根据输入的关键字，在匹配列表中点击期望的关键字
     *
     */
    public void clickSearchKey(String text){
        this.getSearchKey(text);
        click(searchKey);
    }








}
