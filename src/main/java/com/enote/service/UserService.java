package com.enote.service;

import com.enote.entity.User;

public interface UserService {

   public User saveUser(User user);

   public boolean existEmailCheck(String email);

   public void removeSessionMsg();
}
