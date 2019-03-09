package main.java.com.yep.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import main.java.com.yep.dao.ArticleInfoMapper;
import main.java.com.yep.dao.MenuInfoMapper;
import main.java.com.yep.pojo.ArticleBean.RowsBean;
import main.java.com.yep.pojo.MenuInfo;
import main.java.com.yep.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    
    @Resource(name="articleDao")
    private ArticleInfoMapper articleInfoMapper;
    
    @Resource(name="menuDao")
    private MenuInfoMapper menuInfoMapper;

    @Override
    public List<RowsBean> getAllArticleInfo() {
        // TODO Auto-generated method stub
        List<RowsBean> resultLsit = articleInfoMapper.getAllArticleInfo();
        List<MenuInfo> infos = menuInfoMapper.getMenuInfo();
        for(RowsBean row : resultLsit){
            for(MenuInfo info : infos){
                   if(row.getArticlecategoryid() == info.getId()){
                       row.setArticleCategoryName(info.getMenuName());
                   }
               }
           }
        
        return resultLsit;
    }

    @Override
    public void saveArticle(RowsBean articleBean) {
        // TODO Auto-generated method stub
        articleInfoMapper.insert(articleBean);
    }
    
    
    
   

}
