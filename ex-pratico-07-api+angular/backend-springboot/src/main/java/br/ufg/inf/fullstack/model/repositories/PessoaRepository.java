package br.ufg.inf.fullstack.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT p FROM Pessoa p WHERE p.nmPessoa LIKE %:name%")
	public List<Pessoa> findByNmDisciplina(@Param("name") String name);
	
	@Query("SELECT p FROM Pessoa p WHERE p.dtNascimento >= ?1")
	public List<Pessoa> findByDataNascimentoMaior(Date dtNascimento);
	
}
