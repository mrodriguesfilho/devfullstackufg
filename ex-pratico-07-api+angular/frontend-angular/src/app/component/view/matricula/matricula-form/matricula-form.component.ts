import { Aluno } from './../../../../model/aluno.model';
import { AlunoService } from './../../../../service/aluno.service';
import { Oferta } from './../../../../model/oferta.model';
import { OfertaService } from './../../../../service/oferta.service';
import { MatriculaService } from './../../../../service/matricula.service';
import { Matricula } from './../../../../model/matricula.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-matricula-form',
  templateUrl: './matricula-form.component.html',
  styleUrls: ['./matricula-form.component.css']
})
export class MatriculaFormComponent implements OnInit {

  titulo : string = "Cadastrar novo Matricula";
  
  matricula: Matricula = {
    aluno: null,
    oferta: null,
  }

  alunos: Aluno[] = [];
  ofertas: Oferta[] = [];

  constructor(
    private service: MatriculaService,
    private router: Router,
    private alunoService: AlunoService,
    private ofertaService: OfertaService,
  ) { }

  ngOnInit(): void {
    this.alunoService.findAll().subscribe(alunos =>{
      this.alunos = alunos;
    });

    this.ofertaService.findAll().subscribe(ofertas =>{
      this.ofertas = ofertas;
    });
  }


  salvar(): void {
    console.log(this.matricula)
    this.service.create(this.matricula).subscribe(() =>{
      this.service.showMessage("Matricula cadastrada com sucessa.")
      this.router.navigate(['/matricula/list']);
    })
  }

}
