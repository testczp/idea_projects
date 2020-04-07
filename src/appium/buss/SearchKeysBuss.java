package appium.buss;

import appium.page.SearchKeysPage;

/**
 * Created by chenzepeng on 2020/2/28.
 */
public class SearchKeysBuss {

    private SearchKeysPage skp;

    public void searchBykey(String key){
        skp.inputSearchKey(key);
        skp.clickSearchKey(key);

    }
}
