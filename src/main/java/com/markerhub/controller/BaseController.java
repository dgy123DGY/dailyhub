package com.markerhub.controller;

import com.markerhub.base.dto.UserDto;
import com.markerhub.base.lang.Consts;
import com.markerhub.mapstruct.CollectMapper;
import com.markerhub.service.CollectService;
import com.markerhub.service.UserService;
import com.markerhub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class BaseController {

	@Resource
	HttpServletRequest req;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	CollectService collectService;

	@Autowired
	CollectMapper collectMapper;

	@Autowired
	UserService userService;

	UserDto getCurrentUser() {
		UserDto userDto = (UserDto) req.getSession().getAttribute(Consts.CURRENT_USER);
		if (userDto == null) {
			userDto = new UserDto();
			userDto.setId(-1L);
		}
		return userDto;
	}

	long getCurrentUserId() {
		return getCurrentUser().getId();
	}

	Pageable getPage() {
		int page = ServletRequestUtils.getIntParameter(req, "page", 1);
		int size = ServletRequestUtils.getIntParameter(req, "size", 10);

		return PageRequest.of(page - 1, size,
				Sort.by(
						Sort.Order.desc("collected"),
						Sort.Order.desc("created")
				));
	}

}
