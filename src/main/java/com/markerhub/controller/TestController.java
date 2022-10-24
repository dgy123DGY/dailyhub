package com.markerhub.controller;

import com.markerhub.entity.User;
import com.markerhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/test")
	public String test(HttpServletRequest request) {
		User user = userRepository.getById(2L);

		request.setAttribute("username", user.getUsername());
		return "test";
	}

}
