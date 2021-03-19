public class ContaEspecial extends Conta{

  public ContaEspecial(Pessoa cliente, double limite, double saldo) {
		super(cliente, saldo);
		this.limite = limite;
	}

  private double limite;

  public double getLimite() {
    return limite;
  }
  
  public void setLimite(double limite) {
    this.limite = limite;
  }


}
