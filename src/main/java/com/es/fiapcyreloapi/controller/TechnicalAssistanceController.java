package com.es.fiapcyreloapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.es.fiapcyreloapi.service.TechnicalAssistanceService;
import com.es.fiapcyreloapi.service.UserService;
import com.es.fiapcyreloapi.dao.ScheduledActivity;
import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.ScheduledActivityDto;
import com.es.fiapcyreloapi.dto.UserDto;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class TechnicalAssistanceController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TechnicalAssistanceService technicalAssistanceService;

	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/technical_assistance", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ScheduledActivity> getAll() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		return technicalAssistanceService.getAll(user);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/technical_assistance/open", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<ScheduledActivity> getAllOpen() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		return technicalAssistanceService.getAllOpen(user);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/technical_assistance/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ScheduledActivity getOne(@PathVariable long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		return technicalAssistanceService.get(user, id);
	}
	
	@RequestMapping(value = "/technical_assistance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody ScheduledActivityDto scheduledActivityDto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		
		technicalAssistanceService.create(user, scheduledActivityDto);
	}
	
	@ApiOperation(value = "", authorizations = { @io.swagger.annotations.Authorization(value="jwtToken") })
	@RequestMapping(value = "/technical_assistance/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		technicalAssistanceService.delete(user, id);
	}
	
	@RequestMapping(value = "/technical_assistance/{id}/assign", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void assignTechnicalAssistanceToMe(long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		
		technicalAssistanceService.assignTechnicalAssistanceToMe(user, id);
	}
	
	@RequestMapping(value = "/technical_assistance/{id}/start", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void startTechnicalAssistance(long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		
		technicalAssistanceService.startTechnicalAssistance(user, id);
	}
	
	@RequestMapping(value = "/technical_assistance/{id}/close", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void closeTechnicalAssistance(long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByEmail(auth.getName());
		
		technicalAssistanceService.assignTechnicalAssistanceToMe(user, id);
	}
}
