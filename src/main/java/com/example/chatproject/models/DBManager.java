package com.example.chatproject.models;


import java.sql.*;

public class DBManager {
    private static Connection con = DBconnection.getConnection();
    private static PreparedStatement sentencia = null;
    private static ResultSet resultado = null;
    public static String consultas(){
        con=DBconnection.getConnection();
        if(con!=null){
            try {
                sentencia = con.prepareStatement("Select idcliente, (persona::persona_type).nombre, " +
                        "(persona::persona_type).direccion, tlf from clientes;");
                resultado = sentencia.executeQuery();
                ResultSetMetaData metaData = resultado.getMetaData();
                int columnCount = metaData.getColumnCount();
                StringBuilder result = new StringBuilder();

                while (resultado.next()) {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object value = resultado.getObject(columnName);
                        row.append(columnName).append(":").append(value).append(" ");
                    }
                    result.append(row).append("\n");
                }
                DBconnection.closeConnection();
                sentencia.close();
                return result.toString();
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
