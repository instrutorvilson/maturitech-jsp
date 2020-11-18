<%-- 
    Document   : cadastrodevolucao
    Created on : 18 de nov de 2020, 10:58:34
    Author     : entra21
--%>

<%@page import="modelos.Carro"%>
<%@page import="java.sql.Date"%>
<%@page import="modelos.Locacao"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
           String idlocacao = request.getParameter("idlocacao");
           Locacao locacao = new Locacao();
           ResultSet rs = locacao.consultar(Integer.parseInt(idlocacao));
           String placa = "";
           String cliente = "";
           if (rs.next()){
               locacao.setId(rs.getInt("id"));
               placa = rs.getString("placa") + " - " + rs.getString("modelo");
               cliente = rs.getString("cpfcliente") + " - " + rs.getString("nome");
           }
          // 
           String km = request.getParameter("km");
           if (km != null){   
               String datadevolucao = request.getParameter("datadevolucao");
               locacao.consultar(idlocacao);
               
               locacao.setDatadevolucao(Date.valueOf(datadevolucao));
               //chamar metodo para atualizar locacao
               
               //atualiz km do carro
               Carro carro = new Carro();
               carro = carro.consultar(rs.getString("placa"));
               carro.setKm(Integer.parseInt(km));
               carro.alterar();
           }   
          
        %>
        <h1>Registrar devolução</h1>
        <hr />
        <form action="">
            <label>Id Locação</label> 
            <input type="text" readonly="true" value="<%out.write(""+locacao.getId());%>" /><br />
            
            <label>Placa Carro</label>
            <input type="text" readonly="true" value="<%out.write(placa);%>" /> <br />
            <label>Cliente</label> 
            <input type="text" readonly="true" value="<%out.write(cliente);%>" /><br />
            
            <label>Data devolução</label>
            <input type="date" name="datadevolucao" /> <br />
           
            <label>Informe km do veículo</label>
            <input type="number" name="km" />
            <hr />
            <input type="submit" value="Registrar" />
        </form>
    </body>
</html>
