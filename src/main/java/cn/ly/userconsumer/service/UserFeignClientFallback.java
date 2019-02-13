package cn.ly.userconsumer.service;


import cn.ly.userconsumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User queryUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常");
        return user;
    }
}
