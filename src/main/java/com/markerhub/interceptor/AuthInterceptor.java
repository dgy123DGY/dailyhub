package com.markerhub.interceptor;

import com.markerhub.base.annotaion.Login;
import com.markerhub.base.dto.UserDto;
import com.markerhub.base.lang.Consts;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 发送登录用户到前端
		UserDto userDto = (UserDto) request.getSession().getAttribute(Consts.CURRENT_USER);
		if (userDto == null) {
			userDto = new UserDto();
			userDto.setId(-1L);
		}
		request.setAttribute("current", userDto);

		// 判断方法上是否有@Login注解
		Login annoation;
		if (handler instanceof HandlerMethod) {
			annoation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
		} else {
			return true;
		}

		// 如果没有@Login注解，说明不需要登录就能访问，直接放行
		if (annoation == null) {
			return true;
		}

		// 如果有，那么判断用户是否已经登录
		if (userDto.getId() == null || userDto.getId() == -1L) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		return true;
	}

}
