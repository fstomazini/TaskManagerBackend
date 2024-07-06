package com.example.workmannager.services;

import com.example.workmannager.DAO.UserDao;
import com.example.workmannager.repository.UserDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    @Autowired
    private UserDaoRepository userDaoRepository;

    public UserDetails findUserDetailByEmail(String email){
        return userDaoRepository.findUserByEmail(email);
    }

    public UserDao findUserByEmail(String email){
        return userDaoRepository.findUserByEmail(email);
    }
}
