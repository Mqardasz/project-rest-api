package com.project.model;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity //Indeksujemy kolumny, które są najczęściej wykorzystywane do wyszukiwania studentów
@Table(name = "student")
public class Student {
	
    private static final Logger logger = LoggerFactory.getLogger(Student.class);
	
	public Student() {}
	
	public Student(String imie, String nazwisko, String nrIndeksu, String email, Boolean stacjonarny) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nrIndeksu = nrIndeksu;
		this.email = email;
		this.stacjonarny = stacjonarny;
		System.out.println(imie);
    	System.out.println("stworzono studenta");
		}
	
	@Id
	@GeneratedValue
	@Column(name="student_id")
	private Integer studentId;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 50, message = "{projekt.nazwa.size}")
	@Column(length = 50, nullable = false)
	private String imie;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 100, message ="{projekt.nazwa.size}")
	@Column(length = 100, nullable = false)
	private String nazwisko;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 20, message ="{projekt.nazwa.size}")
	@Column(length = 20, name = "nr_indeksu", nullable = false, unique = true)
	private String nrIndeksu;
	
	@NotBlank(message = "{projekt.nazwa.notblank}")
	@Size(min = 3, max = 50, message ="{projekt.nazwa.size}")
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "{projekt.nazwa.notblank}")
	@Column(nullable = false)
	private Boolean stacjonarny;
	
	@ManyToMany(mappedBy = "studenci")
	@JsonIgnore
	private Set<Projekt> projekty; // zbior jest bardziej wydajny
	
	public Student(String imie, String nazwisko, String nrIndeksu, Boolean stacjonarny) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nrIndeksu = nrIndeksu;
	}
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNrIndeksu() {
		return nrIndeksu;
	}

	public void setNrIndeksu(String nrIndeksu) {
		this.nrIndeksu = nrIndeksu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStacjonarny() {
		return stacjonarny;
	}

	public void setStacjonarny(Boolean stacjonarny) {
		this.stacjonarny = stacjonarny;
	}

	public Set<Projekt> getProjekty() {
		return projekty;
	}

	public void setProjekty(Set<Projekt> projekty) {
		this.projekty = projekty;
	}
	
	
}
