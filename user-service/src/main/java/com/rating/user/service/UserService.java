package com.rating.user.service;

import com.rating.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUser(String id);
}
