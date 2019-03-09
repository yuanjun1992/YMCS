package main.java.com.yep.service;

import java.util.List;

import main.java.com.yep.pojo.ArticleBean.RowsBean;

public interface ArticleService {
    public List<RowsBean> getAllArticleInfo();
    
    public void saveArticle(RowsBean articleBean);
}
