package com.simple.basic.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController{

	
	
	public String getErrorPath() {
		
		return null;
	}
	
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		  if(status != null){
	            int statusCode = Integer.valueOf(status.toString());

	            if(statusCode == HttpStatus.NOT_FOUND.value()) {
	                return "404";
	            } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	                return "500";
	            }else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
	                  return "405";
	            }else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
	                  return "400";
	            }
	        }
		  return "error/error";
	}
}
