package com.huihong.healthydiet.cache.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by zangyi_shuai_ge on 2017/7/29
 */

public class SearchHistory  extends DataSupport{

    private  String searchHistory;

    public String getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(String searchHistory) {
        this.searchHistory = searchHistory;
    }
}
