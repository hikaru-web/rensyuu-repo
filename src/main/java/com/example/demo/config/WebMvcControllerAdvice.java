package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.InquiryNotFoundException;

@ControllerAdvice
public class WebMvcControllerAdvice {

	/*
	 * This method changes empty character to null
	 */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // カッコの中に、発生しうる例外を書く。「.class」もお忘れなく
	@ExceptionHandler(EmptyResultDataAccessException.class)
	//EmptyResultDataAccessExceptionはエラーのメッセージを受け取るときに利用する
	public String handleException(EmptyResultDataAccessException e,Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}

	//ここに書くと、すべてのコントローラで発生した例外をキャッチできる
	@ExceptionHandler(InquiryNotFoundException.class)
	public String handleException(InquiryNotFoundException e,Model model) {
		model.addAttribute("message", e);
		return "error/CustomPage";
	}

}
