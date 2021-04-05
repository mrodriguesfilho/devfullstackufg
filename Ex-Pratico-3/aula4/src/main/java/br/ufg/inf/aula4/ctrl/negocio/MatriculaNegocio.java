package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.model.dao.MatriculaDAO;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaNegocio {


		MatriculaDAO dao = new MatriculaDAO();
		public Matricula inserir(Matricula matricula) throws MatriculaException {
			this.validarMatricula(matricula);
			dao.inserir(matricula);
			return matricula;
		}
		
		public List<Matricula> buscaTodos() throws MatriculaException{
			return dao.buscaTodos();	
		}
		
		public Matricula buscaPorId(Integer id) throws MatriculaException {
			
			return dao.buscaPorId(id);
		}
		
		public Matricula alterar(Matricula matricula) throws MatriculaException {		
			this.validarMatricula(matricula);
			return dao.alterar(matricula);
		}
		
		public void excluir(Integer id) throws MatriculaException {
			dao.excluir(id);
		}
		
		private void validarMatricula(Matricula matricula) throws MatriculaException {
			if (matricula.getAluno() == null) {
				throw new MatriculaException("É necessário vincular um aluno à matricula.");
			}
      if (matricula.getOferta() == null) {
				throw new MatriculaException("É necessário vincular uma oferta à matricula.");
			}
		}
}
