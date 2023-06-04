package App;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PrePago extends Assinante {
	
	private int numRecargas;
	private float creditos;
	private ArrayList<Recarga> recargas = new ArrayList<>();
	
	public PrePago(long cpf, String nome, long numero) {
		super(cpf, nome, numero);
		this.numRecargas = 0;
	}
	
	@Override
	public void fazerChamada(GregorianCalendar data, int duracao) {
		
		
		if(creditos < duracao*1.45f) {
			
			System.out.println("Não há créditos suficientes para completar esta ligação!");
		} else {
			
			
			chamadas.add(new Chamada(data,duracao));
			this.numChamadas += 1;
			this.creditos -= duracao * 1.45f;
			
		}
	}
	
	public void recarregar(GregorianCalendar data, float valor) {
		
		recargas.add(new Recarga(data,valor));
		numRecargas++;//Atualizar numRecarga
		creditos += valor;//Atualizar creditos
		
	}
	
	@Override
	public void imprimirFatura(int mes, int ano) {
		
		float valorChamada;// variável para o valor de cada chamada
		float totalChamadas = 0; // variável para armazenar o total das chamadas do mês
		
		System.out.println("Fatura do(s) assinante(s) para o mês ");
		System.out.println(this.toString());
		
		
		for (Chamada chamada : chamadas) {
			// verifica se há alguma chamada realizada no mês desejado
			if (chamada.getData().get(GregorianCalendar.MONTH) == mes && chamada.getData().get(GregorianCalendar.YEAR) == ano) {
				
				System.out.print(chamada.toString());//imprime data e duração de cada chamada
				valorChamada = chamada.getDuracao() * 1.45f;
				System.out.println(" Custo: R$ "+ String.format("%.2f", valorChamada));						
				totalChamadas += (float)chamada.getDuracao() * 1.45f; 
				
				
			}
		}
		
		float totalRecargas = 0; // variável para armazenar o total de recargas do mês
		
		
		
		for (Recarga recarga : recargas) {
			
			if (recarga.getData().get(GregorianCalendar.MONTH) == mes && recarga.getData().get(GregorianCalendar.YEAR) == ano) {
				System.out.println(recarga.toString());
				totalRecargas += recarga.getValor();
			}
		}
		
		
		System.out.println("Total em chamadas: R$ " + String.format("%.2f", totalChamadas));
		System.out.println("Total em recargas: R$ " + String.format("%.2f", totalRecargas));
		System.out.println("Créditos: R$ " + String.format("%.2f", creditos));
		
	}
}