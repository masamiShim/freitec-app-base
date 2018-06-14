package com.freitech.kotetsu.controllers.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mail.ImapMailReceiver;
import org.springframework.integration.mail.MailReceiver;
import org.springframework.integration.mail.config.MailReceiverFactoryBean;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class MailIndexController extends SpringControllerBase2<MailForm> {

	@Autowired
	GmailReceiver receiver;

	@ModelAttribute("receives")
	public MailDataList getNewMails() {
		return receiver.receive();
	}

	@GetMapping(value = {"/index"})
	public String index(Model model) {
		return "/mail/index";
	}

}
