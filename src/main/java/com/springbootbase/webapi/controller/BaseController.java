package com.springbootbase.webapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootbase.webapi.model.ApiResponse;
import com.springbootbase.webapi.model.VersionModel;
import com.springbootbase.webapi.service.BaseService;

@RestController
public class BaseController {
	
	@Autowired
	private BaseService baseService;

	@GetMapping(value = "/v1/version", headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<ApiResponse<VersionModel>> version(HttpServletRequest request) {
		ApiResponse<VersionModel> apiResponse = baseService.version(request);
		return new ResponseEntity<ApiResponse<VersionModel>>(apiResponse, HttpStatus.OK);
	}
}
