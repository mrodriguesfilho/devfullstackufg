import { Pessoa } from './pessoa.model';
import { Curso } from './curso.model';

export interface Aluno {
    idAluno?: number;
    dtInicio: Date;
    ativo:  number;
    pessoa: Pessoa;
    curso: Curso;  
}