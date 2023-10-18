package br.com.agencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia.connection.ConnectionMySQL;
import br.com.agencia.model.Clientes;
import br.com.agencia.model.Compras;
import br.com.agencia.model.Hoteis;
import br.com.agencia.model.Pacotes;
import br.com.agencia.model.Voos;

public class ComprasDAO {
	// create
		public void create(Compras compra) {
			String sql = "INSERT INTO compras (id_pacote, id_cliente, data_compra, tipo_pagamento, parcelas) VALUES (?, ?, ?, ?, ?)";
			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);

				pstm.setInt(1, compra.getPacote().getId());
				pstm.setInt(2, compra.getCliente().getId());
				pstm.setDate(3, (Date) compra.getData_compra());
				pstm.setString(4, compra.getTipo_pagamento());
				pstm.setInt(5, compra.getParcelas());

				pstm.execute();

			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					if (conn != null) {
						conn.close();
					}

					if (pstm != null) {
						pstm.close();
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}

		// read
		public List<Compras> read() {
			List<Compras> comprasList = new ArrayList<Compras>();

			String sql = "SELECT \r\n"
					+ "    c.*,\r\n"
					+ "    co.*,\r\n"
					+ "	p.*,\r\n"
					+ "	h.*,\r\n"
					+ "	vi.*,\r\n"
					+ "	vv.*\r\n"
					+ "FROM \r\n"
					+ "    compras AS co\r\n"
					+ "    JOIN clientes AS c ON co.id_cliente = c.id_cliente\r\n"
					+ "    JOIN pacotes AS p ON co.id_pacote = p.id_pacote\r\n"
					+ "    JOIN hoteis AS h ON p.id_hotel = h.id_hotel\r\n"
					+ "    JOIN voos AS vi ON p.id_voo_ida = vi.id_voo\r\n"
					+ "    JOIN voos AS vv ON p.id_voo_volta = vv.id_voo;";
			
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;

			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				rset = pstm.executeQuery();

				while (rset.next()) {
					Compras compra = new Compras();
					Clientes cliente = new Clientes();
					Pacotes pacote = new Pacotes();
					Hoteis hotel = new Hoteis();
					Voos voo_ida = new Voos();
					Voos voo_volta = new Voos();
					
					VoosDAO voosDAO = new VoosDAO();
					ClientesDAO clientesDAO = new ClientesDAO();
					PacotesDAO pacotesDAO = new PacotesDAO();
					HoteisDAO hoteisDAO = new HoteisDAO();
					
					compra.setId(rset.getInt("id_compra"));
					compra.setData_compra( (Date) rset.getDate("data_compra"));
					compra.setTipo_pagamento(rset.getString("tipo_pagamento"));
					compra.setParcelas(rset.getInt("parcelas"));
					compra.setValor_total(rset.getFloat("valor_compra"));
					
					int idCliente = rset.getInt("id_cliente");
					int idPacote = rset.getInt("id_pacote");
					
					cliente = clientesDAO.readByID(idCliente);
				    pacote = pacotesDAO.readByID(idPacote);
 
					int idHotel = pacote.getHotel().getId();
				    int idVooIda = pacote.getVoo_ida().getId();
				    int idVooVolta = pacote.getVoo_volta().getId();
					
				    voo_ida = voosDAO.readByID(idVooIda);
				    voo_volta = voosDAO.readByID(idVooVolta);
				    hotel = hoteisDAO.readByID(idHotel);

				    pacote.setVoo_ida(voo_ida);
				    pacote.setVoo_volta(voo_volta);
					pacote.setHotel(hotel);
					
					compra.setCliente(cliente);
				    compra.setPacote(pacote);
					
					comprasList.add(compra);
				}

			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					if (conn != null) {
						conn.close();
					}

					if (pstm != null) {
						pstm.close();
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}

			return comprasList;
		}

		// update

		public void update(Compras compra) {
			String sql = "UPDATE compras SET id_pacote = ?, id_cliente = ?, data_compra = ?"
					+ ", tipo_pagamento = ?, parcelas = ? WHERE (id_compra = ?)";
			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				
				pstm.setInt(1, compra.getPacote().getId());
				pstm.setInt(2, compra.getCliente().getId());
				pstm.setDate(3, (Date) compra.getData_compra());
				pstm.setString(4, compra.getTipo_pagamento());
				pstm.setInt(5, compra.getParcelas());

				pstm.setInt(6, compra.getId());

				pstm.execute();

			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					if (conn != null) {
						conn.close();
					}

					if (pstm != null) {
						pstm.close();
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}

		// delete
		public void delete(int id) {
			String sql = "DELETE FROM compras WHERE id_compra = ?";
			Connection conn = null;
			PreparedStatement pstm = null;

			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);

				pstm.setInt(1, id);

				pstm.execute();

			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					if (conn != null) {
						conn.close();
					}

					if (pstm != null) {
						pstm.close();
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}

		// read by id
		public Compras readByID(int id) {
			String sql = "SELECT * FROM compras WHERE id_compra = ?";
			Compras compra = new Compras();

			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;

			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);

				pstm.setInt(1, id);

				pstm.execute();
				rset = pstm.executeQuery();
				rset.next();
				
				Clientes cliente = new Clientes();
				Pacotes pacote = new Pacotes();
				Hoteis hotel = new Hoteis();
				Voos voo_ida = new Voos();
				Voos voo_volta = new Voos();
				
				VoosDAO voosDAO = new VoosDAO();
				ClientesDAO clientesDAO = new ClientesDAO();
				PacotesDAO pacotesDAO = new PacotesDAO();
				HoteisDAO hoteisDAO = new HoteisDAO();

				compra.setId(rset.getInt("id_compra"));
				compra.setId(rset.getInt("id_pacote"));
				compra.setId(rset.getInt("id_cliente"));
				compra.setData_compra( (Date) rset.getDate("data_compra"));
				compra.setTipo_pagamento(rset.getString("tipo_pagamento"));
				compra.setParcelas(rset.getInt("parcelas"));
				compra.setValor_total(rset.getFloat("valor_compra"));
				
				int idCliente = rset.getInt("id_cliente");
				int idPacote = rset.getInt("id_pacote");
				
				cliente = clientesDAO.readByID(idCliente);
			    pacote = pacotesDAO.readByID(idPacote);

				int idHotel = pacote.getHotel().getId();
			    int idVooIda = pacote.getVoo_ida().getId();
			    int idVooVolta = pacote.getVoo_volta().getId();
				
			    voo_ida = voosDAO.readByID(idVooIda);
			    voo_volta = voosDAO.readByID(idVooVolta);
			    hotel = hoteisDAO.readByID(idHotel);

			    pacote.setVoo_ida(voo_ida);
			    pacote.setVoo_volta(voo_volta);
				pacote.setHotel(hotel);
				
				compra.setCliente(cliente);
			    compra.setPacote(pacote);				
			    
			} catch (Exception e) {

				e.printStackTrace();

			} finally {

				try {
					if (conn != null) {
						conn.close();
					}

					if (pstm != null) {
						pstm.close();
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}

			return compra;
		}
}
