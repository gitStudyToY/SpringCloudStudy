package com.sitech.service;

import com.sitech.inter.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendServiceImpl implements MailSendService {

        @Autowired
        private JavaMailSender javaMailSender;

        @Value("${spring.mail.host}")
        private String host;
        @Value("${spring.mail.username}")
        private String account;
        @Value("${spring.mail.password}")
        private String password;
        @Value("${mail.smtp.auth}")
        private String isAuth;
        @Value("${mail.smtp.timeout}")
        private String outTime;

        @Override
        public void mailSend() {

//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

//		try {
////			message.setFrom(account);
//		    message.setTo(account);
////		    message.setSubject("测试邮件主题");
////		    message.setText("测试邮件内容");
//		    mailSender.send(mimeMessage);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(account);
            message.setTo("1584080254@qq.com");
            message.setSubject("主题：简单邮件");
            message.setText("测试邮件内容");
            javaMailSender.send(message);


        }



} 