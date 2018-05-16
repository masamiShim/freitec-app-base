package com.freitech.kotetsu.config.advices;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.freitech.kotetsu.exceptions.OptimisticLockWrappedException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ OptimisticLockWrappedException.class })
	@ResponseBody
	public Map<String, Object> handleError() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "許可されていないメソッド");
		return map;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handle500Error() {
		return "redirect:/500";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handle404Error() {
		return "redirect:/404";
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ResponseBody
	public String handle403Error() {
		return "redirect:/403";
	}

}
