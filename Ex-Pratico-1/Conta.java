import java.util.ArrayList;
import java.util.List;

public class Conta{
  private Pessoa cliente;
  private static int nrConta = 0;
  private double saldo;
  
  public Conta(Pessoa cliente){
    Conta.nrConta = Conta.nrConta +1;
    this.cliente = cliente;
    this.saldo = 0;
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

  public String sacar(double valorSaque, double limite){
   
	  if(saldo + limite >= valorSaque) {
		  saldo = saldo - valorSaque;
		  return "Saque realizado com sucesso!";
	  }else {
		  return "Saldo insuficiente!";
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
  
  public double deposito(double deposito) {
	  saldo = saldo + deposito;
	  return saldo;
  }
  
  public String transferir(double valorTransferencia, Conta contaOrigem, Conta contaDestino) {
	  if(!contaOrigem.temSaldo(valorTransferencia)) {
		  return "Saldo insuficiente para transferência";
	  }else {
		  contaOrigem.debitar(valorTransferencia);
		  contaDestino.deposito(valorTransferencia);
		  return "Transfêrencia realizada com sucesso!";
	  }
  }
}