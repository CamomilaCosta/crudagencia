package br.com.agencia.crud;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.agencia.dao.PacotesDAO;
import br.com.agencia.dao.ClientesDAO;
import br.com.agencia.dao.ComprasDAO;
import br.com.agencia.model.Pacotes;
import br.com.agencia.model.Clientes;
import br.com.agencia.model.Compras;

public class ComprasCRUD {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ComprasDAO compraDAO = new ComprasDAO();
		ClientesDAO clientesDAO = new ClientesDAO();
		PacotesDAO pacotesDAO = new PacotesDAO();
		
		int option = 0, opt_pag = 0, id = 0, dia = 0, mes = 0, ano = 0, id_cliente =0, id_pacote = 0, parcelas;
		
		String tipo_pagamento = "";
		
		LocalDate data_compra;
		Date data_compra2;
		
		
		do {
			System.out.println("\n==================COMPRAS====================\n");
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
				
				System.out.println("Digite o id do cliente: ");
				id_cliente = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do pacote: ");
				id_pacote = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite a data de compra: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_compra = LocalDate.of(ano, mes, dia);
				data_compra2 = Date.valueOf(data_compra);
				
				do {
					System.out.println("Escolha o método de pagamento: ");
					System.out.println("1 - Crédito");
					System.out.println("2 - Débito");
					System.out.println("3 - PIX");
					System.out.println("3 - Boleto");
					opt_pag = scanner.nextInt();
					scanner.nextLine();
					
					switch(opt_pag) {
					case 1:
					tipo_pagamento = "Crédito";
						break;
					case 2:
						tipo_pagamento = "Débito";
						break;
					case 3: 
						tipo_pagamento = "PIX";
						break;
					case 4: 
						tipo_pagamento = "Boleto";
						break;
					default:
						System.out.println("Opção inválida");
					break;
					}
				} while(opt_pag < 1 && opt_pag > 4);
				
				System.out.println("Digite a quantidade de parcelas: ");
				parcelas = scanner.nextInt();
				scanner.nextLine();
				
				Pacotes pacote = pacotesDAO.readByID(id_pacote);
				Clientes cliente = clientesDAO.readByID(id_cliente);
				
				
				Compras compra = new Compras(data_compra2, tipo_pagamento, parcelas, cliente, pacote );
				compraDAO.create(compra);
				break;
				
			case 2:
				for (Compras c: compraDAO.read()) {
					System.out.println("-----------------------------------");
					System.out.println(c.toString());
					System.out.println("-----------------------------------");
				}
				break;
			case 3: 
				
				System.out.println("Digite o id da compra que deseja atualizar: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do cliente: ");
				id_cliente = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite o id do pacote: ");
				id_pacote = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Digite a data de compra: ");
				System.out.println("Dia: ");
				dia = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Mês: ");
				mes = scanner.nextInt();
				scanner.nextLine();
				
				System.out.println("Ano: ");
				ano = scanner.nextInt();
				scanner.nextLine();
				
				data_compra = LocalDate.of(ano, mes, dia);
				data_compra2 = Date.valueOf(data_compra);
				
				do {
					System.out.println("Escolha o método de pagamento: ");
					System.out.println("1 - Crédito");
					System.out.println("2 - Débito");
					System.out.println("3 - PIX");
					System.out.println("3 - Boleto");
					opt_pag = scanner.nextInt();
					scanner.nextLine();
					
					switch(opt_pag) {
					case 1:
					tipo_pagamento = "Crédito";
						break;
					case 2:
						tipo_pagamento = "Débito";
						break;
					case 3: 
						tipo_pagamento = "PIX";
						break;
					case 4: 
						tipo_pagamento = "Boleto";
						break;
					default:
						System.out.println("Opção inválida");
					break;
					}
				} while(opt_pag < 1 && opt_pag > 4);
				
				System.out.println("Digite a quantidade de parcelas: ");
				parcelas = scanner.nextInt();
				scanner.nextLine();
				
				
				Pacotes pacoteUP = pacotesDAO.readByID(id_pacote);
				Clientes clienteUP = clientesDAO.readByID(id_cliente);
				
				Compras compra2 = new Compras(id, data_compra2, tipo_pagamento, parcelas, clienteUP, pacoteUP );
				
				compraDAO.update(compra2);
				break;
			case 4:
				System.out.println("Digite o id da compra que deseja excluir: ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				compraDAO.delete(id);
				
				break;
			case 5:
				System.out.println("Digite o id da compra que deseja procurar ");
				id = scanner.nextInt();
				scanner.nextLine();
				
				Compras compra3 = compraDAO.readByID(id);
				System.out.println(compra3.toString());
				
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
