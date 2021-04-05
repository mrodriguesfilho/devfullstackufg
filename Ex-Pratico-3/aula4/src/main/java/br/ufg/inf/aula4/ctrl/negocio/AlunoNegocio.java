package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.model.dao.AlunoDAO;
import br.ufg.inf.aula4.model.entities.Aluno;

public class AlunoNegocio {


		AlunoDAO dao = new AlunoDAO();
    PessoaNegocio pessoaNegocio = new PessoaNegocio();
		CursoNegocio cursoNegocio = new CursoNegocio();
    
    // CREATE
		public Aluno inserir(Aluno aluno) throws AlunoException {
			this.validarAluno(aluno);
			dao.inserir(aluno);
			return aluno;
		}
		
		// READ
		public List<Aluno> buscaTodos() throws AlunoException, PessoaExection, CursoException{
	/*		List<Aluno> alunos =  new ArrayList<Aluno>();
			for(Aluno a : dao.buscaTodos()) {
				a.setPessoa(pessoaNegocio.buscaPorId(a.getPessoa().getIdPessoa()));
				a.setCurso(cursoNegocio.buscaPorId((a.getCurso().getIdCurso())));
			} */
			return dao.buscaTodos();	
		}
		
		public Aluno buscaPorId(Integer id) throws AlunoException, PessoaExection, CursoException {
/*			Aluno aluno = new Aluno();
			aluno.setDtInicio(aluno.getDtInicio());
			aluno.setAtivo(aluno.getAtivo());
			aluno.setPessoa(pessoaNegocio.buscaPorId(aluno.getPessoa().getIdPessoa()));
			aluno.setCurso(cursoNegocio.buscaPorId(aluno.getCurso().getIdCurso()));
			*/
			return dao.buscaPorId(id);
		}
		
		
		// UPDATE
		
		public Aluno alterar(Aluno aluno) throws AlunoException, PessoaExection, CursoException {		
			return dao.alterar(aluno);
		}
		
		// DELETE
		
		public void excluir(Integer id) throws AlunoException {
			dao.excluir(id);
		}
		
		private void validarAluno(Aluno aluno) throws AlunoException {
			if(aluno.getPessoa() == null ){
				throw new AlunoException("É necessário vincular uma pessoa ao aluno");
			}

			if(aluno.getCurso() == null ){
				throw new AlunoException("Um aluno precisa ser aluno de um curso");
			}
		}
}
