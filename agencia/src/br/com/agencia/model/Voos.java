package br.com.agencia.model;

import java.util.Calendar;
import java.util.Date;

public class Voos {
	private int id;
	private String empresa;
	private String cidade_origem;
	private String cidade_destino;
	private String aeroporto_decolagem;
	private String aeroporto_pouso;
	private String hora_decolagem;
	private String hora_pouso;
	private Date dia_decolagem;
	private Date dia_pouso;
	private float preco;

	
	public Voos(int id, String empresa, String cidade_origem, String cidade_destino, String aeroporto_decolagem, String aeroporto_pouso,
			String hora_decolagem, String hora_pouso, Date dia_decolagem, Date dia_pouso,  float preco) {
		this.id = id;
		this.empresa = empresa;
		this.preco = preco;
		this.aeroporto_decolagem = aeroporto_decolagem;
		this.aeroporto_pouso = aeroporto_pouso;
		this.hora_decolagem = hora_decolagem;
		this.hora_pouso = hora_pouso;
		this.dia_decolagem = dia_decolagem;
		this.dia_pouso = dia_pouso;
		this.cidade_origem = cidade_origem;
		this.cidade_destino = cidade_destino;
	}
	
	public Voos(String empresa, String cidade_origem, String cidade_destino, String aeroporto_decolagem, String aeroporto_pouso,
			String hora_decolagem, String hora_pouso, Date dia_decolagem, Date dia_pouso,  float preco) {
		this.empresa = empresa;
		this.preco = preco;
		this.aeroporto_decolagem = aeroporto_decolagem;
		this.aeroporto_pouso = aeroporto_pouso;
		this.hora_decolagem = hora_decolagem;
		this.hora_pouso = hora_pouso;
		this.dia_decolagem = dia_decolagem;
		this.dia_pouso = dia_pouso;
		this.cidade_origem = cidade_origem;
		this.cidade_destino = cidade_destino;
	}

	public Voos() {
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getAeroporto_decolagem() {
		return aeroporto_decolagem;
	}

	public void setAeroporto_decolagem(String aeroporto_decolagem) {
		this.aeroporto_decolagem = aeroporto_decolagem;
	}

	public String getAeroporto_pouso() {
		return aeroporto_pouso;
	}

	public void setAeroporto_pouso(String aeroporto_pouso) {
		this.aeroporto_pouso = aeroporto_pouso;
	}

	public String getHora_decolagem() {
		return hora_decolagem;
	}

	public void setHora_decolagem(String hora_decolagem) {
		this.hora_decolagem = hora_decolagem;
	}

	public String getHora_pouso() {
		return hora_pouso;
	}

	public void setHora_pouso(String hora_pouso) {
		this.hora_pouso = hora_pouso;
	}

	public Date getDia_decolagem() {
		return dia_decolagem;
	}

	public void setDia_decolagem(Date dia_decolagem) {
		this.dia_decolagem = dia_decolagem;
	}

	public Date getDia_pouso() {
		return dia_pouso;
	}

	public void setDia_pouso(Date dia_pouso) {
		this.dia_pouso = dia_pouso;
	}

	public String getCidade_origem() {
		return cidade_origem;
	}

	public void setCidade_origem(String cidade_origem) {
		this.cidade_origem = cidade_origem;
	}

	public String getCidade_destino() {
		return cidade_destino;
	}

	public void setCidade_destino(String cidade_destino) {
		this.cidade_destino = cidade_destino;
	}
	
	public static String formatDate(Date datavoo) {
		if (datavoo != null) {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(datavoo);

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
		String data_decolagem_formatada = formatDate(dia_decolagem);
		String data_pouso_formatada = formatDate(dia_pouso);
		return "Voos: id = " + id + ", empresa = " + empresa + ", cidade de origem = " + cidade_origem + ", cidade de destino = "
				+ cidade_destino + ", aeroporto de origem = " + aeroporto_decolagem + ", aeroporto de destino="
				+ aeroporto_pouso + ", horário de decolagem = " + hora_decolagem + ", horário de pouso = " + hora_pouso
				+ ", data de saída=" + data_decolagem_formatada + ", data de chagada = " + data_pouso_formatada + ", preço = " + preco + ";";
	}
	
	
	
}
