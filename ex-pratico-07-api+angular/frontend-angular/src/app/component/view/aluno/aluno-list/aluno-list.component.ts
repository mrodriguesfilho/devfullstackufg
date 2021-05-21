import { AlunoService } from './../../../../service/aluno.service';
import { Aluno } from 'src/app/model/aluno.model';
import { DeleteConfirmComponent } from 'src/app/component/template/delete-confirm/delete-confirm.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-aluno-list',
  templateUrl: './aluno-list.component.html',
  styleUrls: ['./aluno-list.component.css']
})
export class AlunoListComponent implements OnInit {

  alunos: Aluno[] = [];
  displayedColumns: string[] = ['id', 'pessoa', 'curso', 'acao'];
  constructor(private service: AlunoService, public dialog: MatDialog) { }

  atualizarTabela(): void {
    this.service.findAll().subscribe(alunos => {
      this.alunos = alunos;
    });

  }
  ngOnInit(): void {
    this.atualizarTabela();
  }

/*  getEscolaridade<String>(escolaridade: Escolaridade): String {
    return Escolaridade[escolaridade];
  }*/

  excluir(aluno: Aluno): void {

    const dialogRef = this.dialog.open(DeleteConfirmComponent, {
      data: {
        message: `Deseja realmente excluir o(a) aluno(a) ${aluno.pessoa.nmPessoa}?`,
        buttonText: {
          ok: 'Excluir',
          cancel: 'Desistir'
        }
      }
    })

    dialogRef.afterClosed().subscribe((confirmed: boolean) => {
      if (confirmed) {
        this.service.delete(aluno).subscribe(() => {
          this.service.showMessage("Aluno(a) excluído(a) com sucesso");
          this.atualizarTabela();
        });
      }
    })
  }

}
