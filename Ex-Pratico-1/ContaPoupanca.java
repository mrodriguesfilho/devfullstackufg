public class ContaPoupanca extends Conta{

  private double txCorrecao;

  public ContaPoupanca(Pessoa cliente, double txCorrecao) {
    super(cliente);
    this.txCorrecao = txCorrecao;
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

  public double atualizaRendimento(PessoaFisica pessoa) {
	  return 0.0;
  }
}
