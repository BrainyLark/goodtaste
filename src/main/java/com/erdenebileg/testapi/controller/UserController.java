package com.erdenebileg.testapi.controller;

import com.erdenebileg.testapi.Entity.User;
import com.erdenebileg.testapi.Entity.request.AddUserRequest;
import com.erdenebileg.testapi.Entity.request.LoginUserRequest;
import com.erdenebileg.testapi.Entity.response.AddUserResponse;
import com.erdenebileg.testapi.Entity.response.LoginUserResponse;
import com.erdenebileg.testapi.repository.UserRepository;
import com.erdenebileg.testapi.utils.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;
    private DatabaseHelper dbhelper;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.dbhelper = new DatabaseHelper();
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public LoginUserResponse findUser(@RequestBody LoginUserRequest req) {
        int returned = dbhelper.getUser(req.getUname(), req.getPasswd());
        LoginUserResponse res = new LoginUserResponse();
        if(returned != -1) {
            res.setSuccess(true);
            res.setId(returned);
        } else {
            res.setSuccess(false);
            res.setId(-1);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public AddUserResponse addUser(@RequestBody AddUserRequest addUserRequest) {
        User user = new User();
        user.setUname(addUserRequest.getUname());
        user.setPasswd(addUserRequest.getPasswd());
        user.setEmail(addUserRequest.getEmail());
        boolean success = dbhelper.addUser(user);
        AddUserResponse res = new AddUserResponse();
        if(success) {
            res.setId(200);
            res.setSuccess(true);
            res.setDescription("Амжилттай бүртгүүллээ!");
        } else {
            res.setId(400);
            res.setSuccess(false);
            res.setDescription("Бүртгэл амжилтгүй. Ийм нэртэй хэрэглэгч байна, өөр нэр сонгоно уу!");
        }
        return res;
    }
}
