package com.freitech.kotetsu.config.advices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.freitech.kotetsu.db.repositories.UserRepository;
import com.freitech.kotetsu.models.User;

@ControllerAdvice
public class MessageHandlerAdvice {

	@Autowired
	UserRepository userRepository;
	
	@ModelAttribute("success")
	public String success(@RequestParam(name = "success", required = false) Optional<String> mes) {
		return mes.orElse(null);
	}

	@ModelAttribute("error")
	public String error(@RequestParam(name = "error", required = false) Optional<String> mes) {
		return mes.orElse(null);
	}

	@ModelAttribute("user")
	public User getUser(@AuthenticationPrincipal User user ) {
		return user;
	}

}
