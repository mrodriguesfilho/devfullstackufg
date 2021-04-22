package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

  @Query("SELECT d FROM Matricula d WHERE d.aluno.pessoa.nmPessoa LIKE %:name%")
  public List<Matricula> findByNmAluno(@Param("name") String name);

}
