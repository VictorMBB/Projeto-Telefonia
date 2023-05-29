package App;

import java.text.DecimalFormat;

public class Assinante {
	private long cpf;
	private String nome;
	private long numero;
	protected int numChamadas;
	protected Chamada[] chamadas;

	public Assinante(long cpf, String nome, long numero) {
		this.cpf = cpf;
		this.nome = nome;
		this.numero = numero;
		this.chamadas = new Chamada[10];
		this.numChamadas = 0;
	}

	public long getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		DecimalFormat formatadorCPF = new DecimalFormat("00000000000");
		String cpfFormatado = formatadorCPF.format(cpf);

		DecimalFormat formatadorNumero = new DecimalFormat("0000000000");
		String numeroFormatado = formatadorNumero.format(numero);

		return "Nome= " + nome + ", CPF= " + cpfFormatado.substring(0, 3) + "." +
				cpfFormatado.substring(3, 6) + "." + cpfFormatado.substring(6, 9) +
				"-" + cpfFormatado.substring(9) + ", Número= +55 (" + numeroFormatado.substring(0, 2) +
				") " + numeroFormatado.substring(2, 7) + "-" + numeroFormatado.substring(7);
	}

}
