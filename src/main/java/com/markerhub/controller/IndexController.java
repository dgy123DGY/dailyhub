package com.markerhub.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import com.markerhub.base.annotaion.Login;
import com.markerhub.base.dto.CollectDto;
import com.markerhub.base.dto.DatelineDto;
import com.markerhub.entity.Collect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController extends BaseController{

	@Value("${server.domain}")
	String domain;

	@Login
	@RequestMapping(value = {"", "/"})
	public String index() {

		List<DatelineDto> datelineDtos = collectService.getDatelineByUserId(getCurrentUserId());
		req.setAttribute("datelines", datelineDtos);
		req.setAttribute("userId", getCurrentUserId());

		return "index";
	}
	@Login
	@RequestMapping(value = "/collect-user/{userId}")
	public String userCollect(@PathVariable(name = "userId") long userId) {

		List<DatelineDto> datelineDtos = collectService.getDatelineByUserId(userId);
		req.setAttribute("datelines", datelineDtos);
		req.setAttribute("userId", userId);

		return "index";
	}



	@Login
	@GetMapping("/collect/edit")
	public String editCollect(Collect collect) {

		String js = "javascript:(function(){" +
				"var site='" + domain +"/collect/edit" +
				"?title='+encodeURIComponent(document.title)+'" +
				"&url='+encodeURIComponent(document.URL);" +
				"var win=window.open(site,'_blank');" +
				"win.focus();})()";

		if (collect.getId() != null) {
			Collect temp = collectService.findById(collect.getId());
			Assert.notNull(temp, "未找到对应收藏");
			Assert.isTrue(getCurrentUserId() == temp.getUser().getId(), "无权限编辑");

			BeanUtil.copyProperties(temp, collect);
		}

		CollectDto collectDto = collectMapper.toDto(collect);

		req.setAttribute("js", js);
		req.setAttribute("collect", collectDto);
		return "collect-edit";
	}

	@GetMapping("/collect-square")
	public String collectSquare() {
		return "collect-square";
	}
}
