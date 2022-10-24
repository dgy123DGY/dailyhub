package com.markerhub.service;

import com.markerhub.base.dto.UserDto;

public interface UserService {
	UserDto register(String openid);

	UserDto getDtoById(Long userId);
}
