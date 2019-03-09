package main.java.com.yep.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import main.java.com.yep.pojo.MenuInfo;


@Repository("menuDao")
public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);
    
    List<HashMap<Object, Object>> selectAllMenuInfo();
    
    List<MenuInfo> getMenuInfo();
}