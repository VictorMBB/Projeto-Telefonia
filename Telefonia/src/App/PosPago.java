package App;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PosPago extends Assinante {
	
	private float assinatura;
	
	public PosPago(long cpf, String nome, long numero, float assinatura) {
		
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}
	
	@Override
	public void fazerChamada(GregorianCalendar data, int duracao) {
		
		chamadas.add(new Chamada(data, duracao));
		this.numChamadas += 1; // Atualiza numChamadas
		
	}
	
	@Override
	public void imprimirFatura(int mes, int ano) {
		float valorChamada, totalFatura = 0;
		
		// Imprimindo dados do Assinante
		System.out.println(this.toString());
		
		// Checando existência de chamadas e calculando o valor das chamadas e total
		// fatura
		for (Chamada chamada : chamadas) {
			
			if (chamada.getData().get(Calendar.MONTH) == mes && chamada.getData().get(Calendar.YEAR) == ano) {// Comparando o mes e ano inseridos
				System.out.print(chamada.toString());
				valorChamada = chamada.getDuracao() * 1.04f;
				System.out.println(" Custo: R$ " + String.format("%.2f", valorChamada));
				totalFatura += valorChamada;
			}
			
		}
		
		totalFatura = totalFatura + this.assinatura;
		System.out.println("Total da fatura = R$ " + String.format("%.2f", totalFatura));
	}
}
