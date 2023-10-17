package br.com.agencia.crud;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.agencia.dao.VoosDAO;
import br.com.agencia.model.Voos;

public class VoosCRUD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		VoosDAO vooDAO = new VoosDAO();
		
		int option = 0, opt_empresa=0, id = 0, dia = 0, mes = 0, ano = 0;
		
		String empresa = "", cidade_origem = "", cidade_destino ="",
		aeroporto_decolagem = "", aeroporto_pouso = "", hora_decolagem="", hora_pouso="";
		
		LocalDate dia_decolagem, dia_pouso;
		Date dia_decolagem2, dia_pouso2;
		
		Float preco = 0f;
		
		do {
			System.out.println("\n==================VOOS====================\n");
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
				
				do {
					System.out.println("Escolha a empresa: ");
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
					default:
						System.out.println("Opção inválida");
					break;
					}
				} while(opt_empresa != 1 && opt_empresa != 2 && opt_empresa != 3);
					
				
				
				System.out.println("Digite a cidade de origem do voo: ");
				cidade_origem = scanner.nextLine();
				
				System.out.println("Digite a cidade de destino do voo: ");
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
				
				Voos voo = new Voos(empresa, cidade_origem, cidade_destino, aeroporto_decolagem,
						aeroporto_pouso, hora_decolagem, hora_pouso, dia_decolagem2, dia_pouso2, preco);
				
				vooDAO.create(voo);
				break;
			case 2:
				for (Voos v: vooDAO.read()) {
					System.out.println("-----------------------------------");
					System.out.println(v.toString());
					System.out.println("-----------------------------------");
				}
				break;
			case 3: 
				
				System.out.println("Digite o id do voo que deseja atualizar: ");
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
				
				
				
				System.out.println("Digite a cidade de origem do voo: ");
				cidade_origem = scanner.nextLine();
				
				System.out.println("Digite a cidade de destino do voo: ");
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
				
				Voos voo2 = new Voos(id, empresa, cidade_origem, cidade_destino, aeroporto_decolagem,
						aeroporto_pouso, hora_decolagem, hora_pouso, dia_decolagem2, dia_pouso2, preco);
				
				vooDAO.update(voo2);
				break;
			case 4:
				System.out.println("Digite o id do voo que deseja excluir: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				vooDAO.delete(id);
				
				break;
			case 5:
				System.out.println("Digite o id do voo que deseja procurar ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				Voos voo3 = vooDAO.readByID(id);
				System.out.println(voo3.toString());
				
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
