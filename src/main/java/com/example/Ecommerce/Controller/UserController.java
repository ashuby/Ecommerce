package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userServ;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public User save(@RequestBody User user){
        System.out.println("Check");
        return userServ.saveUser(user);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody User user) {
        userServ.deleteUser(user);
    }

    @DeleteMapping("/deletebyid/{id}")
    public void deletebyid(@PathVariable int id) {
        userServ.deleteById(id);
    }

    @GetMapping("/getallusers")
    public List<User> getallUsers() {
        List<User> users = userServ.getallusers();
        return users;
    }
    @PutMapping("/update/")
    public Optional<User> updateUser(@RequestBody User user) {
        Optional<User> updatedUser = userServ.updateuser(user);
        return updatedUser;
    }
    @PostMapping("/exists/email")
    public Boolean emailExists(@RequestBody User user){
        String userEmail = user.getEmail();
        return userServ.existByEmail(userEmail);
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return userServ.userLogin(email, password);
    }

}
