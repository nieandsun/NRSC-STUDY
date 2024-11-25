package com.nrsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nrsc.dto.UserInfoDTO;
import com.nrsc.service.UserServiceImpl;
import com.nrsc.starter.HelloService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nrsc", "com.yoyo"})
@RestController
public class NrscSpringBootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(NrscSpringBootStarterTestApplication.class, args);
    }

    @Autowired
    private HelloService helloService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/say/hello/to/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }

    @PostMapping("/save/user")
    public String saveUser(@RequestBody UserInfoDTO userInfoDTO) {
        userService.saveUser(userInfoDTO);
        return "ok";
    }

    @PostMapping("/update/user")
    public String updateUser(@RequestBody UserInfoDTO userInfoDTO) {
        userService.updateUser(userInfoDTO);
        return "ok";
    }
}
