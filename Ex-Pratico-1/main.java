import java.time.LocalDate;
import java.util.*;

public class main {

	public static void main(String[] args) {
		
		System.out.println("Main iniciado!\n");
		LocalDate date1 = LocalDate.of(2000, 1, 1);
		LocalDate date2 = LocalDate.of(2000, 1, 1);
		
		PessoaFisica pf1 = new PessoaFisica(1, "Márcio", "575", 123123123, date1, "Masculino");
		System.out.println("Pessoa Física: "+pf1.getNome()+" criada!");
		PessoaJuridica pj1 = new PessoaJuridica(2, "OWC Corp", "Silicon Valley", 123123123, "Tech");
		System.out.println("Pessoa Jurídica: "+pj1.getNome()+" criada!\n");
		
		ContaPoupanca cp1 = new ContaPoupanca(pf1, 0.03);
		System.out.println("Conta Poupança criada para "+cp1.getCliente().getNome()+" com número: "+cp1.getNrConta());
		ContaEspecial ce1 = new ContaEspecial(pj1, 100000);
		System.out.println("Conta Especial criada para "+ce1.getCliente().getNome()+" com número: "+ce1.getNrConta());
		
		System.out.println("Cliente "+cp1.getCliente().getNome()+", tem saldo: "+cp1.getSaldo());
		System.out.println("Depositando 10000.0");
		cp1.deposito(10000);
		System.out.println("Novo saldo: "+cp1.getSaldo());
		
		System.out.println("\nCliente"+ce1.getCliente().getNome()+", tem saldo: "+ce1.getSaldo());
		System.out.println("Transferindo 5000 de "+cp1.getCliente().getNome()+" para "+ce1.getCliente().getNome());
		System.out.println(cp1.transferir(5000, cp1, ce1));
		System.out.println("\nCliente"+ce1.getCliente().getNome()+", tem saldo: "+ce1.getSaldo());
		System.out.println("Cliente"+cp1.getCliente().getNome()+", tem saldo: "+cp1.getSaldo());
		
		System.out.println("\nFazendo saque além do saldo em conta Especial");
		System.out.println("Saldo: "+ce1.getSaldo());
		System.out.println(ce1.sacar(6000, ce1.getLimite()));
		System.out.println("Novo Saldo: "+ce1.getSaldo());
		

	}
}
