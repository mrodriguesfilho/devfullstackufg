public class Conta{
  private Pessoa cliente;
  private static int nrConta = 0;
  private double saldo;
  private Categoria tipo;
  private enum Categoria{
    Simples,
    Executiva,
    Premium,
    Personalite
  }

  public Conta(Pessoa cliente, double saldo){
    Conta.nrConta = Conta.nrConta +1;
    this.cliente = cliente;
    this.saldo = saldo;
  }

  public Pessoa getCliente() {
    return cliente;
  }
  
  public void setCliente(Pessoa cliente) {
    this.cliente = cliente;
  }

  public int getNrConta() {
    return nrConta;
  }

  public double getSaldo() {
    return saldo;
  }

  public Categoria getTipo() {
    return tipo;
  }

  public void setTipo(Categoria tipo) {
    this.tipo = tipo;
  }
  
  public void sacar(double valorSaque) throws AppError{
    
    double limite = 0;
    // Falar com o professor sobre essa implementação de conta que ele fez violando OOP

    try {
      if(saldo + limite >= valorSaque) {
		  saldo = saldo - valorSaque;
		  System.out.println("Saque realizado com sucesso!");
	  }else {
		  throw new AppError("Saldo insuficiente!");
	  }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
	  
  }
  
  private double debitar(double valorDebitar) {
	  saldo = saldo - valorDebitar; 
	  return saldo;
  }

  protected boolean temSaldo(double valor){
     
    if(saldo - valor >= 0){
      return true;
    }else{
      return false;
    }

  }
  
  public double depositar(double deposito) {
	  saldo = saldo + deposito;
	  return saldo;
  }
  
  public String transferir(double valorTransferencia, Conta contaDestino) throws AppError{
    try {
      if(this.temSaldo(valorTransferencia)) {
        throw new AppError("Saldo Insuficiente para fazer tranferência");
      }else {
        this.debitar(valorTransferencia);
        contaDestino.depositar(valorTransferencia);
        return ("Transfêrencia realizada com sucesso!");
      }
    } catch (Exception e) {
      return e.getMessage();
    }

  }
}