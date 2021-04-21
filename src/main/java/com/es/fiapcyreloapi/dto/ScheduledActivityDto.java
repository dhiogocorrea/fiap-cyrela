package com.es.fiapcyreloapi.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.es.fiapcyreloapi.dao.User;

public class ScheduledActivityDto {

	private String pjo_tipodeatividade;
	private String subject;
	private String pjo_empreendimentoid;
	private String pjo_blocoid;
	private String pjo_unidadeid;

	public String getPjo_tipodeatividade() {
		return pjo_tipodeatividade;
	}

	public void setPjo_tipodeatividade(String pjo_tipodeatividade) {
		this.pjo_tipodeatividade = pjo_tipodeatividade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPjo_empreendimentoid() {
		return pjo_empreendimentoid;
	}

	public void setPjo_empreendimentoid(String pjo_empreendimentoid) {
		this.pjo_empreendimentoid = pjo_empreendimentoid;
	}

	public String getPjo_blocoid() {
		return pjo_blocoid;
	}

	public void setPjo_blocoid(String pjo_blocoid) {
		this.pjo_blocoid = pjo_blocoid;
	}

	public String getPjo_unidadeid() {
		return pjo_unidadeid;
	}

	public void setPjo_unidadeid(String pjo_unidadeid) {
		this.pjo_unidadeid = pjo_unidadeid;
	}
	
}
