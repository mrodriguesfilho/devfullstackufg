package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

  @Query("SELECT d FROM Aluno d WHERE d.curso.nmCurso LIKE %:name%")
  public List<Aluno> findByCurso(@Param("name") String name);

}
