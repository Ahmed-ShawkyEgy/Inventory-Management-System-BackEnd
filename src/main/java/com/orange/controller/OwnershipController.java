package com.orange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.repository.custom.OwnershipRepositoryCustom;

@RestController
@RequestMapping("/api")
public class OwnershipController {
	
	@Autowired
	private OwnershipRepositoryCustom userRepositoryCustom;
	
	

}
