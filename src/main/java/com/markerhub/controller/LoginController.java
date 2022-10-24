package com.markerhub.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.markerhub.base.dto.UserDto;
import com.markerhub.base.lang.Consts;
import com.markerhub.base.lang.Result;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class LoginController extends BaseController {

	@Autowired
	WxMpService wxMpService;

	@Autowired
	WxMpMessageRouter wxMpMessageRouter;

	@GetMapping("/login")
	public String login() {

		String code = "DY" + RandomUtil.randomNumbers(4);
		while (redisUtil.hasKey("ticket-" + code)) {
			code = "DY" + RandomUtil.randomNumbers(4);
		}

		String ticket = RandomUtil.randomString(32);

		// 5分钟后过期
		redisUtil.set("ticket-" + code, ticket, 5 * 60);

		req.setAttribute("code", code);
		req.setAttribute("ticket", ticket);

		return "login";
	}

	@ResponseBody
	@GetMapping("/login-check")
	public Result loginCheck(String code, String ticket) {

		// 校验逻辑后面写
		if (!redisUtil.hasKey("Info-" + code)) {
			return Result.failure("用户未登录");
		}

		String ticketBak = redisUtil.get("ticket-" + code).toString();
		if (!ticketBak.equals(ticket)) {
			return Result.failure("登录失败");
		}

		String userJson = redisUtil.get("Info-" + code).toString();
		UserDto userDto = JSONUtil.toBean(userJson, UserDto.class);
		req.getSession().setAttribute(Consts.CURRENT_USER, userDto);

		return Result.success();
	}

	@ResponseBody
	@RequestMapping("/wx/back")
	public String wxCallBack(String signature, String timestamp, String nonce, String echostr) throws IOException {

		if (StrUtil.isNotBlank(echostr)) {
			log.info("正在配置回调接口 ------------{}", echostr);
			return echostr;
		}

		boolean checkSignature = wxMpService.checkSignature(timestamp, nonce, signature);
		if (!checkSignature) {
			log.error("签名不合法");
			return null;
		}

		WxMpXmlMessage wxMpXmlMessage = WxMpXmlMessage.fromXml(req.getInputStream());
		WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(wxMpXmlMessage);

		return outMessage == null ? "" : outMessage.toXml();
	}

	@GetMapping("/autologin")
	public String autologin(String token) {

		Object userObj = redisUtil.get("autologin-" + token);
		if (userObj != null) {
			UserDto userDto = JSONUtil.toBean(userObj.toString(), UserDto.class);
			req.getSession().setAttribute(Consts.CURRENT_USER, userDto);
			return "redirect:/";
		}

		return "redirect:/login";
	}

}
