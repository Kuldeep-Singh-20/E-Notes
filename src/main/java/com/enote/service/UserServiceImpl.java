package com.enote.service;

import com.enote.entity.User;
import com.enote.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder PasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
      User newUser =  userRepository.save(user);
        return newUser;
    }

    @Override
    public boolean existEmailCheck(String email) {
      return userRepository.existsByEmail(email);
    }

    @Override
    public void removeSessionMsg() {
     HttpSession session=((ServletRequestAttributes) (RequestContextHolder.
             getRequestAttributes())).getRequest().getSession();

     session.removeAttribute("msg");
    }


}
