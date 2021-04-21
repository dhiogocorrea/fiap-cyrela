package com.es.fiapcyreloapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.es.fiapcyreloapi.dao.ScheduledActivity;
import com.es.fiapcyreloapi.dao.User;
import com.es.fiapcyreloapi.dto.ScheduledActivityDto;
import com.es.fiapcyreloapi.enumerable.UserType;
import com.es.fiapcyreloapi.repository.TechnicalAssistanceRepository;
import com.es.fiapcyreloapi.service.TechnicalAssistanceService;

@Service
public class TechnicalAssistanceServiceImpl implements TechnicalAssistanceService {
	
	private TechnicalAssistanceRepository technicalAssistanceRepository;
	
	@Autowired
	public TechnicalAssistanceServiceImpl(TechnicalAssistanceRepository technicalAssistanceRepository) {
		this.technicalAssistanceRepository = technicalAssistanceRepository;
	}

	@Override
	public List<ScheduledActivity> getAll(User user) {
		if (user.getUserType() == UserType.ADMIN) {
			return getAll();
		} else if (user.getUserType() == UserType.CUSTOMER) {
			return technicalAssistanceRepository.findAllByCustomer(user.getUserId());
		}
		return technicalAssistanceRepository.findAllByProfessional(user.getUserId());
	}
	
	private List<ScheduledActivity> getAll() {
		ArrayList<ScheduledActivity> result = new ArrayList<ScheduledActivity>();
		technicalAssistanceRepository.findAll().forEach(result::add);
		return result;
	}
	
	@Override
	public List<ScheduledActivity> getAllOpen(User user) {
		if (user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.TECH_SUPPORT_PROFESSIONAL) {
			return technicalAssistanceRepository.findAllByActualstartIsNull();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have access to this.");
		}
	}

	@Override
	public ScheduledActivity get(User user, long id) {
		ScheduledActivity act = technicalAssistanceRepository.findById(id).get();
		
		if (user.getUserType() == UserType.ADMIN || user.getUserType() == UserType.TECH_SUPPORT_PROFESSIONAL || act.getCustomer().getUserId() == user.getUserId()) {
			return act;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't have access to this.");
		}
	}

	@Override
	public void create(User user, ScheduledActivityDto dto) {
		ScheduledActivity act = new ScheduledActivity();
		
		if (user.getUserType() != UserType.CUSTOMER) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only customers can open a scheduled technical assistance.");
		}
		
		act.setCustomer(user);
		act.setPjo_blocoid(dto.getPjo_blocoid());
		act.setPjo_empreendimentoid(dto.getPjo_empreendimentoid());
		act.setPjo_tipodeatividade(dto.getPjo_tipodeatividade());
		act.setPjo_unidadeid(dto.getPjo_unidadeid());
		act.setSubject(dto.getSubject());
		
		technicalAssistanceRepository.save(act);
	}

	@Override
	public void delete(User user, long id) {
		ScheduledActivity act = technicalAssistanceRepository.findById(id).get();
		
		if  (user.getUserType() == UserType.ADMIN || act.getCustomer().getUserId() == user.getUserId()) {
			if (act.getActualend() != null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete closed technical assistance.");
			}
			technicalAssistanceRepository.delete(act);

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only the owner can delete the technical assistance.");
		}
	}
	
	@Override
	public void assignTechnicalAssistanceToMe(User professional, long id) {
		ScheduledActivity act = technicalAssistanceRepository.findById(id).get();
		
		if (act.getProfessional() == null) {
			act.setProfessional(professional);
			technicalAssistanceRepository.save(act);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Technical assistance already taken.");
		}
	}
	
	@Override
	public void startTechnicalAssistance(User professional, long id) {
		ScheduledActivity act = technicalAssistanceRepository.findById(id).get();
		
		if (act.getProfessional().getUserId() == professional.getUserId() || professional.getUserType() == UserType.ADMIN) {
			if (act.getActualstart() == null) {
				act.setActualstart(new Date());
				technicalAssistanceRepository.save(act);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Technical assistance already started at " + act.getActualstart());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't own this technical assistance.");
		}
	}
	
	@Override
	public void closeTechnicalAssistance(User professional, long id) {
		ScheduledActivity act = technicalAssistanceRepository.findById(id).get();
		
		if (act.getProfessional().getUserId() == professional.getUserId() || professional.getUserType() == UserType.ADMIN) {
			if (act.getActualend() == null) {
				act.setActualend(new Date());
				technicalAssistanceRepository.save(act);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Technical assistance already ended at " + act.getActualstart());
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You don't own this technical assistance.");
		}
	}
}
