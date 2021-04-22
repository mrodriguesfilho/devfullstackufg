package br.ufg.inf.fullstack.ctrl.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fullstack.ctrl.exception.AlunoException;
import br.ufg.inf.fullstack.model.entities.Aluno;
import br.ufg.inf.fullstack.model.repositories.AlunoRepository;

@Service
public class AlunoBusiness {

  @Autowired
  private AlunoRepository repository;

  public List<Aluno> findAll() {
    return repository.findAll();
  }

  public Aluno findById(Integer id) throws AlunoException {
    Optional<Aluno> retorno = repository.findById(id);
    if (retorno.isEmpty()) {
      throw new AlunoException("0131");
    }
    return retorno.get();
  }

  public List<Aluno> findByCurso(String str) {
    return repository.findByCurso(str);
  }

  public Aluno insert(Aluno aluno) throws AlunoException {
    this.validarAluno(aluno);
    return repository.save(aluno);
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public Aluno update(Aluno alunoUpd) throws AlunoException {
    this.validarAluno(alunoUpd);
    Aluno aluno = repository.findById(alunoUpd.getIdAluno()).get();
    aluno.setDtInicio(alunoUpd.getDtInicio());
    aluno.setAtivo(alunoUpd.getAtivo());
    aluno.setPessoa(alunoUpd.getPessoa());
    aluno.setCurso(alunoUpd.getCurso());

    return repository.save(aluno);

  }

  private void validarAluno(Aluno aluno) throws AlunoException {
    if (aluno.getPessoa() == null) {
      throw new AlunoException("0132");
    }

    if (aluno.getCurso() == null) {
      throw new AlunoException("0133");
    }
  }

}
