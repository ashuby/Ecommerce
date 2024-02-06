package com.example.Ecommerce.Services;

import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public void deleteUser(User user) { userRepo.delete(user);}
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }
    public List<User> getallusers() {
        return userRepo.findAll();
    }
    public Optional<User> updateuser(User user) {
        Optional<User> updatedUser = Optional.of(userRepo.saveAndFlush(user));
        int id = user.getId();
        if(existbyid(id)) return updatedUser;
        return null;
    }
    public boolean existbyid(int id) {
        boolean existornot = userRepo.existsById(id);
        return existornot;
    }
    public String userLogin(String email, String password ) {
        boolean foundUsers = existByEmail(email);
        if(foundUsers) {
            User user = userRepo.getUserByUsername(email);
            if(user.getPassword().equals(password)) {
                return "{" +
                        "\"message\":"+"Successfully logged in\",\n"+
                        "\"data\":"+user+",\n"+
                        "}";
            }
        }
       return "{" +
                "\"message\":"+"Authentication Failed\",\n"+
                "}";
    }
    public Boolean existByEmail(String email){
        Optional<User> usersObj = Optional.ofNullable(userRepo.getUserByUsername(email));
//        System.out.println(usersObj);
        if(!usersObj.isEmpty()){
            return true;
        }
        return false;
    }
}
