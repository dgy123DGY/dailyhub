package com.markerhub.controller;

import com.markerhub.base.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController extends BaseController{

	@GetMapping("/search")
	public String search(String q, Long userId) {

		String message = "正在搜索公开【收藏广场】的收藏记录";

		if (userId != null) {
			UserDto userDto = userService.getDtoById(userId);
			if (userDto != null) {
				message = "正在搜索用户【" + userDto.getUsername() +  "】的收藏记录";
			}
		}
		req.setAttribute("searchTip", message);
		req.setAttribute("q", q);

		return "collect-square";
	}

}
