package com.genx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genx.model.Birth;
import com.genx.service.MicroService;

@RestController
@RequestMapping("/micro")
public class Controller {

	@Autowired
	MicroService microService;

	@PostMapping(value = "/birthRegister")
	public boolean microInsert(@RequestBody Birth birth) throws Exception {
		return microService.insertBirthRecord(birth);
	}

}
