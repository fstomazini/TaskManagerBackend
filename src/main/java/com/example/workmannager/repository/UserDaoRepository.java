package com.example.workmannager.repository;

import com.example.workmannager.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoRepository {
    @Autowired
    private UserDaoRepo userDaoRepo;

public UserDetails findUserDetailByEmail(String email){
    UserDetails user = userDaoRepo.findUserDaoByUsername(email);
    return user;
}
public UserDao findUserByEmail(String email){
        UserDao user = userDaoRepo.findUserDaoByUsername(email);
        return user;
}


}
