package br.com.agencia.crud;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;

import br.com.agencia.dao.ClientesDAO;
import br.com.agencia.model.Clientes;

public class ClientesCRUD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ClientesDAO clienteDAO = new ClientesDAO();
		
		int option = 0; int id = 0; int dia = 0; int mes = 0; int ano = 0;
		String nome = "", email = "";
		LocalDate data_nasc;
		Date data_nascimento;
		
		
		
		do {
			System.out.println("\n==================CLIENTES====================\n");
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
				System.out.println("Digite o nome do cliente: ");
				nome = scanner.nextLine();
				
				System.out.println("Digite o email: ");
				email = scanner.nextLine();
				
				System.out.println("Digite a data de nascimento: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_nasc = LocalDate.of(ano, mes, dia);
				data_nascimento = Date.valueOf(data_nasc);
				
				Clientes cliente = new Clientes(nome, data_nascimento, email);
				
				clienteDAO.create(cliente);
				break;
			case 2:
				for (Clientes c: clienteDAO.read()) {
					System.out.println("-----------------------------------");
					System.out.println(c.toString());
					System.out.println("-----------------------------------");
				}
				break;
			case 3: 
				
				System.out.println("Digite o id do cleinte que deseja atualizar: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o nome do cliente atualizado: ");
				nome = scanner.nextLine();
				
				System.out.println("Digite a data de nascimento atualizada: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o email atualizado: ");
				email = scanner.nextLine();
				
				data_nasc = LocalDate.of(ano, mes, dia);
				data_nascimento = Date.valueOf(data_nasc);
				
				Clientes cliente2 = new Clientes(id, nome, data_nascimento, email);
				clienteDAO.update(cliente2);
				break;
			case 4:
				System.out.println("Digite o id do cliente que deseja excluir: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				clienteDAO.delete(id);
				
				break;
			case 5:
				System.out.println("Digite o id do usuário que deseja procurar ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				Clientes user3 = clienteDAO.readByID(id);
				System.out.println(user3.toString());
				
				break;
			default:
				if(option != 0) {
					System.out.println("Opção inválida");
				}
				break;
			}
			
		} while(option != 0);
		
		System.out.println("Programa encerrado");
		scanner.close();
	}
}
