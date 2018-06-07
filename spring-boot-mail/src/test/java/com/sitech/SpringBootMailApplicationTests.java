package com.sitech;

import com.sitech.inter.MailSendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailApplicationTests {

	@Autowired
	private MailSendService mailSendService;

	@Test
	public void contextLoads() {

		mailSendService.mailSend();
	}

}
