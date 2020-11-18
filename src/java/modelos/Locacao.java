/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;
import utils.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author entra21
 */
public class Locacao {
  private int id;
  private Date datadevolucao;
  private int idcarro;
  private String cfpcliente;
  private Date dataretirada;
  private Date data;  
  
  public ResultSet consultarInner(){
      Connection con = Conexao.conectar();
      String sql = "select l.id, l.idcarro,c.placa, c.modelo, \n" +
                   "       l.cpfcliente,cli.nome, l.data, \n" +
                   "       l.dataretirada, l.datadevolucao       \n" +
                   "from locacao l , carro c, cliente cli  \n" +
                   "where l.idcarro = c.id\n" +
                   "and l.cpfcliente = cli.cpf";
      ResultSet rs = null;
      try {
          PreparedStatement stm = con.prepareStatement(sql);
          rs = stm.executeQuery();
      } catch (SQLException ex) {
          Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
      }
      return rs;
  }

  public List<Locacao> consultar(String cliente){
     List<Locacao> lista = new ArrayList<>();
     Connection con = Conexao.conectar();     
     String sql = "select id, idcarro, cpfcliente, data, dataretirada";
            sql += " from locacao where cpfcliente = ?";
      try {
          PreparedStatement stm = con.prepareStatement(sql);
          stm.setString(1, cliente);
          ResultSet rs = stm.executeQuery();
         
          while(rs.next()){
             Locacao locacao = new Locacao();
             locacao.setId(rs.getInt("id"));
             locacao.setCfpcliente(rs.getString("cpfcliente"));
             locacao.setIdcarro(rs.getInt("idcarro"));
             locacao.setData(rs.getDate("data"));
             locacao.setDataretirada(rs.getDate("dataretirada"));
             
             lista.add(locacao);
          }
      } catch (SQLException ex) {
          System.out.println("Erro: " + ex.getMessage());
      }     
     return lista;
  }
    public boolean salvar(){
        Connection con = Conexao.conectar();
        String sql = "insert into locacao(idcarro,cpfcliente,dataretirada,data, datadevolucao)";
               sql += "values(?,?,?,?)";
      try {
          PreparedStatement stm = con.prepareStatement(sql);
          stm.setInt(1, this.idcarro);
          stm.setString(2, this.cfpcliente);
          stm.setDate(3, this.dataretirada);
          stm.setDate(4, this.data);
          stm.setDate(3, this.data);
          
          stm.execute();
      } catch (SQLException ex) {
          System.out.println("Erro: " + ex.getMessage());
      }
        return true;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public int getIdcarro() {
        return idcarro;
    }

    public void setIdcarro(int idcarro) {
        this.idcarro = idcarro;
    }

    public String getCfpcliente() {
        return cfpcliente;
    }

    public void setCfpcliente(String cfpcliente) {
        this.cfpcliente = cfpcliente;
    }

    public Date getDataretirada() {
        return dataretirada;
    }

    public void setDataretirada(Date dataretirada) {
        this.dataretirada = dataretirada;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
