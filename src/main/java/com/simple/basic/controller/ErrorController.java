package com.simple.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {

	@ExceptionHandler(Exception.class) // Exception타입이 아니라 .class를 붙임으로써 값으로 인식한다.
	public String except(Exception e, Model model) {

		log.error("Exception...................." + e.getMessage());

		// mode.addAttribute(exception)에 담아준것으로 view에서 어떤 에러가 났는지 표시하고, 에러페이지를 만들어준다.
		model.addAttribute("exception", e); // attributename명을 "exception"으로 한 것이다.

		log.error(model.toString());

		return "/error";
	}
         
}

