import { Aluno } from './../../../../model/aluno.model';
import { PessoaService } from './../../../../service/pessoa.service';
import { AlunoService } from './../../../../service/aluno.service';
import { Pessoa } from './../../../../model/pessoa.model';
import { Curso } from './../../../../model/curso.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CursoService } from './../../../../service/curso.service';

@Component({
  selector: 'app-aluno-update',
  templateUrl: './../aluno-form/aluno-form.component.html',
  styleUrls: ['./../aluno-form/aluno-form.component.css']
})
export class AlunoUpdateComponent implements OnInit {

  
  titulo : string = "Alterar dados do Aluno";
  aluno: Aluno = {
    pessoa: null,
    dtInicio: null,
    ativo: null,
    curso: null,
  }

  pessoas: Pessoa[] = [];
  cursos: Curso[] = [];
  dtInicio: string;

  constructor(
    private service: AlunoService,
    private router: Router,
    private pessoaService: PessoaService,
    private cursoService: CursoService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    
    this.pessoaService.findAll().subscribe(pessoas =>{
      this.pessoas = pessoas;
    });
    
    this.cursoService.findAll().subscribe(cursos =>{
      this.cursos = cursos;
    });

    this.service.findById(id).subscribe(aluno => {
      this.aluno = aluno;
      console.log(this.aluno)
    });

    this.service.findById(id).subscribe(aluno => {
      this.aluno = aluno;
      this.dtInicio = aluno.dtInicio.toString().substr(0, 10);
    });
    
  }
  salvar(): void {
      this.aluno.dtInicio = new Date(this.dtInicio);
      this.service.update(this.aluno).subscribe(() =>{
        this.service.showMessage("Aluno(a) alterado(a) com sucesso!");
        this.router.navigate(['/aluno/list']);
      })
  }

}
