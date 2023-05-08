package App;

import java.util.GregorianCalendar;

public class PrePago extends Assinante {

	private int numRecargas;
	private float creditos;
	private Recarga[] recargas;

	public PrePago(long cpf, String nome, int numero) {
		super(cpf, nome, numero);
		this.numRecargas = 0;
		this.recargas = new Recarga[10];//iniciei o array com dez espaços de inicio mas podemos alterar depois
	}


	public void fazerChamada(GregorianCalendar data, int duracao) {

		//esta condição verifica se há créditos disponiveis ou espaço no vetor de chamadas para executar a chamada
		if(numChamadas == chamadas.length || creditos <= 0) {

			System.out.println("Não é possível completar esta ligação!");
		} else {
			//do contrário passamos para a posição numChamadas do array
			
			this.chamadas[numChamadas] = new Chamada(data,duracao);//chamada recebe a data e a duração passadas pelo usuário
			this.numChamadas += 1; //numChamadas aumenta +1
			this.creditos -= (float)duracao * 1.45f; //aqui ele atualiza o valor de crédito cobrando de acordo com a duração da chamada(fiz o casting para float porque o valor provido é double porém o requerido é float
			
		}
	}

	public void recarregar(GregorianCalendar data, float valor) {
		
		//condicional verificar se todas as recargas possíveis foram feitas
		if(numRecargas == recargas.length){
			
			System.out.println("Não é possível fazer mais recargas!");
		} else {

			//vamos para a posição numRecargas do array para fazer a nova recarga
			recargas[numRecargas] = new Recarga(data,valor);//novo objeto Recarga na posição vazia do vetor recargas
			numRecargas++;//Atualizar numRecarga
			creditos += valor;//Atualizar creditos
		}

	}

	public void imprimirFatura(int mes) {
		
		float valorChamada;// variável para o valor de cada chamada
		float totalChamadas = 0; // variável para armazenar o total das chamadas do mês
		
		System.out.println("Fatura do(s) assinante(s) para o mês ");
		System.out.println(this.toString());
		
		// laço for que percorre o array de chamadas do assinante e calcula o total de chamadas realizadas no mês
		for (int i = 0; i < numChamadas; i++) {
			// verifica se há alguma chamada realizada no mês desejado
			if (chamadas[i].getData().get(GregorianCalendar.MONTH) == mes) {
				
				System.out.print(chamadas[i].toString());//imprime data e duração de cada chamada
				valorChamada = (float)chamadas[i].getDuracao() * 1.45f;
				System.out.println(" Custo: R$ "+ String.format("%.2f", valorChamada));//imprime valor de cada chamada						
				totalChamadas += (float)chamadas[i].getDuracao() * 1.45f; // adiciona o valor da chamada ao total
				
			}
		}

		float totalRecargas = 0; // variável para armazenar o total de recargas do mês
		
		// laço for que percorre o array de recargas do assinante e calcula o total de recargas realizadas no mês
		for (int i = 0; i < numRecargas; i++) {
			// verifica se a recarga foi realizada no mês desejado
			Recarga recarga = recargas[i];
			if (recarga.getData().get(GregorianCalendar.MONTH) == mes) {
				System.out.println(recarga.toString());
				totalRecargas += recarga.getValor(); // adiciona o valor da recarga ao total
				
			}
		}
		
		// imprime o total do assinante
		System.out.println("Total em chamadas: R$ " + String.format("%.2f", totalChamadas));
		System.out.println("Total em recargas: R$ " + String.format("%.2f", totalRecargas));
		System.out.println("Créditos: R$ " + String.format("%.2f", creditos));

	}
}
