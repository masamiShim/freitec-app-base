package com.freitech.kotetsu.controllers.commons;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.freitech.kotetsu.exceptions.BussinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ListControllerBase<T> extends SpringControllerBase2<T> {

	@Autowired
	HttpSession session;

	@SuppressWarnings("unchecked")
	protected T searchCond() throws BussinessException {
		T cond = load();
		if (session.getAttribute("cond") != null
		  && session.getAttribute("cond").getClass().isInstance(m)) {
			cond = (T) session.getAttribute("cond");
		}
		session.removeAttribute("cond");
		return cond;
	}

	protected abstract T load();

}
