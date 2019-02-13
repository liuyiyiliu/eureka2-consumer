package cn.ly.userconsumer.service;

import cn.ly.userconsumer.Dao.UserDao;
import cn.ly.userconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserFeignClient client;
   // private UserDao userDao;

    public List<User> queryUserByids(List<Long> ids){
        List<User> users = new ArrayList<>();
//        for (Long id : ids){
//            User user = this.userDao.queryByid(id);
//            users.add(user);
//        }
        // 我们测试多次查询，
        ids.forEach(id ->users.add(this.client.queryUserById(id)));
        return  users;
    }
}
