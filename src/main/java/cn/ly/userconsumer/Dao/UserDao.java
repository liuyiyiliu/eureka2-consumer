package cn.ly.userconsumer.Dao;


import cn.ly.userconsumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@Component
public class UserDao {

    @Autowired
    private RestTemplate restTemplate;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserDao.class);
    // @Autowired
    // private DiscoveryClient client;

    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public User queryByid(Long id) {
        // List<ServiceInstance> instances = client.getInstances("user-service");
        //如果是一个服务名称 多个tomcat 使用逻辑算方法(随机数和取余)
        // ServiceInstance serviceInstance = instances.get(0);

        //String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/"+id;

        long begin = System.currentTimeMillis();
        String url = "http://user-service/user/" + id;
        User user = this.restTemplate.getForObject(url, User.class);
        long end = System.currentTimeMillis();

        //记录访问用时
        logger.info("访问用时:{}"+ (end - begin));
        return user;
    }

    public User queryUserByIdFallback(Long id){
        User user = new User();
        user.setId(id);
        user.setName("用户信息查询出现异常！");
        return user;
    }
}
