package com.project.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="zadanie_id")
public class Zadanie {
	
	public Zadanie() {
		
	}
	
	public Zadanie(Integer zadanieId, Projekt projekt, String nazwa, Integer kolejnosc, String opis, LocalDateTime dataczasDodania) {
		this.zadanieId = zadanieId;
		this.projekt = projekt;
		this.nazwa = nazwa;
		this.kolejnosc = kolejnosc;
		this.opis = opis;
		this.dataczasDodania = dataczasDodania;
	}



	@Id
	@GeneratedValue
	@Column(name="zadanie_id")
	private Integer zadanieId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "projekt_id", nullable = false)
	@JsonBackReference
	private Projekt projekt;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
	@Column(length = 50, nullable = false)
	private String nazwa;
	
	@Column
	private Integer kolejnosc;
	
	@Size(min = 3, max = 50, message ="{projekt.nazwa.size}")
	@Column(length = 1000)
	private String opis;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Column(name = "dataczas_dodania", nullable = false)
	private LocalDateTime dataczasDodania;

	public Integer getZadanieId() {
		return zadanieId;
	}

	public void setZadanieId(Integer zadanieId) {
		this.zadanieId = zadanieId;
	}

	public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getKolejnosc() {
		return kolejnosc;
	}

	public void setKolejnosc(Integer kolejnosc) {
		this.kolejnosc = kolejnosc;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getDataczasDodania() {
		return dataczasDodania;
	}

	public void setDataczasDodania(LocalDateTime dataczasDodania) {
		this.dataczasDodania = dataczasDodania;
	}
	
	
}
