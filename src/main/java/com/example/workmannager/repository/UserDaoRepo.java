package com.example.workmannager.repository;

import com.example.workmannager.DAO.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoRepo extends JpaRepository<UserDao, Long> {

    UserDao findUserDaoByUsername(String username);
}
