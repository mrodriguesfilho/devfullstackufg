package br.ufg.inf.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufg.inf.ctrl.AlunoCtrl;
import br.ufg.inf.ctrl.CursoCtrl;
import br.ufg.inf.ctrl.DisciplinaCtrl;
import br.ufg.inf.ctrl.OfertaCtrl;
import br.ufg.inf.ctrl.PessoaCtrl;
import br.ufg.inf.ctrl.ProfessorCtrl;
import br.ufg.inf.ctrl.MatriculaCtrl;
import br.ufg.inf.model.entities.Disciplina;
import br.ufg.inf.model.entities.Oferta;
import br.ufg.inf.model.entities.Pessoa;
import br.ufg.inf.model.entities.Professor;
import br.ufg.inf.model.entities.Curso;
import br.ufg.inf.model.entities.Aluno;
import br.ufg.inf.model.entities.Matricula;
import br.ufg.inf.model.enums.Dia;
import br.ufg.inf.model.enums.Escolaridade;

public class Aplication {

	public static void main(String[] args) {
		System.out.println("Executando!!!");
		//testePessoa();
		//testeDisciplina();
		//testeProfessor();
		//testeCurso();
		//testeOferta();
		//testeAluno();
		testeMatricula();
		System.out.println("Concluindo");
	}

