package cn.ly.userconsumer.controller;

import cn.ly.userconsumer.pojo.User;
import cn.ly.userconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consume")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> consume(@RequestParam("ids") List<Long> ids )throws Exception{
        return userService.queryUserByids(ids);
    }
}
