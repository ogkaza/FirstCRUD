package com.example.TestTask.service;

import com.example.TestTask.entity.UserEntity;
import com.example.TestTask.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserService {
    @Autowired
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public void saveUser(UserEntity userEntity){
        userEntity.setPassword(encoder().encode(userEntity.getPassword()));
        userRepo.save(userEntity);
    }

    public void deleteUser(@RequestParam Long id){
        try{
            UserEntity userEntity = userRepo.findById(id).get();
            userRepo.delete(userEntity);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public UserEntity updateUser(@RequestParam Long id, @RequestBody UserEntity userEntity) {
        UserEntity user = userRepo.findById(id).get();
        user.setName(userEntity.getName());
        user.setPassword(encoder().encode(userEntity.getPassword()));
        return userRepo.save(user);
    }

    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    public Optional<UserEntity> findById(Long id){
        Optional<UserEntity> userEntity = userRepo.findById(id);
        return userEntity;
    }
}
