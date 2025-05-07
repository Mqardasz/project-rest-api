package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="projekt")
public class Projekt {
	
	public Projekt() {
		
	}
	
	public Projekt(Integer projektId, String nazwa, String opis, LocalDateTime createdDate, LocalDate lastModifiedDate) {
		this.projektId = projektId;
		this.nazwa = nazwa;
		this.opis = opis;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Projekt(String nazwa, String opis, LocalDate lastModifiedDate) {
		this.nazwa = nazwa;
		this.opis = opis;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public Projekt(Integer projektId, String nazwa, String opis, LocalDate lastModifiedDate) {
		this.projektId = projektId;
		this.nazwa = nazwa;
		this.opis = opis;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Id
	@GeneratedValue
	@Column(name="projekt_id")
	private Integer projektId;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
	@Column(nullable = false, length = 50)
	private String nazwa;
	
	@Size(min = 1, max = 1000, message = "{projekt.nazwa.size}")
	@Column(length = 1000)
	private String opis;
	
	// działa to automatycznie
	@CreatedDate
	@Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column(name = "dataczas_modyfikacji", insertable = false)
	private LocalDate lastModifiedDate;
	
	@OneToMany(mappedBy = "projekt")
	@JsonManagedReference
	private List<Zadanie> zadania;
	
	
	 @ManyToMany
	 @JsonIgnore
	 @JoinTable(name = "projekt_student",
	 			joinColumns = {@JoinColumn(name="projekt_id")},
	 			inverseJoinColumns = {@JoinColumn(name="student_id")})
	 private Set<Student> studenci;

	public Integer getProjektId() {
		return projektId;
	}

	public void setProjektId(Integer projektId) {
		this.projektId = projektId;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDate lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<Zadanie> getZadania() {
		return zadania;
	}

	public void setZadania(List<Zadanie> zadania) {
		this.zadania = zadania;
	}

	public Set<Student> getStudenci() {
		return studenci;
	}

	public void setStudenci(Set<Student> studenci) {
		this.studenci = studenci;
	}

	 

}
