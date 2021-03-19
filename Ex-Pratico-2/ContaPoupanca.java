import java.time.*;

public class ContaPoupanca extends Conta{

  private double txCorrecao;
  private LocalDate dataCriacao;
  
  public ContaPoupanca(Pessoa cliente, double txCorrecao, double saldo) {
    super(cliente, saldo);
    this.txCorrecao = txCorrecao;
    dataCriacao = LocalDate.now();
  }

  public double getTxCorrecao() {
    return txCorrecao;
  }

  public void setTxCorrecao(double txCorrecao) {
    this.txCorrecao = txCorrecao;
  }

  public boolean atualizaSaldoRendimento(){
	return false;
  }

  public boolean abrirConta(){
    return false;
  }

  public void atualizaRendimento(Conta conta) {
	  LocalDate dataCheck = LocalDate.now();
	  Period period = Period.between(dataCriacao, dataCheck);
	  if(period.getMonths() >= 12) {
		  conta.depositar(conta.getSaldo()*txCorrecao);
	  }
  }
}
