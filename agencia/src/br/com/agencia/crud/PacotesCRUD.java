package br.com.agencia.crud;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.agencia.dao.HoteisDAO;
import br.com.agencia.dao.PacotesDAO;
import br.com.agencia.dao.VoosDAO;
import br.com.agencia.model.Hoteis;
import br.com.agencia.model.Pacotes;
import br.com.agencia.model.Voos;

public class PacotesCRUD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PacotesDAO pacoteDAO = new PacotesDAO();
		VoosDAO voosDAO = new VoosDAO();
		HoteisDAO hoteisDAO = new HoteisDAO();
		
		int option = 0, id = 0, dia = 0, mes = 0, ano = 0, id_voo_ida =0, id_voo_volta = 0, id_hotel = 0;
		
		String origem_pacote = "", destino_pacote ="";
		
		LocalDate data_ida, data_volta;
		Date data_ida2, data_volta2;
		
		
		do {
			System.out.println("\n==================PACOTES====================\n");
			System.out.println("1- Criar");
			System.out.println("2 - Consultar");
			System.out.println("3 - Atualizar");
			System.out.println("4 - Deletar");
			System.out.println("5 - Consultar por ID");
			System.out.println("0 - Sair");
			option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
			case 1:
				
				System.out.println("Digite o id do voo de ida: ");
				id_voo_ida = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do voo de volta: ");
				id_voo_volta = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do hotel: ");
				id_hotel = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o destino do pacote: ");
				destino_pacote = scanner.nextLine();
				
				System.out.println("Digite a origem do pacote: ");
				origem_pacote = scanner.nextLine();
				
				System.out.println("Digite a data de ida: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_ida = LocalDate.of(ano, mes, dia);
				data_ida2 = Date.valueOf(data_ida);
				
				System.out.println("Digite a data de volta: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_volta = LocalDate.of(ano, mes, dia);
				data_volta2 = Date.valueOf(data_volta);
				
				Voos vooIda = voosDAO.readByID(id_voo_ida);
				Voos vooVolta = voosDAO.readByID(id_voo_volta);
				Hoteis hotel = hoteisDAO.readByID(id_hotel);
				
				Pacotes pacote = new Pacotes(destino_pacote, origem_pacote, data_ida2, data_volta2, vooIda, vooVolta, hotel);
				
				pacoteDAO.create(pacote);
				break;
				
			case 2:
				for (Pacotes p: pacoteDAO.read()) {
					System.out.println("-----------------------------------");
					System.out.println(p.toString());
					System.out.println("-----------------------------------");
				}
				break;
			case 3: 
				
				System.out.println("Digite o id do pacote que deseja atualizar: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do voo de ida: ");
				id_voo_ida = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do voo de volta: ");
				id_voo_volta = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do hotel: ");
				id_hotel = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o destino do pacote: ");
				destino_pacote = scanner.nextLine();
				
				System.out.println("Digite a origem do pacote: ");
				origem_pacote = scanner.nextLine();
				
				System.out.println("Digite a data de ida: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_ida = LocalDate.of(ano, mes, dia);
				data_ida2 = Date.valueOf(data_ida);
				
				System.out.println("Digite a data de volta: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_volta = LocalDate.of(ano, mes, dia);
				data_volta2 = Date.valueOf(data_volta);
				
				Voos vooIdaUp = voosDAO.readByID(id_voo_ida);
				Voos vooVoltaUp = voosDAO.readByID(id_voo_volta);
				Hoteis hotelUp = hoteisDAO.readByID(id_hotel);
			
				
				Pacotes pacote2 = new Pacotes(id, destino_pacote, origem_pacote, data_ida2,
						data_volta2, vooIdaUp, vooVoltaUp, hotelUp);
				
				pacoteDAO.update(pacote2);
				break;
			case 4:
				System.out.println("Digite o id do pacote que deseja excluir: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				pacoteDAO.delete(id);
				
				break;
			case 5:
				System.out.println("Digite o id do pacote que deseja procurar ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				Pacotes pacote3 = pacoteDAO.readByID(id);
				System.out.println(pacote3.toString());
				
				break;
			default:
				if(option != 0) {
					System.out.println("Opção inválida");
				}
				break;
			}
			
		} while(option != 0);
		
		System.out.println("Programa encerrado!");
		scanner.close();
		}
}
