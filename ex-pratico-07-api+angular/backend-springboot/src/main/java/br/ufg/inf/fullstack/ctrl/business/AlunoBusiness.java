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
	
	public List<Aluno> findAll(){
		return repository.findAll();
	}
	
	public Aluno findById(Integer id) throws AlunoException {
		Optional<Aluno> retorno = repository.findById(id);
		if(retorno.isEmpty()) {
			throw new AlunoException("0609");
		}
		return retorno.get();
	}
	
	public List<Aluno> findAlunoByName(String str){
		return repository.findAlunoByName(str);
	}
	
	public List<Aluno> findAlunoByCurso(String str){
		return repository.findAlunoByCurso(str);
	}

	
	
	public Aluno insert(Aluno aluno) throws AlunoException {
		this.validarAluno(aluno);
		return repository.save(aluno);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Aluno update(Aluno aluno) throws AlunoException {
		this.validarAluno(aluno);
		Aluno alunoUpd = repository.findById(aluno.getIdAluno()).get();
		alunoUpd.setAtivo(aluno.getAtivo());
		alunoUpd.setCurso(aluno.getCurso());
		alunoUpd.setPessoa(aluno.getPessoa());
		alunoUpd.setDtInicio(aluno.getDtInicio());
		return repository.save(alunoUpd);
		
	}
	
	private void validarAluno(Aluno aluno) throws AlunoException {

		if(aluno.getCurso() == null) {
			throw new AlunoException("0103");
		}
		
		if(aluno.getPessoa() == null) {
			throw new AlunoException("0104");
		}
		
		if(aluno.getDtInicio() == null) {
			throw new AlunoException("0110");
		}
	}
	
}
