import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	static List<ContaPoupanca> contasp = new ArrayList<ContaPoupanca>();
	static List<ContaEspecial> contase = new ArrayList<ContaEspecial>();
	static List<PessoaFisica> clientespf = new ArrayList<PessoaFisica>();
	static List<PessoaJuridica> clientespj = new ArrayList<PessoaJuridica>();
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		@SuppressWarnings("deprecation")
		PessoaFisica luiz = new PessoaFisica(1, "Luiz", "Anápolis", "999", new Date(2002, 12, 1), "Masculino");
		@SuppressWarnings("deprecation")
		PessoaFisica maria = new PessoaFisica(2, "Maria", "Goiânia", "888", new Date(2001, 9, 10), "Feminino");

		System.out.println(luiz.getIdade());
		System.out.println(maria.getIdade());
		
		PessoaJuridica apple = new PessoaJuridica(3, "Apple SA", "Califónia", "111", "Tecnologia");
		PessoaJuridica padaria = new PessoaJuridica(4, "Pão de Queijo e Companhia", "Setor Universitário", "222", "Alimentos");
	
		ContaEspecial ceLuiz = new ContaEspecial(luiz, 1000.0, 1000);
		ContaPoupanca cpMaria = new ContaPoupanca(maria, 500.0, 50);
		ContaEspecial ceApple = new ContaEspecial(apple, 100000.0, 1000000);
		ContaPoupanca cpPadaria = new ContaPoupanca(padaria, 1500.0, 5000);
		
		Main.clientespf.add(luiz);
		Main.clientespf.add(maria);
		Main.clientespj.add(apple);
		Main.clientespj.add(padaria);
		
		Main.contase.add(ceLuiz);
		Main.contasp.add(cpMaria);
		Main.contase.add(ceApple);
		Main.contasp.add(cpPadaria);
		
		Menu menu = new Menu();
		System.out.println("-------------------------");
		System.out.println("---- Seja Bem Vindo!-----");
		System.out.println("-------------------------");
		menu.menuPrincipal(sc);
		System.out.println("-------------------------");
		System.out.println("-- Programa encerrado!---");
		System.out.println("------- Até Mais!--------");
		System.out.println("-------------------------");
		
		sc.close();

	}

}