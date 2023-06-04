package App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telefonia {
	
	private int numAssinantes;
	private ArrayList<Assinante> assinantes = new ArrayList<>();
	
	private static Scanner input = new Scanner(System.in);
	
	public Telefonia() {
		numAssinantes = 0;
	}
	
	public boolean validarCPF(String cpf) {
		// Remova qualquer formatação do CPF
		cpf = cpf.replaceAll("[^0-9]", "");
		
		// Verifique se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}
		
		// Verifique se todos os dígitos são iguais (CPF inválido)
		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}
		
		// Verifique os dígitos verificadores
		int soma = 0;
		int resto;
		
		for (int i = 0; i < 9; i++) {
			soma += Integer.parseInt(cpf.substring(i, i + 1)) * (10 - i);
		}
		
		resto = 11 - (soma % 11);
		if (resto == 10 || resto == 11) {
			resto = 0;
		}
		
		if (resto != Integer.parseInt(cpf.substring(9, 10))) {
			return false;
		}
		
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Integer.parseInt(cpf.substring(i, i + 1)) * (11 - i);
		}
		
		resto = 11 - (soma % 11);
		if (resto == 10 || resto == 11) {
			resto = 0;
		}
		
		if (resto != Integer.parseInt(cpf.substring(10, 11))) {
			return false;
		}
		
		return true;
	}
	
	public void cadastrarAssinante(Assinante assinante) {
		
		assinantes.add(assinante);
		
		numAssinantes++;
	}
	
	public void listarAssinantes() {
		
		Collections.sort(assinantes, (a1, a2) -> a1.getNome().compareTo(a2.getNome()));
		
		System.out.println("========================== PÓS-PAGOS ==========================");
		
		for(Assinante assinante : assinantes) {
			if(assinante instanceof PosPago) {
				assinante.toString();
			}
		}
		
		System.out.println("========================== PRÉ-PAGOS ==========================");
		
		for(Assinante assinante : assinantes) {
			if(assinante instanceof PrePago) {
				assinante.toString();
			}
		}
	}
	
	public void fazerChamada(Assinante assinante) {
		
		System.out.println("Qual a duração da chamada?");
		int inputDuracao = input.nextInt();
		System.out.println("Insira a data da chamada no formato dd/mm/aaaa");
		input.nextLine();
		String inputData = input.nextLine();
		SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = (Date) frmt.parse(inputData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GregorianCalendar dataChamada = new GregorianCalendar();
		dataChamada.setTime(data);
		assinante.fazerChamada(dataChamada, inputDuracao);
		System.out.println("Chamada realizada!");
	}
	
	public void fazerRecarga(PrePago prePago) {
		
		System.out.print("Qual o valor da recarga? ");
		float valorRecarga = input.nextFloat();
		System.out.println("Insira a data da recarga no formato dd/mm/aaaa ");
		input.nextLine();
		String inputData = input.nextLine();
		SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = null;
		try {
			data = (Date) frmt.parse(inputData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		GregorianCalendar dataRecarga = new GregorianCalendar();
		dataRecarga.setTime(data);
		prePago.recarregar(dataRecarga, valorRecarga);
		System.out.println("Recarga realizado!");
		
	}
	
	private Assinante localizarAssinante(long cpf) {
		for (int i = 0; i < numAssinantes; i++) {
			if (assinantes.get(i).getCpf() == cpf) {
				return assinantes.get(i);
			}
		}
		return null;
	}
	
	public void imprimirFaturas() {
		// Retorna o método imprimirFatura em posições não vazias do vetor posPagos
		
		System.out.println("Informe o número do mês da fatura");
		int inputMes = input.nextInt() - 1;// o mês no GregorianCalendar conta a partir de 0
		System.out.println("Informe o ano da fatura com 4 dígitos");
		int inputAno = input.nextInt();

		System.out.println("========== PRÉ-PAGOS =================");
		
		for (Assinante assinante : assinantes) {
			
			if(assinante instanceof PrePago){
				assinante.imprimirFatura(inputMes, inputAno);
			}
		}
		
		System.out.println("========== PÓS-PAGOS =================");
		for (Assinante assinante : assinantes) {
			
			if(assinante instanceof PosPago){
				assinante.imprimirFatura(inputMes, inputAno);
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
				System.out.println("Qual tipo de assinatura desejada?: ");
				System.out.print("1 - Pós-pago\n2 - Pré-pago\n");
				int plano = input.nextInt();				
				
				if(plano == 1) {
					System.out.println("Quais são os dados do assinantes?");
					System.out.print("Digite o CPF: ");
					String inputCpf = input.next();
					
					if (!telefonia.validarCPF(inputCpf)) {
						System.out.println("CPF inválido!");
						return;
					}
					
					long cpf = Long.parseLong(inputCpf);
					
					System.out.print("Digite seu Nome: ");
					input.nextLine(); // consumir o \n do nextLong anterior
					String inputNome = input.nextLine();
					
					System.out.print("Digite o numero desejado: ");
					Long inputNumero = input.nextLong();
					
					System.out.print("Digite o valor do plano: ");
					float inputAssinatura = input.nextFloat();
					
					Assinante assinante = new PosPago(cpf,inputNome,inputNumero,inputAssinatura);
					telefonia.cadastrarAssinante(assinante);
					
				} else if(plano == 2) {
					
					System.out.println("Quais são os dados do assinantes?");
					System.out.print("Digite o CPF: ");
					String inputCpf = input.next();
					
					if (!telefonia.validarCPF(inputCpf)) {
						System.out.println("CPF inválido!");
						return;
					}
					
					long cpf = Long.parseLong(inputCpf);
					
					System.out.print("Digite seu Nome: ");
					input.nextLine(); // consumir o \n do nextLong anterior
					String inputNome = input.nextLine();
					
					System.out.print("Digite o numero desejado: ");
					Long inputNumero = input.nextLong();
					
					Assinante assinante = new PrePago(cpf,inputNome,inputNumero);
					telefonia.cadastrarAssinante(assinante);
					
				} else {
					
					System.out.println("Entrada inválida, por favor digite 1 para pós-pago ou 2 para pré-pago");
					
				}
				
				break;
				case 2:
				telefonia.listarAssinantes();
				break;
				case 3:
				
				System.out.print("Qual é o tipo do assinante?\n1 - Pós-pago\n2 - Pré-pago\n");
				plano = input.nextInt();
				System.out.print("Digite o CPF: ");
				long inputCpf = input.nextLong();
				
				Assinante assinanteFound = telefonia.localizarAssinante(inputCpf);
				
				if(assinanteFound != null){
					
					telefonia.fazerChamada(assinanteFound);
				} else {
					System.out.println("Assinante não encontrado!");
				}
				
				break;
				case 4:
				
				System.out.print("Informe o CPF do assinante: ");
				inputCpf = input.nextLong();
				
				assinanteFound = telefonia.localizarAssinante(inputCpf);
				
				if(assinanteFound != null) {
					
					if(assinanteFound instanceof PrePago) {
						
						PrePago assinantePre = (PrePago)assinanteFound;
						
						telefonia.fazerRecarga(assinantePre);
					} else {
						System.out.println("Assinante de plano pós-pago!");
					}
				} else {
					System.out.println("Assinante não encontrado!");
					
				}
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
