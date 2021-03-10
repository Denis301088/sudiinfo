package com.sudiinfo.repo;

import com.sudiinfo.domain.databaseclasses.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
