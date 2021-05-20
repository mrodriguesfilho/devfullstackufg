package br.ufg.inf.fullstack.ctrl.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fullstack.ctrl.exception.PessoaException;
import br.ufg.inf.fullstack.model.entities.Pessoa;
import br.ufg.inf.fullstack.model.repositories.PessoaRepository;

@Service
public class PessoaBusiness {

	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> findAll(){
		return repository.findAll();
	}
	
	public Pessoa findById(Integer id) throws PessoaException {
		Optional<Pessoa> retorno = repository.findById(id);
		if(retorno.isEmpty()) {
			throw new PessoaException("0209");
		}
		return retorno.get();
	}
	
	public List<Pessoa> findByName(String str){
		return repository.findByNmDisciplina(str);
	}
	
	public List<Pessoa> findDtNascimentoMaior(Date dt){
		return repository.findByDataNascimentoMaior(dt);
	}
	
	public Pessoa insert(Pessoa pessoa) throws PessoaException {
		this.validarPessoa(pessoa);
		return repository.save(pessoa);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public Pessoa update(Pessoa pessoa) throws PessoaException {
		this.validarPessoa(pessoa);
		Pessoa pessoaUpd = repository.findById(pessoa.getIdPessoa()).get();
		pessoaUpd.setCpf(pessoa.getCpf());
		pessoaUpd.setNmPessoa(pessoa.getNmPessoa());
		pessoaUpd.setDtNascimento(pessoa.getDtNascimento());
		return repository.save(pessoaUpd);
	}
	
	private void validarPessoa(Pessoa pessoa) throws PessoaException {
		
		if (pessoa.getNmPessoa() == null || pessoa.getNmPessoa().length() == 0) {
			throw new PessoaException("0204");
		}

		if (pessoa.getCpf().toString().length() != 11) {
			throw new PessoaException("0211");
		}
		
		Calendar calHoje = Calendar.getInstance();
		Calendar calNascimento = Calendar.getInstance();
		calNascimento.setTime(pessoa.getDtNascimento());
		
		if (calHoje.after(calNascimento)) {
			throw new PessoaException("0203");
		}
	}
}
