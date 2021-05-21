import { MatriculaService } from './../../../../service/matricula.service'
import { Matricula } from './../../../../model/matricula.model';
import { Aluno } from './../../../../model/aluno.model';
import { Oferta } from './../../../../model/oferta.model';
import { DeleteConfirmComponent } from 'src/app/component/template/delete-confirm/delete-confirm.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AlunoService } from 'src/app/service/aluno.service';
import { OfertaService } from 'src/app/service/oferta.service';

@Component({
  selector: 'app-matricula-list',
  templateUrl: './matricula-list.component.html',
  styleUrls: ['./matricula-list.component.css'],
})
export class MatriculaListComponent implements OnInit {

  matriculas: Matricula[] = [];
  alunos: Aluno[] = [];
  ofertas: Oferta[] = [];

  displayedColumns: string[] = ['id', 'aluno', 'oferta', 'acao'];
  constructor(
    private service: MatriculaService,
    private alunoService: AlunoService,
    private ofertaService: OfertaService,
    public dialog: MatDialog
    ) { }

  atualizarTabela(): void {
    this.service.findAll().subscribe(matriculas => {
      this.matriculas = matriculas;
    });

    this.alunoService.findAll().subscribe(alunos => {
      this.alunos = alunos;
    });

    this.ofertaService.findAll().subscribe(ofertas => {
      this.ofertas = ofertas;
    });

  }
  ngOnInit(): void {
    this.atualizarTabela();
  }

  excluir(matricula: Matricula): void {

    const dialogRef = this.dialog.open(DeleteConfirmComponent, {
      data: {
        message: `Deseja realmente excluir a matricula de ${matricula.oferta.disciplina.nmDisciplina} do aluno(a) ${matricula.aluno.pessoa.nmPessoa}?`,
        buttonText: {
          ok: 'Excluir',
          cancel: 'Desistir'
        }
      }
    })

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.service.delete(matricula).subscribe(() => {
          this.service.showMessage("Matricula excluída com sucesso com sucesso");
          this.atualizarTabela();
        });
      }
    })
  }

}
