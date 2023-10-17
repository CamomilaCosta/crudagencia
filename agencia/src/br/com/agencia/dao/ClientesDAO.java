package br.com.agencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import br.com.agencia.model.Clientes;
import br.com.agencia.connection.ConnectionMySQL;

public class ClientesDAO {
	//create
	public void create(Clientes cliente) {
		String sql = "INSERT INTO clientes (nome_cliente, data_nasc_cliente, email_cliente) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNome());
			pstm.setDate(2, (Date) cliente.getDatanasc());;
			pstm.setString(3, cliente.getEmail());
			
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
	public List<Clientes> read(){
		List<Clientes> clientesList = new ArrayList<Clientes>();
		
		String sql = "SELECT * FROM clientes";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Clientes cliente = new Clientes();
				
				cliente.setId(rset.getInt("id_cliente"));
				cliente.setNome(rset.getString("nome_cliente"));
				cliente.setEmail(rset.getString("email_cliente"));
				cliente.setDatanasc(rset.getDate("data_nasc_cliente"));
				
				clientesList.add(cliente);
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
		
		return clientesList;
	}
	
	//update 
	
	public void update(Clientes cliente) {
		String sql = "UPDATE clientes SET nome_cliente= ?, data_nasc_cliente = ?, email_cliente= ? WHERE (id_cliente= ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionMySQL.creatConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNome());
			pstm.setDate(2, (Date) cliente.getDatanasc());
			pstm.setString(3, cliente.getEmail());
			
			pstm.setInt(4, cliente.getId());
			
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
		String sql = "DELETE FROM clientes WHERE id_cliente = ?";
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
	public Clientes readByID(int id) {
		String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
		Clientes cliente = new Clientes();
		
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
			
			cliente.setId(rset.getInt("id_cliente"));
			cliente.setNome(rset.getString("nome_cliente"));
			cliente.setDatanasc(rset.getDate("data_nasc_cliente"));
			cliente.setEmail(rset.getString("email_cliente"));					
					
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
		
		return cliente;
	}
}
