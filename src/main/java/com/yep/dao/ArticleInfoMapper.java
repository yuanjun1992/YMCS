package main.java.com.yep.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.com.yep.pojo.ArticleBean.RowsBean;;


@Repository("articleDao")
public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RowsBean record);

    int insertSelective(RowsBean record);

    RowsBean selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RowsBean record);

    int updateByPrimaryKeyWithBLOBs(RowsBean record);

    int updateByPrimaryKey(RowsBean record);
    
    List<RowsBean> getAllArticleInfo();
}