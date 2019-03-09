package main.java.com.yep.service.impl;




import main.java.com.yep.dao.IUserDao;
import main.java.com.yep.pojo.User;
import main.java.com.yep.service.UserService;

import org.springframework.stereotype.Service;
 








import javax.annotation.Resource;

import java.util.List;
 
@Service("userService")
public class UserServiceImpl implements UserService{
 
    @Resource(name = "userDao")
    private IUserDao userDao;
 
    @Override
    public List<User> getUser() {
        return userDao.selectAllUser();
    }
}

