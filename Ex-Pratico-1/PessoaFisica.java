import java.time.LocalDate;
import java.util.*;

public class PessoaFisica extends Pessoa{
  private int cpf;
  private LocalDate dtNascimento;
  private String genero;

  public PessoaFisica(int id, String nome, String endereco, int cpf, LocalDate dtNascimento, String genero) {
    super(id, nome, endereco);
    this.cpf = cpf;
    this.dtNascimento = dtNascimento;
    this.genero = genero;
  }
  
  public int getCpf() {
    return cpf;
  }
  
  public void setCpf(int cpf) {
    this.cpf = cpf;
  }

  public LocalDate getDtNascimento() {
    return dtNascimento;
  }

  public void setDtNascimento(LocalDate dtNascimento) {
    this.dtNascimento = dtNascimento;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }
}
