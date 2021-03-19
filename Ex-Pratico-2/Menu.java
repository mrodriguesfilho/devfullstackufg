import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class Menu {

	public void menuPrincipal(Scanner sc) {
		Integer escolha = 1;
		Conta conta;
		do {
			this.showMenuPrincipal();
			try {
				escolha = sc.nextInt();
				switch (escolha) {
				case 1:
					this.abrirConta(sc);
					break;

				case 2:
					conta = this.buscarConta(sc);
					this.menuConta(sc, conta);
					break;

				case 3:
					this.cadatrarCliente(sc);
					break;

				case 4:
					this.relatoriosContas(sc);
					break;

				case 5:
					System.out.println("5 - Sair");
					break;

				default:
					System.out.println("Opção Incorreta");
				}
			} catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 5;
			}
		} while (escolha != 5);

	}

	private void relatoriosContas(Scanner sc){
		System.out.println("---- Qual relatório deseja? ----");
		System.out.println("1 - Saldo das contas");
		System.out.println("2 - Total das contas");
		
		Integer opcao = sc.nextInt();

		if( opcao == 1){
			for (ContaEspecial ce : Main.contase) {
				System.out.println("Conta numero: "+ce.getNrConta()+" / Saldo ="+ce.getSaldo());
			}

			for (ContaPoupanca cp : Main.contasp) {
				System.out.println("Conta numero: "+cp.getNrConta()+" / Saldo ="+cp.getSaldo());
			}
		}else{
			Double saldoTotal = 0.0;
			for (ContaEspecial ce : Main.contase) {
				saldoTotal = saldoTotal + ce.getSaldo();
			}

			for(ContaPoupanca cp : Main.contasp){
				saldoTotal = saldoTotal +cp.getSaldo();
			}

			System.out.println("O Saldo total é: "+saldoTotal);
		}
	}

	private void abrirConta(Scanner sc){
		System.out.println("---- Nova Conta ----");
		System.out.println("Qual tipo de cliente para a conta?");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");

		Integer tipoCliente = sc.nextInt();

		if( tipoCliente == 1 ){
			System.out.println("Digite o seu CPF cadastrado: ");
			String cpf = sc.nextLine();
			PessoaFisica clienteEncontrado = null;
			for (PessoaFisica cliente : Main.clientespf) {

				if (cliente.getCpf().equals(cpf)) {
					clienteEncontrado = cliente;
					break;
				}
			}
	
			try {
				if( clienteEncontrado != null ){
					System.out.println("---- Nova Conta ----");
					System.out.println("Selecione o tipo de Conta");
					System.out.println("1 - Conta Poupança");
					System.out.println("2 - Conta Especial");

					Integer tipoConta = sc.nextInt();

					if( tipoConta == 1 ){
						Main.contasp.add(new ContaPoupanca(clienteEncontrado, 2, 0));
						System.out.println("Conta criada com sucesso!");
					}else{
						Main.contase.add(new ContaEspecial(clienteEncontrado, 1000, 0));
						System.out.println("Conta criada com sucesso!");
					}
				}else{
					throw new AppError("CPF não encontrado. Você deve se cadastrar primeiro!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}else{
			System.out.println("Digite o seu CNPJ cadastrado: ");
			String cnpj = sc.nextLine();
			PessoaJuridica clienteEncontrado = null;
			for (PessoaJuridica cliente : Main.clientespj) {

				if (cliente.getCnpj().equals(cnpj)) {
					clienteEncontrado = cliente;
					break;
				}
			}
	
			try {
				if( clienteEncontrado != null ){
					System.out.println("---- Nova Conta ----");
					System.out.println("Selecione o tipo de Conta");
					System.out.println("1 - Conta Poupança");
					System.out.println("2 - Conta Especial");

					Integer tipoConta = sc.nextInt();

					if( tipoConta == 1 ){
						Main.contasp.add(new ContaPoupanca(clienteEncontrado, 2, 0));
						System.out.println("Conta criada com sucesso!");
					}else{
						Main.contase.add(new ContaEspecial(clienteEncontrado, 1000, 0));
						System.out.println("Conta criada com sucesso!");
					}
				}else{
					throw new AppError("CNPJ não encontrado. Você deve se cadastrar primeiro!");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void cadatrarCliente(Scanner sc) {

		System.out.println("---- Novo Cliente -----");
		System.out.println("Selecione o tipo de Pessoa");
		System.out.println("1 - Pessoa Física");
		System.out.println("2 - Pessoa Jurídica");
		
		Integer tipo = sc.nextInt();
		
		
		System.out.println("--- Informe o ID -----");
		Integer id = sc.nextInt();

		System.out.println("--- Informe o Nome -----");
		String nome = sc.nextLine();
		
		System.out.println("--- Informe o Endereço -----");
		String endereco = sc.nextLine();
		
		if(tipo == 1) {
			System.out.println("--- Informe o CPF -----");
			String cpf = sc.nextLine();
			
			System.out.println("--- Informe a Data de Nascimento -----");
			String[] dtAux = sc.next().trim().split("/");
			
			Date dtNascimento = Date.from(Instant.parse(dtAux[2]+"-"+dtAux[2]+"-"+dtAux[0]+"T00:00:00Z"));
					
			System.out.println("--- Informe o Genero (M/F) -----");
			String genero = sc.next();
			
			Main.clientespf.add(new PessoaFisica(id, nome, endereco, cpf, dtNascimento, genero));
			
		}else {
			System.out.println("--- Informe o Nome -----");
			String cnpj = sc.nextLine();
			
			System.out.println("--- Informe o Endereço -----");
			String atividade = sc.nextLine();
			
			Main.clientespj.add(new PessoaJuridica(id, nome, endereco, cnpj, atividade));
		}

	}

	private void menuConta(Scanner sc, Conta conta) {
		
		Integer escolha = 1;
		do {
			this.showMenuConta(conta);
			try {
				escolha = sc.nextInt();
				Double vr;
				switch (escolha) {
				case 1: 
					// alterar conta
					break;
				case 2: 
					System.out.println("Informe o Valor do Depósito");
					vr = sc.nextDouble();
					conta.depositar(vr);
					break;
				case 3: 
					System.out.println("Informe o Valor para Saque");
					vr = sc.nextDouble();
					conta.sacar(vr);
					
					break;
				case 4: 
					Conta dest = this.buscarConta(sc);
					System.out.println("Informe o Valor para Transferência");
					vr = sc.nextDouble();
					conta.transferir(vr, dest);
					break;
				case 5:
					System.out.println("-------------------------");
					System.out.println("--- SALDO: R$ "+conta.getSaldo());
					System.out.println("-------------------------");
					
					break;
				}

			}  catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 6;
			}
		}while (escolha != 6);
	}

	private void showMenuPrincipal() {
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 - Abrir Nova Conta");
		System.out.println("2 - Selecionar Conta");
		System.out.println("3 - Cadastrar Cliente");
		System.out.println("4 - Relat-rios");
		System.out.println("5 - Sair");
		System.out.println("-------------------------");
	}

	private void showMenuConta(Conta conta) {
		System.out.println("-------------------------");
		System.out.println("Cliente: " + conta.getCliente().getNome());
		System.out.println("Nr Conta: " + conta.getNrConta());
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 - Alterar Conta");
		System.out.println("2 - Deposito");
		System.out.println("3 - Saque");
		System.out.println("4 - Transferência");
		System.out.println("5 - Saldo");
		System.out.println("6 - Sair");
		System.out.println("-------------------------");
	}

	public Conta buscarConta(Scanner sc) {


		Conta contaRetorno = null;
		do {
			System.out.println("------------------------------");
			System.out.println("----Qual o tipo da conta?-----");
			System.out.println("------------------------------");
			System.out.println("------1 - Conta Poupança------");
			System.out.println("------2 - Conta Especial------");
			System.out.println("------------------------------");
			Integer tipoConta = sc.nextInt();

			if( tipoConta == 1){
				System.out.println("------------------------------");
				System.out.println("---Digite o número da Conta---");
				System.out.println("------------------------------");
				int numeroConta = sc.nextInt();
				for (ContaPoupanca conta : Main.contasp) {
	
					if (conta.getNrConta() == (numeroConta)) {
						contaRetorno = conta;
						break;
					}
				}

			}else{
				System.out.println("------------------------------");
				System.out.println("---Digite o número da Conta---");
				System.out.println("------------------------------");
				int numeroConta = sc.nextInt();
				for (ContaEspecial conta : Main.contase) {
	
					if (conta.getNrConta() == (numeroConta)) {
						contaRetorno = conta;
						break;
					}
				}
			}

			if ( contaRetorno == null ){
				System.out.println("------------------------------");
				System.out.println("-----Conta Não Encontrada-----");
				System.out.println("------------------------------");				
			}

		} while ( contaRetorno == null );

		return contaRetorno;
	}
}
