package br.ufg.inf.aula4.app;

import br.ufg.inf.aula4.ctrl.DisciplinaCtrl;
import br.ufg.inf.aula4.ctrl.OfertaCtrl;
import br.ufg.inf.aula4.ctrl.PessoaCtrl;
import br.ufg.inf.aula4.ctrl.ProfessorCtrl;
import br.ufg.inf.aula4.model.entities.Pessoa;
import br.ufg.inf.aula4.ctrl.AlunoCtrl;
import br.ufg.inf.aula4.ctrl.CursoCtrl;

public class aplication {

	public static void main(String[] args) {

		System.out.println("Começando por aqui");

		TesteApp testeApp = new TesteApp();
		
	//	testeApp.testeCrudCurso(new CursoCtrl());
	//	testeApp.testeCrudDisciplina(new DisciplinaCtrl());
	//	testeApp.testeCrudPessoa(new PessoaCtrl());
	//	testeApp.testeCrudProfessor(new ProfessorCtrl(), new PessoaCtrl());
	//	testeApp.testeCrudOferta(new OfertaCtrl(), new DisciplinaCtrl(), new ProfessorCtrl());
		testeApp.testeCrudAluno(new AlunoCtrl(), new PessoaCtrl(), new CursoCtrl());
	}
	
	

}
