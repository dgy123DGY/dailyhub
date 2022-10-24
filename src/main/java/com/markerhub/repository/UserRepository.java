package com.markerhub.repository;

import com.markerhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByOpenId(String openid);
}
