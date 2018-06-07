package com.sitech.inter;

import com.sitech.entity.User;

import java.util.List;

public interface UserService {

    public User find(Long id);

    public List<User> findAll(List<Long> ids);
} 