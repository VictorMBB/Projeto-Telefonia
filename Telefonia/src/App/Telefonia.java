package App;

import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telefonia {

	private int numPrePagos;
	private PrePago[] prePagos;
	private int numPosPagos;
	private PosPago[] posPagos;

	private static Scanner input = new Scanner(System.in);

	public Telefonia() {
		numPrePagos = 0;
		prePagos = new PrePago[10];
		numPosPagos = 0;
		posPagos = new PosPago[10];
	}

	public void cadastrarAssinante() {
		// criar um novo objeto Assinante
		System.out.println("Qual tipo de assinatura você deseja:");
		System.out.println("1 - Pós-pago\n2 - Pré-pago\n");

		int plano = input.nextInt();

		if (plano == 1) {

			System.out.println("Digite o CPF: ");
			long inputCpf = input.nextLong();

			System.out.println("Digite o Nome: ");
			input.nextLine(); // consumir o \n do nextLong anterior
			String inputNome = input.nextLine();

			System.out.println("Digite o numero: ");
			int inputNumero = input.nextInt();

			System.out.println("Digite o valor da assinatura: ");
			float inputAssinatura = input.nextFloat();

			if(localizarPosPago(inputCpf) != null){
				System.out.println("Este CPF já está cadastrado com um número!");
			} else {

				this.posPagos[numPosPagos] = new PosPago(inputCpf, inputNome, inputNumero, inputAssinatura);
				numPosPagos += 1;

			}

		}

		if (plano == 2) {
			System.out.println("Digite o CPF: ");
			long inputCpf = input.nextLong();

			System.out.println("Digite seu Nome: ");
			input.nextLine(); // consumir o \n do nextLong anterior
			String inputNome = input.nextLine();

			System.out.println("Digite o numero desejado: ");
			int inputNumero = input.nextInt();

			if(localizarPrePago(inputCpf) != null){
				System.out.println("Este CPF já está cadastrado com um número");
			} else {

				this.prePagos[numPrePagos] = new PrePago(inputCpf, inputNome, inputNumero);
				numPrePagos += 1;
				
			}

		}
	}

	public void listarAssinantes() {
		System.out.println("Assinantes:");

		for(int i = 0; i < numPosPagos; i++) {
			System.out.println(posPagos[i].toString());
		}

		for(int j = 0; j < numPrePagos; j++) {
			System.out.println(prePagos[j].toString());
		}

	}

	public void fazerChamada() {
		// Localiza o assinante pós-pago pelo CPF
		System.out.println("Qual é o tipo do assinante?\n1 - Pós-pago\n2 - Pré-pago\n");
		int plano = input.nextInt();
		System.out.println("Digite o CPF: ");
		long inputCpf = input.nextLong();

		if (plano == 1) {
			PosPago assinantePosPago = localizarPosPago (inputCpf);//localiza o assinante pelo CPF
			if (assinantePosPago == null) {
				System.out.println("CPF não encontrado!");
			} else {
				System.out.println("Qual a duracao da chamada?");
				int inputDuracao = input.nextInt();
				System.out.print("Qual é o dia? ");
				int inputDay = input.nextInt();
				System.out.print("Qual é o mês? ");
				int inputMonth = input.nextInt() - 1;
				GregorianCalendar dataChamada = new GregorianCalendar(2023, inputMonth, inputDay);
				assinantePosPago.fazerChamada(dataChamada, inputDuracao);
			}
		}

		if (plano == 2) {
			PrePago assinantePrePago = localizarPrePago (inputCpf);//localiza o assinante pelo CPF
			if (assinantePrePago == null) {
				System.out.println("CPF não encontrado!");
			} else {
				System.out.println("Qual a duracao da chamada?");
				int inputDuracao = input.nextInt();
				GregorianCalendar dataChamada = new GregorianCalendar();
				assinantePrePago.fazerChamada(dataChamada, inputDuracao);
			}
		}

	}

	public void fazerRecarga() {
		
		System.out.println("Digite o CPF do assinante: ");
		long inputCpf = input.nextLong();
		PrePago assinantePrePago = localizarPrePago(inputCpf);//localiza o assinante pelo CPF
		
		if(assinantePrePago == null){
			System.out.println("CPF não encontrado!");
		} else {
			System.out.println("Qual o valor da recarga?");
			float valor = input.nextFloat();
			GregorianCalendar dataRecarga = new GregorianCalendar();
			
			assinantePrePago.recarregar(dataRecarga, valor);
		}
	}

	private PrePago localizarPrePago(long cpf) {
		for (int i = 0; i < numPrePagos; i++) {
				if (prePagos[i].getCpf() == cpf) {
					return prePagos[i];
				}
			}

		return null;
	}

	private PosPago localizarPosPago(long cpf) {
		for (int i = 0; i < numPosPagos; i++) {
				if (posPagos[i].getCpf() == cpf) {
					return posPagos[i];
				}
			}

		return null;
	}

	public void imprimirFaturas() {
		// Retorna o método imprimirFatura em posições não vazias do vetor posPagos

		System.out.println("Informe o número do mês da fatura");
		int inputMes = input.nextInt() - 1;// o mês no GregorianCalendar conta a partir de 0

		System.out.println("============== PÓS-PAGOS ==============");
		for (int i = 0; i < posPagos.length; i++) {
			if (posPagos[i] != null) {
				posPagos[i].imprimirFatura(inputMes);
			}
		}
		
		System.out.println("============== PRÉ-PAGOS ==============");
		for (int i = 0; i < prePagos.length; i++) {
			if (prePagos[i] != null) {
				prePagos[i].imprimirFatura(inputMes);
			}
		}

	}

	public static void main(String[] args) {
		Telefonia telefonia = new Telefonia();

		int opcao;

		do {
			System.out.println("Escolha uma opção:");
			System.out.println("1 - Cadastrar Assinante");
			System.out.println("2 - Listar Assinantes");
			System.out.println("3 - Fazer Chamada");
			System.out.println("4 - Fazer Recarga");
			System.out.println("5 - Imprimir Fatura");
			System.out.println("0 - Sair");
			opcao = input.nextInt();

			switch (opcao) {
				case 1:
					telefonia.cadastrarAssinante();
					break;
				case 2:
					telefonia.listarAssinantes();
					break;
				case 3:
					telefonia.fazerChamada();
					break;
				case 4:
					telefonia.fazerRecarga();
					break;
				case 5:
					telefonia.imprimirFaturas();
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida!");
					break;
			}

		} while (opcao != 0);

		input.close();

	}
}