	public static void testeMatricula(){
		try {
			MatriculaCtrl matriculaCtrl = new MatriculaCtrl();
			AlunoCtrl alunoCtrl = new AlunoCtrl();
			OfertaCtrl ofertaCtrl = new OfertaCtrl();

			Matricula m1 = new Matricula(null, alunoCtrl.buscaPorId(1), ofertaCtrl.buscaPorId(1));
			Matricula m2 = new Matricula(null, alunoCtrl.buscaPorId(2), ofertaCtrl.buscaPorId(2));
			Matricula m3 = new Matricula(null, alunoCtrl.buscaPorId(3), ofertaCtrl.buscaPorId(3));

			matriculaCtrl.inserir(m1);
			matriculaCtrl.inserir(m2);
			matriculaCtrl.inserir(m3);

			for( Matricula m : matriculaCtrl.buscaTodos()){
				System.out.println(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testeAluno() {
		try {
			AlunoCtrl alunoCtrl = new AlunoCtrl();
			CursoCtrl cursoCtrl = new CursoCtrl();
			PessoaCtrl pessoaCtrl = new PessoaCtrl();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			Aluno a1 = new Aluno(null, simpleDateFormat.parse("01/01/2020"), true, pessoaCtrl.buscaPorId(1),
			cursoCtrl.buscaPorId(1));


			Aluno a2 = new Aluno(null, simpleDateFormat.parse("01/01/2016"), true, pessoaCtrl.buscaPorId(2),
			cursoCtrl.buscaPorId(2));


			Aluno a3 = new Aluno(null, simpleDateFormat.parse("01/01/2018"), true, pessoaCtrl.buscaPorId(3),
			cursoCtrl.buscaPorId(3));
			
			alunoCtrl.inserir(a1);
			alunoCtrl.inserir(a2);
			alunoCtrl.inserir(a3);

			Aluno alunoAlterar = alunoCtrl.buscaPorId(3);
			alunoAlterar.setCurso(cursoCtrl.buscaPorId(2));
			alunoCtrl.alterar(alunoAlterar);

			for (Aluno a : alunoCtrl.buscaTodos()) {
				System.out.println(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testeCurso() {

		try {
			CursoCtrl cursoCtrl = new CursoCtrl();

			Curso c1 = new Curso(null, "Android Engineering");
			cursoCtrl.inserir(c1);

			/* Curso c2 = cursoCtrl.buscaPorId(1);
			c2.setNmCurso("Computer Science");
			cursoCtrl.alterar(c2);
			*/
			for (Curso c : cursoCtrl.buscaTodos()) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void testeOferta() {

		OfertaCtrl ctrl = new OfertaCtrl();
		ProfessorCtrl professorCtrl = new ProfessorCtrl();
		DisciplinaCtrl disciplinaCtrl = new DisciplinaCtrl();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Oferta ofe1 = new Oferta(null, professorCtrl.buscaPorId(1), disciplinaCtrl.buscaPorId(2),
					simpleDateFormat.parse("02/02/2021"), simpleDateFormat.parse("15/06/2021"), Dia.SEGUNDA, "08:00");

			Oferta ofe2 = new Oferta(null, professorCtrl.buscaPorId(2), disciplinaCtrl.buscaPorId(3),
					simpleDateFormat.parse("02/02/2021"), simpleDateFormat.parse("15/06/2021"), Dia.QUARTA, "14:00");

			Oferta ofe3 = new Oferta(null, professorCtrl.buscaPorId(3), disciplinaCtrl.buscaPorId(4),
					simpleDateFormat.parse("02/02/2021"), simpleDateFormat.parse("15/06/2021"), Dia.SEXTA, "19:00");

			ctrl.inserir(ofe1);
			ctrl.inserir(ofe2);
			ctrl.inserir(ofe3);

			for (Oferta o : ctrl.buscaTodos()) {
				System.out.println(o);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void testeProfessor() {

		ProfessorCtrl ctrl = new ProfessorCtrl();
		PessoaCtrl pessoaCtrl = new PessoaCtrl();

		Professor prof1 = new Professor(null, pessoaCtrl.buscaPorId(1), Escolaridade.MESTRADO);
		Professor prof2 = new Professor(null, pessoaCtrl.buscaPorId(2), Escolaridade.MEDIO);
		Professor prof3 = new Professor(null, pessoaCtrl.buscaPorId(3), Escolaridade.DOUTORADO);

		ctrl.inserir(prof1);
		ctrl.inserir(prof2);
		ctrl.inserir(prof3);

	}

	public static void testePessoa() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

		PessoaCtrl ctrl = new PessoaCtrl();

		try {

			Pessoa pes1 = new Pessoa(null, "Luiz", 12345678901l, simpleDateFormat.parse("01-01-1990"));
			ctrl.inserir(pes1);

			Pessoa pes2 = new Pessoa(null, "Fulano", 11111111111l, simpleDateFormat.parse("01-01-1990"));
			ctrl.inserir(pes2);

			Pessoa pes3 = new Pessoa(null, "Beltrano", 22222222222l, simpleDateFormat.parse("01-01-1990"));
			ctrl.inserir(pes3);

			Pessoa pes4 = new Pessoa(null, "Cicrano", 33333333333l, simpleDateFormat.parse("01-01-1990"));
			ctrl.inserir(pes4);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void testeCtrl() {

		DisciplinaCtrl ctrl = new DisciplinaCtrl();
		Disciplina disciplina = new Disciplina(null, "Banco de Dados", 64);

		// ctrl.inserir(disciplina);
		disciplina.setNmDisciplina(disciplina.getNmDisciplina() + " - Alterada");
		// ctrl.alterar(disciplina);
		System.out.println(ctrl.buscaPorId(3));

		System.out.println("-----------------------------------");
		for (Disciplina d : ctrl.buscaPorNome("Banco")) {
			System.out.println(d);
		}
	}

	public static void testeDisciplina() {

		DisciplinaCtrl ctrl = new DisciplinaCtrl();

		Disciplina disc1 = new Disciplina(null, "FullStack", 64);
		Disciplina disc2 = new Disciplina(null, "Matemática", 88);
		Disciplina disc3 = new Disciplina(null, "Java", 30);
		Disciplina disc4 = new Disciplina(null, "TypeScript", 90);

		try {
			ctrl.inserir(disc1);
			ctrl.inserir(disc2);
			ctrl.inserir(disc3);
			ctrl.inserir(disc4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Disciplina d : ctrl.buscaTodos()) {
			System.out.println(d);
		}

	}

}
