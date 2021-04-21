package com.es.fiapcyreloapi.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "scheduled_activity")
@SequenceGenerator(name = "S_A_SEQ", sequenceName = "S_A_SEQ", initialValue = 1, allocationSize = 1)
public class ScheduledActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_A_SEQ")
	private long scheduledActivityId;
	@Temporal(TemporalType.DATE)
	private Date actualstart;
	@Temporal(TemporalType.DATE)
	private Date actualend;
	private String pjo_tipodeatividade;
	private String subject;
	private String pjo_empreendimentoid;
	private String pjo_blocoid;
	private String pjo_unidadeid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId", referencedColumnName = "userId")
	private User customer;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professionalId", referencedColumnName = "userId")
	private User professional;
	
	@Temporal(TemporalType.DATE)
	private Date createdIn;
	
	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getScheduledActivityId() {
		return scheduledActivityId;
	}

	public void setScheduledActivityId(long scheduledActivityId) {
		this.scheduledActivityId = scheduledActivityId;
	}

	public Date getActualstart() {
		return actualstart;
	}

	public void setActualstart(Date actualstart) {
		this.actualstart = actualstart;
	}

	public Date getActualend() {
		return actualend;
	}

	public void setActualend(Date actualend) {
		this.actualend = actualend;
	}

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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getProfessional() {
		return professional;
	}

	public void setProfessional(User professional) {
		this.professional = professional;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}
	
}
