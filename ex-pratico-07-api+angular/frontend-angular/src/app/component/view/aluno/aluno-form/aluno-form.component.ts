import { Pessoa } from './../../../../model/pessoa.model';
import { PessoaService } from './../../../../service/pessoa.service';
import { AlunoService } from './../../../../service/aluno.service';
import { Aluno } from './../../../../model/aluno.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Curso } from 'src/app/model/curso.model';
import { CursoService } from './../../../../service/curso.service';

@Component({
  selector: 'app-aluno-form',
  templateUrl: './aluno-form.component.html',
  styleUrls: ['./aluno-form.component.css']
})
export class AlunoFormComponent implements OnInit {


  titulo : string = "Cadastrar novo Aluno";
  aluno: Aluno = {
    pessoa: null,
    curso: null,
    dtInicio: null,
    ativo: null,
  }

  pessoas: Pessoa[] = [];
  cursos: Curso[] = [];
  dtInicio: string;

  constructor(
    private service: AlunoService,
    private router: Router,
    private pessoaService: PessoaService,
    private cursoService: CursoService,
  ) { }

  ngOnInit(): void {
    this.pessoaService.findAll().subscribe(pessoas =>{
      this.pessoas = pessoas;
    });

    this.cursoService.findAll().subscribe(cursos =>{
      this.cursos = cursos;
    })
  }


  salvar(): void {
    this.aluno.dtInicio = new Date(this.dtInicio);
    console.log(this.aluno)
    this.service.create(this.aluno).subscribe(() =>{
      this.service.showMessage("Aluno cadastrado com sucesso.")
      this.router.navigate(['/aluno/list']);
    })
  }

}
