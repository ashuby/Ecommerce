package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

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
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userServ.updateuser(user);
        return updatedUser;
    }
}
