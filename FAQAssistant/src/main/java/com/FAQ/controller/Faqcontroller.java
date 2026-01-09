package com.FAQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FAQ.exception.InvalidRequestException;
import com.FAQ.model.AskRequest;
import com.FAQ.model.AskResponse;
import com.FAQ.service.Faqservice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ask")
public class Faqcontroller {
	
	@Autowired
	private Faqservice faqservice;
	
	@PostMapping
	public AskResponse askResponse(@Valid @RequestBody AskRequest askRequest) {
		if (askRequest.getUserid() == null || askRequest.getUserid().trim().isEmpty()) {
            throw new InvalidRequestException("UserId must not be empty");
        }

        if (askRequest.getQuestion() == null || askRequest.getQuestion().trim().isEmpty()) {
            throw new InvalidRequestException("Question must not be empty");
        }
		String answer=faqservice.getAnswer(askRequest.getUserid(), askRequest.getQuestion());
		return new AskResponse(answer);
	}

}
