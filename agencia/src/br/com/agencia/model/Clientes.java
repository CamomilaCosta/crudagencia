package br.com.agencia.model;

import java.util.Calendar;
import java.util.Date;

public class Clientes {
	private int id;
	private String nome;
	private Date datanasc;
	private String email;
	
	public Clientes(int id, String nome, Date datanasc, String email) {
		this.id = id;
		this.nome = nome;
		this.datanasc = datanasc;
		this.email = email;
	}
	
	public Clientes(String nome, Date datanasc, String email) {
		this.nome = nome;
		this.datanasc = datanasc;
		this.email = email;
	}
	
	public Clientes() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Date getDatanasc() {
		return datanasc;
	}
	
	public void setDatanasc(Date datanasc) {
		this.datanasc = datanasc;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static String formatDate(Date datanasc) {
		if (datanasc != null) {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(datanasc);

	        // Extrair dia, mês e ano
	        int dia = calendar.get(Calendar.DAY_OF_MONTH);
	        int mes = calendar.get(Calendar.MONTH) + 1; // Os meses em Calendar começam em 0 (janeiro é 0)
	        int ano = calendar.get(Calendar.YEAR);
	        String dataformatada = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/"+ ano;
	        return dataformatada;
		} else {
			return "Data não especificada";
		}
		
	}
	
	@Override
	public String toString() {
		String dataFormatada = formatDate(datanasc);
		return "Cliente: id = " + id + ", nome = " + nome + ", data de nascimento = " + dataFormatada + ", email = " + email + ";";
	}
	
}
