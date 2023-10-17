package br.com.agencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia.connection.ConnectionMySQL;
import br.com.agencia.model.Hoteis;

public class HoteisDAO {
	//create
	public void create(Hoteis hotel) {
		String sql = "INSERT INTO hoteis (nome, preco_diaria, cidade_hotel, bairro_hotel, rua_hotel) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, hotel.getNome());
			pstm.setFloat(2, hotel.getPreco_diaria());;
			pstm.setString(3, hotel.getCidade());
			pstm.setString(4, hotel.getBairro());
			pstm.setString(5, hotel.getRua());
			
			pstm.execute();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				if(conn != null) {
					conn.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
			} catch(Exception e) {
				
				e.printStackTrace();
				
			}
		}
	}
	
	//read
		public List<Hoteis> read(){
			List<Hoteis> hoteisList = new ArrayList<Hoteis>();
			
			String sql = "SELECT * FROM hoteis";
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				rset = pstm.executeQuery();
				
				while(rset.next()) {
					Hoteis hotel = new Hoteis();
					
					hotel.setId(rset.getInt("id_hotel"));
					hotel.setNome(rset.getString("nome"));
					hotel.setPreco_diaria(rset.getFloat("preco_diaria"));
					hotel.setCidade(rset.getString("cidade_hotel"));
					hotel.setBairro(rset.getString("bairro_hotel"));
					hotel.setRua(rset.getString("rua_hotel"));
					
					hoteisList.add(hotel);
				}
						
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				try {
					if(conn != null) {
						conn.close();
					}
					
					if(pstm != null) {
						pstm.close();
					}
					
				} catch(Exception e) {
					
					e.printStackTrace();
					
				}
			}
			
			return hoteisList;
		}

		//update 
		
		public void update(Hoteis hotel) {
			String sql = "UPDATE hoteis SET nome= ?, preco_diaria = ?, cidade_hotel= ?, bairro_hotel= ?, rua_hotel= ? WHERE (id_hotel= ?)";
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, hotel.getNome());
				pstm.setFloat(2, hotel.getPreco_diaria());
				pstm.setString(3, hotel.getCidade());
				pstm.setString(4, hotel.getBairro());
				pstm.setString(5, hotel.getRua());
				
				pstm.setInt(6, hotel.getId());
				
				pstm.execute();
				
						
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				try {
					if(conn != null) {
						conn.close();
					}
					
					if(pstm != null) {
						pstm.close();
					}
					
				} catch(Exception e) {
					
					e.printStackTrace();
					
				}
			}
		}
		//delete
		public void delete(int id) {
			String sql = "DELETE FROM hoteis WHERE id_hotel = ?";
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				
				pstm.setInt(1, id);
				
				pstm.execute();
				
						
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				try {
					if(conn != null) {
						conn.close();
					}
					
					if(pstm != null) {
						pstm.close();
					}
					
				} catch(Exception e) {
					
					e.printStackTrace();
					
				}
			}
		}

		//read by id
		public Hoteis readByID(int id) {
			String sql = "SELECT * FROM hoteis WHERE id_hotel = ?";
			Hoteis hotel = new Hoteis();
			
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
				
				hotel.setId(rset.getInt("id_hotel"));
				hotel.setNome(rset.getString("nome"));
				hotel.setPreco_diaria(rset.getFloat("preco_diaria"));
				hotel.setCidade(rset.getString("cidade_hotel"));
				hotel.setBairro(rset.getString("bairro_hotel"));
				hotel.setRua(rset.getString("rua_hotel"));
						
			} catch(Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				try {
					if(conn != null) {
						conn.close();
					}
					
					if(pstm != null) {
						pstm.close();
					}
					
				} catch(Exception e) {
					
					e.printStackTrace();
					
				}
			}
			
			return hotel;
		}
}
