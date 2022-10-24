package com.markerhub.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.markerhub.base.dto.UserDto;
import com.markerhub.entity.User;
import com.markerhub.mapstruct.UserMapper;
import com.markerhub.repository.UserRepository;
import com.markerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	@Override
	public UserDto register(String openid) {

		Assert.isTrue(openid != null, "不合法注册条件");

		User user = userRepository.findByOpenId(openid);

		if (user == null) {
			user = new User();
			user.setUsername("Hub-" + RandomUtil.randomString(5));
			user.setAvatar("http://localhost:8080/images/logo.jpeg");

			user.setCreated(LocalDateTime.now());
			user.setOpenId(openid);

		} else {
			user.setLasted(LocalDateTime.now());
		}

		userRepository.save(user);
		return userMapper.toDto(user);
	}

	@Override
	public UserDto getDtoById(Long userId) {
		Optional<User> optional = userRepository.findById(userId);
		if (optional.isPresent()) {
			return userMapper.toDto(optional.get());
		}
		return null;
	}
}
