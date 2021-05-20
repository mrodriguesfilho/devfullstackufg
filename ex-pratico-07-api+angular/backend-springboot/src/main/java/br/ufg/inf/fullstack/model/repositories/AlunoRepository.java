package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

	@Query("SELECT a FROM Aluno a WHERE a.pessoa.nmPessoa LIKE %:name%")
	public List<Aluno> findAlunoByName(@Param("name") String name);
	
	@Query("SELECT a FROM Aluno a WHERE a.curso.nmCurso LIKE %:name%")
	public List<Aluno> findAlunoByCurso(@Param("name") String name);
	
}
