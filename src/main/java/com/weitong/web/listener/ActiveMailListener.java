package com.weitong.web.listener;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.weitong.utils.AliSMSUtil;
import com.weitong.utils.MailUtils;

@Component("activeMailListener")
public class ActiveMailListener implements MessageListener{

	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		try {
			String subject = mapMessage.getString("subject");
			String content = mapMessage.getString("content");
			String to = mapMessage.getString("to");
			//调用mailUtil发送激活邮件
			MailUtils.sendMail(subject, content, to);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	

}
