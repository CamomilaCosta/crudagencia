package br.com.agencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia.connection.ConnectionMySQL;
import br.com.agencia.model.Hoteis;
import br.com.agencia.model.Pacotes;
import br.com.agencia.model.Voos;

public class PacotesDAO {
	// create
	public void create(Pacotes pacote) {
		String sql = "INSERT INTO pacotes (id_voo_ida, id_voo_volta, id_hotel, destino_pacote, origem_pacote, data_ida_pacote, data_volta_pacote) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, pacote.getVoo_ida().getId());
			pstm.setInt(2, pacote.getVoo_volta().getId());
			pstm.setInt(3, pacote.getHotel().getId());
			pstm.setString(4, pacote.getDestino());
			pstm.setString(5, pacote.getOrigem());
			pstm.setDate(6, (Date) pacote.getData_ida());
			pstm.setDate(7, (Date) pacote.getData_volta());

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
	public List<Pacotes> read() {
		List<Pacotes> pacotesList = new ArrayList<Pacotes>();

		String sql = "SELECT \r\n"
				+ "	p.*,\r\n"
				+ "	h.*,\r\n"
				+ "	vi.*,\r\n"
				+ "	vv.*,\r\n"
				+ "    p.id_pacote AS pacote_id, \r\n"
				+ "    h.id AS hotel_id, \r\n"
				+ "    vi.id_voo AS voo_ida_id, \r\n"
				+ "    vv.id_voo AS voo_volta_id\r\n"
				+ "FROM \r\n"
				+ "    pacotes AS p\r\n"
				+ "    JOIN hoteis AS h ON p.id_hotel = h.id\r\n"
				+ "    JOIN voos AS vi ON p.id_voo_ida = vi.id_voo\r\n"
				+ "    JOIN voos AS vv ON p.id_voo_volta = vv.id_voo";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();

			while (rset.next()) {
				Pacotes pacote = new Pacotes();
				Hoteis hotel = new Hoteis();
				Voos voo_ida = new Voos();
				Voos voo_volta = new Voos();

				pacote.setId(rset.getInt("id_pacote"));
				pacote.setDestino(rset.getString("destino_pacote"));
				pacote.setOrigem(rset.getString("origem_pacote"));
				pacote.setData_ida(rset.getDate("data_ida_pacote"));
				pacote.setData_volta(rset.getDate("data_volta_pacote"));
				pacote.setPreco(rset.getFloat("preco_pacote"));
				pacote.setDias_pacote(rset.getInt("dias_pacote"));
				
				voo_ida.setId(rset.getInt("id_voo_ida"));
				voo_ida.setEmpresa(rset.getString("empresa_voo"));
				voo_ida.setCidade_origem(rset.getString("origem_voo"));
				voo_ida.setCidade_destino(rset.getString("destino_voo"));
				voo_ida.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
				voo_ida.setAeroporto_pouso(rset.getString("aeroporto_destino"));
				voo_ida.setHora_decolagem(rset.getString("hora_decolagem"));
				voo_ida.setHora_pouso(rset.getString("hora_pouso"));
				voo_ida.setDia_decolagem(rset.getDate("saida_voo"));
				voo_ida.setDia_pouso(rset.getDate("chegada_voo"));
				voo_ida.setPreco(rset.getFloat("preco_voo"));
				
				voo_volta.setId(rset.getInt("id_voo_volta"));
				voo_volta.setEmpresa(rset.getString("empresa_voo"));
				voo_volta.setCidade_origem(rset.getString("origem_voo"));
				voo_volta.setCidade_destino(rset.getString("destino_voo"));
				voo_volta.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
				voo_volta.setAeroporto_pouso(rset.getString("aeroporto_destino"));
				voo_volta.setHora_decolagem(rset.getString("hora_decolagem"));
				voo_volta.setHora_pouso(rset.getString("hora_pouso"));
				voo_volta.setDia_decolagem(rset.getDate("saida_voo"));
				voo_volta.setDia_pouso(rset.getDate("chegada_voo"));
				voo_volta.setPreco(rset.getFloat("preco_voo"));
				
				hotel.setId(rset.getInt("id_hotel"));
				hotel.setNome(rset.getString("nome"));
				hotel.setPreco_diaria(rset.getFloat("preco_diaria"));
				hotel.setCidade(rset.getString("cidade_hotel"));
				hotel.setBairro(rset.getString("bairro_hotel"));
				hotel.setRua(rset.getString("rua_hotel"));
				
				pacote.setHotel(hotel);
				pacote.setVoo_ida(voo_ida);
				pacote.setVoo_volta(voo_volta);

				pacotesList.add(pacote);
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

		return pacotesList;
	}

	// update

	public void update(Pacotes pacote) {
		String sql = "UPDATE pacotes SET id_voo_ida = ?, id_voo_volta = ?, id_hotel = ?"
				+ ", destino_pacote = ?, origem_pacote = ? , data_ida_pacote = ?,"
				+ " data_volta_pacote = ? WHERE (id_pacote = ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, pacote.getVoo_ida().getId());
			pstm.setInt(2, pacote.getVoo_volta().getId());
			pstm.setInt(3, pacote.getHotel().getId());
			pstm.setString(4, pacote.getDestino());
			pstm.setString(5, pacote.getOrigem());
			pstm.setDate(6, (Date) pacote.getData_ida());
			pstm.setDate(7, (Date) pacote.getData_volta());

			pstm.setInt(8, pacote.getId());

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
		String sql = "DELETE FROM pacotes WHERE id_pacote = ?";
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
	public Pacotes readByID(int id) {
		String sql = "SELECT * FROM pacote_info WHERE id_pacote = ?";
		Pacotes pacote = new Pacotes();

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
			
			Hoteis hotel = new Hoteis();
			Voos voo_ida = new Voos();
			Voos voo_volta = new Voos();

			pacote.setId(rset.getInt("id_pacote"));
			pacote.setDestino(rset.getString("destino_pacote"));
			pacote.setOrigem(rset.getString("origem_pacote"));
			pacote.setData_ida(rset.getDate("data_ida_pacote"));
			pacote.setData_volta(rset.getDate("data_volta_pacote"));
			pacote.setPreco(rset.getFloat("preco_pacote"));
			pacote.setDias_pacote(rset.getInt("dias_pacote"));
			
			voo_ida.setId(rset.getInt("id_voo_ida"));
			voo_ida.setEmpresa(rset.getString("empresa_voo"));
			voo_ida.setCidade_origem(rset.getString("origem_voo"));
			voo_ida.setCidade_destino(rset.getString("destino_voo"));
			voo_ida.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
			voo_ida.setAeroporto_pouso(rset.getString("aeroporto_destino"));
			voo_ida.setHora_decolagem(rset.getString("hora_decolagem"));
			voo_ida.setHora_pouso(rset.getString("hora_pouso"));
			voo_ida.setDia_decolagem(rset.getDate("saida_voo"));
			voo_ida.setDia_pouso(rset.getDate("chegada_voo"));
			voo_ida.setPreco(rset.getFloat("preco_voo"));
			
			voo_volta.setId(rset.getInt("id_voo_volta"));
			voo_volta.setEmpresa(rset.getString("empresa_voo"));
			voo_volta.setCidade_origem(rset.getString("origem_voo"));
			voo_volta.setCidade_destino(rset.getString("destino_voo"));
			voo_volta.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
			voo_volta.setAeroporto_pouso(rset.getString("aeroporto_destino"));
			voo_volta.setHora_decolagem(rset.getString("hora_decolagem"));
			voo_volta.setHora_pouso(rset.getString("hora_pouso"));
			voo_volta.setDia_decolagem(rset.getDate("saida_voo"));
			voo_volta.setDia_pouso(rset.getDate("chegada_voo"));
			voo_volta.setPreco(rset.getFloat("preco_voo"));
			
			hotel.setId(rset.getInt("id_hotel"));
			hotel.setNome(rset.getString("nome"));
			hotel.setPreco_diaria(rset.getFloat("preco_diaria"));
			hotel.setCidade(rset.getString("cidade_hotel"));
			hotel.setBairro(rset.getString("bairro_hotel"));
			hotel.setRua(rset.getString("rua_hotel"));
			
			pacote.setHotel(hotel);
			pacote.setVoo_ida(voo_ida);
			pacote.setVoo_volta(voo_volta);

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

		return pacote;
	}
}
