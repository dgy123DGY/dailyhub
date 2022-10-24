package com.markerhub.handler;

import cn.hutool.core.util.StrUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TextHandler implements WxMpMessageHandler {

	@Autowired
	LoginHandler loginHandler;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

		// 用户的身份id
		String openid = wxMessage.getFromUser();
		// 用户输入的内容
		String content = wxMessage.getContent();

		String result = "无法识别字符串！";

		if (StrUtil.isNotBlank(content)) {

			content = content.toUpperCase().trim();
			if (content.indexOf("DY") == 0) {
				// 交给登录处理器
				result = loginHandler.hanlde(openid, content, wxMpService);
			}
		}

		return WxMpXmlOutMessage.TEXT()
				.content(result)
				.fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser())
				.build();
	}
}
