package com.example.Ecommerce.Services;

import com.example.Ecommerce.Model.User;
import com.example.Ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public User updateuser(User user) {
        User updatedUser = userRepo.saveAndFlush(user);
        return updatedUser;
    }
}
