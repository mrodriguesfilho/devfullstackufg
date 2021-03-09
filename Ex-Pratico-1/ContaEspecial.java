public class ContaEspecial extends Conta{

  public ContaEspecial(Pessoa cliente, double limite) {
		super(cliente);
		this.limite = limite;
	}

private double limite;

  public double getLimite() {
    return limite;
  }
  
  public void setLimite(double limite) {
    this.limite = limite;
  }

  protected boolean temSaldo(){
    return false;
  }

  public boolean abrirConta(){
    return false;
  }


}
