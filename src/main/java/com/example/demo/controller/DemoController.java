package com.example.demo.controller;

import com.example.demo.controller.exception.InvalidUsernameException;
import com.example.demo.domain.LoginInfo;
import com.example.demo.domain.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    private Map<Integer, UserInfo> users = Map.of(
            1, UserInfo.builder().username("Harry").build(),
            2, UserInfo.builder().username("Hermione").build(),
            3, UserInfo.builder().username("Ron").build()
    );

    @PostMapping("user/login")
    @ApiOperation("Authorization")
    public UserInfo login(@RequestBody LoginInfo loginInfo) {
        if (users.values().stream()
                .anyMatch(
                        userInfo -> userInfo.getUsername()
                                .equals(loginInfo.getUsername()))){
            return UserInfo.builder()
                    .loginDate(new Date())
                    .username(loginInfo.getUsername())
                    .build();
        }
        else {
            throw new InvalidUsernameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Get All Users' Info")
    public List<UserInfo> getAllUsers(){
        return new ArrayList<>(users.values());
    }

}
