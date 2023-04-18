package App;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Chamada {
	private GregorianCalendar data;
	private int duracao;
	
	public Chamada() {
	
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
		return "Chamada [data=" + formato.format(data) + ", duracao=" + duracao + "]";
	}
	
}