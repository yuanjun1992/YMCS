package main.java.com.yep.dao;

import main.java.com.yep.pojo.UserInfo;

import org.springframework.stereotype.Repository;


@Repository("userdao")
public interface userdao {
    UserInfo findbyname(String principal);
    
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}
