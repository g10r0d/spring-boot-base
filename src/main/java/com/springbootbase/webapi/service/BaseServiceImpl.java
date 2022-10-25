package com.springbootbase.webapi.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springbootbase.webapi.model.ApiResponse;
import com.springbootbase.webapi.model.VersionModel;


@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BuildProperties buildProperties;

	@Override
	public ApiResponse<VersionModel> version(HttpServletRequest request) {
		VersionModel version = new VersionModel();
		version.setArtifact(buildProperties.getArtifact());
		version.setGroup(buildProperties.getGroup());
		version.setName(buildProperties.getName());
		version.setTime(buildProperties.getTime());
		version.setVersion(buildProperties.getVersion());

		ApiResponse<VersionModel> apiResponse = new ApiResponse<>();
		return apiResponse.setResponse(HttpStatus.OK, version, null, null, request);
	}
	
}
