package com.springbootbase.webapi.service;

import javax.servlet.http.HttpServletRequest;

import com.springbootbase.webapi.model.ApiResponse;
import com.springbootbase.webapi.model.VersionModel;


public interface BaseService {

  ApiResponse<VersionModel> version(HttpServletRequest request);
	
}
