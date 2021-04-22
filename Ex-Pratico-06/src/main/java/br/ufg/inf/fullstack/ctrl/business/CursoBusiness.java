package br.ufg.inf.fullstack.ctrl.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fullstack.ctrl.exception.CursoException;
import br.ufg.inf.fullstack.model.entities.Curso;
import br.ufg.inf.fullstack.model.repositories.CursoRepository;

@Service
public class CursoBusiness {

  @Autowired
  private CursoRepository repository;

  public List<Curso> findAll() {
    return repository.findAll();
  }

  public Curso findById(Integer id) throws CursoException {
    Optional<Curso> retorno = repository.findById(id);
    if (retorno.isEmpty()) {
      throw new CursoException("0118");
    }
    return retorno.get();
  }

  public List<Curso> findByNmCurso(String str) {
    return repository.findByNmCurso(str);
  }

  public Curso insert(Curso curso) throws CursoException {
    this.validarCurso(curso);
    return repository.save(curso);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public Curso update(Curso cursoUpd) throws CursoException {
    this.validarCurso(cursoUpd);
    Curso curso = repository.findById(cursoUpd.getIdCurso()).get();
    curso.setNmCurso(cursoUpd.getNmCurso());
    return repository.save(curso);

  }

  private void validarCurso(Curso curso) throws CursoException {
    if (curso.getNmCurso() == null || curso.getNmCurso().length() == 0) {
      throw new CursoException("0119");
    }
  }

}
