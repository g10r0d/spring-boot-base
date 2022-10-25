package com.springbootbase.webapi.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.springbootbase.webapi.model.ApiResponse;
import com.springbootbase.webapi.model.VersionModel;


public interface BaseService {

  ApiResponse<VersionModel> version(HttpServletRequest request);
	
}
