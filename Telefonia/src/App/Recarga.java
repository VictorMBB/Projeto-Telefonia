package App;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Recarga {
	private GregorianCalendar data;
	private float valor;
	
	//Construtor de um objeto do tipo Recarga com os atributos data e valor 
	public Recarga(GregorianCalendar data, float valor) {
		this.data = data;
		this.valor = valor;
	}
	public GregorianCalendar getData() {
		return this.data;
	}
	public float getValor() {
		return this.valor;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return " Data da recarga= " + formato.format(data.getTime()) + ", valor= R$ " + String.format("%.2f",valor) + " ";
	}
}
