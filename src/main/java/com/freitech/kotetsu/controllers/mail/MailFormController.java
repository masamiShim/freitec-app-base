package com.freitech.kotetsu.controllers.mail;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freitech.kotetsu.controllers.commons.SpringControllerBase2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class MailFormController extends SpringControllerBase2<MailForm> {

	@GetMapping(value = {"/input"})
	public String input(@PathVariable(required = false) Optional<Long> id, Model model) {
		return "/mail/input";
	}
}
