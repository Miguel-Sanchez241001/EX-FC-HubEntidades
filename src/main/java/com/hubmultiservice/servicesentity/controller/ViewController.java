package com.hubmultiservice.servicesentity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewController {

    
    @RequestMapping("/")
	public String GoInicio(Model model) {
	 
		return "index";
	}
}
