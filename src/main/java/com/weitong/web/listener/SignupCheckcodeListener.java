package com.weitong.web.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.weitong.utils.AliSMSUtil;

@Component("signupCheckcodeListener")
public class SignupCheckcodeListener implements MessageListener{

	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		try {
			String tel = mapMessage.getString("tel");
			String templateCode = mapMessage.getString("templateCode");
			Map<String, Object> tempalteParams = (Map<String, Object>) mapMessage.getObject("tempalteParams");
			//调用工具类发送短信
			AliSMSUtil.sendMessge(tel, templateCode, tempalteParams);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	

}
