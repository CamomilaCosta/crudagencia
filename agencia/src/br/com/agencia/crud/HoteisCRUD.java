package br.com.agencia.crud;

import java.util.Scanner;

import br.com.agencia.dao.HoteisDAO;
import br.com.agencia.model.Hoteis;

public class HoteisCRUD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HoteisDAO hotelDAO = new HoteisDAO();
		
		int option = 0; int id = 0;
		String nome = "", bairro = "", rua = "", cidade = "";
		Float preco_diaria;
		
		do {
			System.out.println("\n==================HOTEIS====================\n");
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
					System.out.println("Digite o nome do hotel: ");
					nome = scanner.nextLine();
					
					System.out.println("Digite o preço da diária: ");
					preco_diaria = scanner.nextFloat();
					scanner.nextLine();
					
					System.out.println("Digite o endereço: ");
					System.out.println("Cidade: ");
					cidade = scanner.nextLine();
					
					System.out.println("Bairro: ");
					bairro = scanner.nextLine();
					
					System.out.println("Rua: ");
					rua = scanner.nextLine();
					
					
					Hoteis hotel = new Hoteis(nome, preco_diaria, cidade, bairro, rua);
					
					hotelDAO.create(hotel);
				break;
				case 2:
					for (Hoteis h: hotelDAO.read()) {
						System.out.println("-----------------------------------");
						System.out.println(h.toString());
						System.out.println("-----------------------------------");
					}
				break;
				case 3:
					System.out.println("Digite o id do hotel que deseja atualizar: ");
					id = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Digite o nome do hotel atualizado: ");
					nome = scanner.nextLine();
					
					System.out.println("Digite o preço da diária atualizado: ");
					preco_diaria = scanner.nextFloat();
					scanner.nextLine();
					
					System.out.println("Digite o endereço atualizada: ");
					System.out.println("Cidade: ");
					cidade = scanner.nextLine();
					
					System.out.println("Bairro: ");
					bairro = scanner.nextLine();
					
					System.out.println("Rua: ");
					rua = scanner.nextLine();
					
					Hoteis hotel2 = new Hoteis(id, nome, preco_diaria, cidade, bairro, rua);
					hotelDAO.update(hotel2);
				break;
				case 4:
					System.out.println("Digite o id do hotel que deseja excluir: ");
					id = scanner.nextInt();
					scanner.nextLine();
					
					hotelDAO.delete(id);
				break;
				case 5:
					System.out.println("Digite o id do hotel que deseja procurar ");
					id = scanner.nextInt();
					scanner.nextLine();
					
					Hoteis hotel3 = hotelDAO.readByID(id);
					System.out.println(hotel3.toString());
				break;
				default:
				
				break;
			}
		} while(option!=0);
		
		System.out.println("Programa encerrado!");
		scanner.close();
	}
}
