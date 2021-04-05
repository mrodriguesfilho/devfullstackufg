package br.ufg.inf.model.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer idCurso;
	
	@Column(name = "nmCurso")
	private String nmCurso;

	public Curso() {
		super();
	}

	public Curso(Integer idCurso, String nmCurso) {
		super();
		this.idCurso = idCurso;
		this.nmCurso = nmCurso;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public String getNmCurso() {
		return nmCurso;
	}

	public void setNmCurso(String nmCurso) {
		this.nmCurso = nmCurso;
	}

	@Override
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nmCurso=" + nmCurso + "]";
	}

}
