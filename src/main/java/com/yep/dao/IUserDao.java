package main.java.com.yep.dao;


import main.java.com.yep.pojo.User;

import org.springframework.stereotype.Repository;
 



import java.util.List;
 
@Repository("userDao")
public interface IUserDao {
    int deleteByPrimaryKey(Integer id);
 
    int insert(User record);
 
    int insertSelective(User record);
 
    User selectByPrimaryKey(Integer id);
 
    int updateByPrimaryKeySelective(User record);
 
    int updateByPrimaryKey(User record);
    //自己添加的，已匹配Mapper中的Sql
    List<User> selectAllUser();
}

