package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{

	@Query("SELECT m FROM Matricula m WHERE m.aluno.pessoa.nmPessoa LIKE %:name%")
	public List<Matricula> findMatriculaByAluno(@Param("name") String name);
	
	@Query("SELECT m FROM Matricula m WHERE m.oferta.disciplina.nmDisciplina LIKE %:name%")
	public List<Matricula> findMatriculaByDisciplina(@Param("name") String name);
	
}
