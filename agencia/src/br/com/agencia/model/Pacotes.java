package br.com.agencia.model;

import java.util.Calendar;
import java.util.Date;

public class Pacotes {
	private int id;
	private String destino;
	private String origem;
	private Date data_ida;
	private Date data_volta;
	private float preco;
	private int dias_pacote;
	
	Voos voo_ida;
	Voos voo_volta;
	Hoteis hotel;
	
	
	public Pacotes(String destino, String origem, Date data_ida, Date data_volta) {
		this.destino = destino;
		this.origem = origem;
		this.data_ida = data_ida;
		this.data_volta = data_volta;
	}

	public Pacotes(int id, String destino, String origem, Date data_ida, Date data_volta, float preco,
			int dias_pacote, Voos voo_ida, Voos voo_volta, Hoteis hotel) {
		this.id = id;
		this.destino = destino;
		this.origem = origem;
		this.data_ida = data_ida;
		this.data_volta = data_volta;
		this.preco = preco;
		this.dias_pacote = dias_pacote;
		this.voo_ida = voo_ida;
		this.voo_volta = voo_volta;
		this.hotel = hotel;
	}
	
	

	public Pacotes(String destino, String origem, Date data_ida, Date data_volta,
			Voos voo_ida, Voos voo_volta, Hoteis hotel) {
		this.destino = destino;
		this.origem = origem;
		this.data_ida = data_ida;
		this.data_volta = data_volta;
		this.voo_ida = voo_ida;
		this.voo_volta = voo_volta;
		this.hotel = hotel;
	}
	
	

	public Pacotes(int id, String destino, String origem, Date data_ida, Date data_volta, Voos voo_ida,
			Voos voo_volta, Hoteis hotel) {
		this.id = id;
		this.destino = destino;
		this.origem = origem;
		this.data_ida = data_ida;
		this.data_volta = data_volta;
		this.voo_ida = voo_ida;
		this.voo_volta = voo_volta;
		this.hotel = hotel;
	}

	public Pacotes() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Date getData_ida() {
		return data_ida;
	}

	public void setData_ida(Date data_ida) {
		this.data_ida = data_ida;
	}

	public Date getData_volta() {
		return data_volta;
	}

	public void setData_volta(Date data_volta) {
		this.data_volta = data_volta;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getDias_pacote() {
		return dias_pacote;
	}

	public void setDias_pacote(int dias_pacote) {
		this.dias_pacote = dias_pacote;
	}

	public Voos getVoo_ida() {
		return voo_ida;
	}

	public void setVoo_ida(Voos voo_ida) {
		this.voo_ida = voo_ida;
	}

	public Voos getVoo_volta() {
		return voo_volta;
	}

	public void setVoo_volta(Voos voo_volta) {
		this.voo_volta = voo_volta;
	}

	public Hoteis getHotel() {
		return hotel;
	}

	public void setHotel(Hoteis hotel) {
		this.hotel = hotel;
	}
	
	public static String formatDate(Date dataFormatada) {
		if (dataFormatada != null) {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(dataFormatada);

	        int dia = calendar.get(Calendar.DAY_OF_MONTH);
	        int mes = calendar.get(Calendar.MONTH) + 1;
	        int ano = calendar.get(Calendar.YEAR);
	        String dataformatada = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/"+ ano;
	        return dataformatada;
		} else {
			return "Data não especificada";
		}
		
	}

	@Override
	public String toString() {
		String data_ida_formatada = formatDate(data_ida);
		String data_volta_formatada = formatDate(data_volta);
		return "Pacote: id = " + id + ", destino = " + destino + ", origem = " + origem + ", data de ida=" + data_ida_formatada
				+ ", data de volta=" + data_volta_formatada + ", preço = " + preco + ", dias de viagem = " + dias_pacote + ";\n Voo de ida = "
				+ voo_ida + ";\n Voo de volta = " + voo_volta + ";\n Hotel = " + hotel + ";";
	}
	
	
}
