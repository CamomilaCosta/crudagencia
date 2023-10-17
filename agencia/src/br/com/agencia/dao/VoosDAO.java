package br.com.agencia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agencia.connection.ConnectionMySQL;
import br.com.agencia.model.Voos;

public class VoosDAO {
	//create
		public void create(Voos voo) {
			String sql = "INSERT INTO voos (empresa_voo, origem_voo, destino_voo, aeroporto_origem, aeroporto_destino, hora_decolagem, hora_pouso, saida_voo, chegada_voo, preco_voo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, voo.getEmpresa());
				pstm.setString(2, voo.getCidade_origem());
				pstm.setString(3, voo.getCidade_destino());
				pstm.setString(4, voo.getAeroporto_decolagem());
				pstm.setString(5, voo.getAeroporto_pouso());
				pstm.setString(6, voo.getHora_decolagem());
				pstm.setString(7, voo.getHora_pouso());
				pstm.setDate(8, (Date) voo.getDia_decolagem());
				pstm.setDate(9, (Date) voo.getDia_pouso());
				pstm.setFloat(10, voo.getPreco());
				
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
		public List<Voos> read(){
			List<Voos> voosList = new ArrayList<Voos>();
			
			String sql = "SELECT * FROM voos";
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rset = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				rset = pstm.executeQuery();
				
				while(rset.next()) {
					Voos voo = new Voos();
					
					voo.setId(rset.getInt("id_voo"));
					voo.setEmpresa(rset.getString("empresa_voo"));
					voo.setCidade_origem(rset.getString("origem_voo"));
					voo.setCidade_destino(rset.getString("destino_voo"));
					voo.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
					voo.setAeroporto_pouso(rset.getString("aeroporto_destino"));
					voo.setHora_decolagem(rset.getString("hora_decolagem"));
					voo.setHora_pouso(rset.getString("hora_pouso"));
					voo.setDia_decolagem(rset.getDate("saida_voo"));
					voo.setDia_pouso(rset.getDate("chegada_voo"));
					voo.setPreco(rset.getFloat("preco_voo"));
					
					voosList.add(voo);
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
			
			return voosList;
		}
		
		//update 
		
		public void update(Voos voo) {
			String sql = "UPDATE voos SET empresa_voo= ?, origem_voo = ?, destino_voo = ?"
					+ ", aeroporto_origem = ?, aeroporto_destino =? , hora_decolagem = ?,"
					+ "hora_pouso=? , saida_voo=?, chegada_voo=?, preco_voo=? WHERE (id_voo= ?)";
			Connection conn = null;
			PreparedStatement pstm = null;
			
			try {
				conn = ConnectionMySQL.creatConnectionToMySQL();
				pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, voo.getEmpresa());
				pstm.setString(2, voo.getCidade_origem());
				pstm.setString(3, voo.getCidade_destino());
				pstm.setString(4, voo.getAeroporto_decolagem());
				pstm.setString(5, voo.getAeroporto_pouso());
				pstm.setString(6, voo.getHora_decolagem());
				pstm.setString(7, voo.getHora_pouso());
				pstm.setDate(8, (Date) voo.getDia_decolagem());
				pstm.setDate(9, (Date) voo.getDia_pouso());
				pstm.setFloat(10, voo.getPreco());
				
				pstm.setInt(11, voo.getId());
				
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
			String sql = "DELETE FROM voos WHERE id_voo = ?";
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
		public Voos readByID(int id) {
			String sql = "SELECT * FROM voos WHERE id_voo = ?";
			Voos voo = new Voos();
			
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
				
				voo.setId(rset.getInt("id_voo"));
				voo.setEmpresa(rset.getString("empresa_voo"));
				voo.setCidade_origem(rset.getString("origem_voo"));
				voo.setCidade_destino(rset.getString("destino_voo"));
				voo.setAeroporto_decolagem(rset.getString("aeroporto_origem"));
				voo.setAeroporto_pouso(rset.getString("aeroporto_destino"));
				voo.setHora_decolagem(rset.getString("hora_decolagem"));
				voo.setHora_pouso(rset.getString("hora_pouso"));
				voo.setDia_decolagem(rset.getDate("saida_voo"));
				voo.setDia_pouso(rset.getDate("chegada_voo"));
				voo.setPreco(rset.getFloat("preco_voo"));	
						
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
			
			return voo;
		}
}
