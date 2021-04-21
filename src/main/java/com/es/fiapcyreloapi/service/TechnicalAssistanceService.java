package com.es.fiapcyreloapi.service;

import java.util.List;

import com.es.fiapcyreloapi.dao.ScheduledActivity;
import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.ScheduledActivityDto;

public interface TechnicalAssistanceService {

	 List<ScheduledActivity> getAll(User user);
	 List<ScheduledActivity> getAllOpen(User user);
	 ScheduledActivity get(User user, long id);
	 void create(User user, ScheduledActivityDto dto);
	 void delete(User user, long id);
	 void assignTechnicalAssistanceToMe(User professional, long id);
	 void startTechnicalAssistance(User professional, long id);
	 void closeTechnicalAssistance(User professional, long id);
}
