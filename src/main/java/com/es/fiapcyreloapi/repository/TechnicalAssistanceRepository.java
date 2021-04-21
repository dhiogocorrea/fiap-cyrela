package com.es.fiapcyreloapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.es.fiapcyreloapi.dao.ScheduledActivity;

public interface TechnicalAssistanceRepository extends CrudRepository<ScheduledActivity, Long> {
	List<ScheduledActivity> findAllByProfessional(long professionalId);
	List<ScheduledActivity> findAllByCustomer(long customerId);
	
	List<ScheduledActivity> findAllByActualstartIsNull();
	
	List<ScheduledActivity> findAllByProfessionalAndActualendIsNull(long professionalId);
	List<ScheduledActivity> findAllByCustomerAndActualendIsNull(long customerId);
}
