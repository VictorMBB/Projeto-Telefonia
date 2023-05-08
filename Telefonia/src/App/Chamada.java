package App;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Chamada {
	private GregorianCalendar data;
	private int duracao;

	// Criar uma instância do GregorianCalendar com a data e hora atual

	public Chamada(GregorianCalendar data, int duracao) {
		this.data = data;
		this.duracao = duracao;
	}

	public GregorianCalendar getData() {
		return this.data;
	}

	public int getDuracao() {
		return this.duracao;
	}

	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return "Chamada [data=" + formato.format(data.getTime()) + ", duracao=" + duracao + "]";
	}

}
