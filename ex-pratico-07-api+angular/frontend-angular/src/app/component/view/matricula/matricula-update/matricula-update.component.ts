import { Aluno } from './../../../../model/aluno.model';
import { AlunoService } from './../../../../service/aluno.service';
import { Oferta } from './../../../../model/oferta.model';
import { OfertaService } from './../../../../service/oferta.service';
import { Matricula } from './../../../../model/matricula.model';
import { MatriculaService } from './../../../../service/matricula.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-matricula-update',
  templateUrl: './../matricula-form/matricula-form.component.html',
  styleUrls: ['./../matricula-form/matricula-form.component.css']
})
export class MatriculaUpdateComponent implements OnInit {

  
  titulo : string = "Alterar dados da Matricula";
 
  matricula: Matricula = {
    aluno: null,
    oferta : null,
  }

  alunos: Aluno[] = [];
  ofertas: Oferta[] = [];

  constructor(
    private service: MatriculaService,
    private router: Router,
    private alunoService: AlunoService,
    private ofertaService: OfertaService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.paramMap.get('id');
    
    this.alunoService.findAll().subscribe(alunos =>{
      this.alunos = alunos;
    });

    this.ofertaService.findAll().subscribe(ofertas =>{
      this.ofertas = ofertas;
    });

    this.service.findById(id).subscribe(matricula => {
      this.matricula = matricula;
      console.log(this.matricula)
    });

    
  }
  salvar(): void {
      this.service.update(this.matricula).subscribe(() =>{
        this.service.showMessage("Matricula alterada com sucesso!");
        this.router.navigate(['/matricula/list']);
      })
  }

}
