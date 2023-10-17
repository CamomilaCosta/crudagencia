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
		
		Float preco = 0f;
		
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
				
				Pacotes pacote = new Pacotes( destino_pacote, origem_pacote, data_ida2, data_volta2, vooIda, vooVolta, hotel);
				
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
				
				/*System.out.println("Digite o id do pacote que deseja atualizar: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				do {
					System.out.println("Escolha a empresa atualizada: ");
					System.out.println("1 - LATAM");
					System.out.println("2 - Gol");
					System.out.println("3 - Azul");
					opt_empresa = scanner.nextInt();
					scanner.nextLine();
					
					switch(opt_empresa) {
					case 1:
					empresa = "LATAM";
						break;
					case 2:
						empresa = "Gol";
						break;
					case 3: 
						empresa = "Azul";
						break;
					default:
						System.out.println("Opção inválida");
					break;
					}
				} while(opt_empresa != 1 && opt_empresa != 2 && opt_empresa != 3);
				
				
				
				System.out.println("Digite a cidade de origem do pacote: ");
				cidade_origem = scanner.nextLine();
				
				System.out.println("Digite a cidade de destino do pacote: ");
				cidade_destino = scanner.nextLine();
				
				System.out.println("Digite o aeroporto de origem: ");
				aeroporto_decolagem = scanner.nextLine();
				
				System.out.println("Digite o aeroporto de destino: ");
				aeroporto_pouso = scanner.nextLine();
				
				System.out.println("Digite a hora de decolagem: ");
				hora_decolagem = scanner.nextLine();
				
				System.out.println("Digite a hora de pouso: ");
				hora_pouso = scanner.nextLine();
				
				System.out.println("Digite a data de saída: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				dia_decolagem = LocalDate.of(ano, mes, dia);
				dia_decolagem2 = Date.valueOf(dia_decolagem);
				
				System.out.println("Digite a data de chegada: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				dia_pouso = LocalDate.of(ano, mes, dia);
				dia_pouso2 = Date.valueOf(dia_pouso);
				
				System.out.println("Digite o preço da passagem: ");
				preco = scanner.nextFloat();
				scanner.nextLine();
				
				Pacotes pacote2 = new Pacotes(id, empresa, cidade_origem, cidade_destino, aeroporto_decolagem,
						aeroporto_pouso, hora_decolagem, hora_pouso, dia_decolagem2, dia_pouso2, preco);
				
				pacoteDAO.update(pacote2);*/
				break;
			case 4:
				/*System.out.println("Digite o id do pacote que deseja excluir: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				pacoteDAO.delete(id);*/
				
				break;
			case 5:
				/*System.out.println("Digite o id do pacote que deseja procurar ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				Pacotes pacote3 = pacoteDAO.readByID(id);
				System.out.println(pacote3.toString());*/
				
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
