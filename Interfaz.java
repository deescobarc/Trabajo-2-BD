/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.red.social;

/**
 *
 * @author Usuario
 */
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.math.*; 


public class Interfaz extends JFrame implements ActionListener{
    Container panel, panelI;
    JButton bIngresarUser, bEnviar, bCancelar;
    JFrame ingresar;
    JTextField tCodigo, tNombre, tAlias;
    
    public Interfaz(){
        //Se personaliza la ventana principal
        super("Los Gatos");
        setSize(300,300);
        setLocation(200,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        bIngresarUser = new JButton("Ingresar Usuario a la red Social");
        bIngresarUser.addActionListener(this);
        
        panel = this.getContentPane();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Red social los Gatos"));
        panel.add(bIngresarUser);
        
        //Se instancia la ventana para crear un usuario
        ingresar = new JFrame("Ingresar datos");
        ingresar.setSize(400,200);
        ingresar.setLocation(200,100);
        panelI = ingresar.getContentPane();
        panelI.setLayout(new GridLayout(4,2,20,20));
        panelI.add(new JLabel("          Código"));
        tCodigo = new JTextField();
        panelI.add(tCodigo);
        panelI.add(new JLabel("          Nombre"));
        tNombre = new JTextField();
        panelI.add(tNombre);
        panelI.add(new JLabel("          Alias"));
        tAlias = new JTextField();
        panelI.add(tAlias);
        bEnviar = new JButton("Enviar");
        bEnviar.addActionListener(this);
        bCancelar = new JButton("Cancelar");
        bCancelar.addActionListener(this);
        panelI.add(bEnviar);
        panelI.add(bCancelar);
        
        conexion();
    }
    
    public void actionPerformed(ActionEvent e) {
        String s = (String) e.getActionCommand();
        if(s.equals("Ingresar Usuario a la red Social")){
            System.out.println("Me presionaron");
            
            ingresar.setVisible(true);
        }
        if(s.equals("Enviar")){
            System.out.println("Me presionaron enviar");
            ingresar.setVisible(false);
            
            //Recupero los datos ingresados
            String codigo = tCodigo.getText();
            String nombre = tNombre.getText();
            String alias = tAlias.getText();
            
            ingresarUsuarioaBD(codigo, nombre, alias);
            
        }
    }
    
    public String ingresarUsuarioaBD(String codigo, String nombre, String alias){
        Connection conn;
        Statement sentencia;
        ResultSet resultado;
        System.out.println( "Conexión a la base de datos..." );
        
        try{// Se carga el driver JDBC-ODBC
            Class.forName ("oracle.jdbc.driver.OracleDriver");
        } catch( Exception e ) {
            System.out.println("No se pudo cargar el driver JDBC");
            return "No se pudo cargar el driver JDBC";             
            }
        try{ // Se establece la conexión con la base de datos
            conn = DriverManager.getConnection   
            ("jdbc:oracle:thin:@DESKTOP-AURP5RF:1521:xe","david", "david");
            sentencia = conn.createStatement();
        } catch( SQLException e ) {
            System.out.println( "No hay conexión con la base de datos." );
            return "";
        } 

        try {
            System.out.println( "Seleccionando..." );
            resultado = sentencia.executeQuery
            ("SELECT codigo FROM usuario");
            //Se recorren las tuplas retornadas
            while (resultado.next())
            {
                System.out.println(resultado.getInt("codigo"));
            }
            conn.close(); //Cierre de la conexión
            } catch( SQLException e ){       
             System.out.println("Error: " + 
              e.getMessage());
                }            
        System.out.println("Consulta finalizada.");
        return "";
    }
    
    public void conexion(){
        Connection conn;
        Statement sentencia;
        ResultSet resultado;
        System.out.println( "Conexión a la base de datos..." );
        
        try{// Se carga el driver JDBC-ODBC
            Class.forName ("oracle.jdbc.driver.OracleDriver");
        } catch( Exception e ) {
            System.out.println("No se pudo cargar el driver JDBC");
            return;             
            }
        try{ // Se establece la conexión con la base de datos
            conn = DriverManager.getConnection   
            ("jdbc:oracle:thin:@DESKTOP-AURP5RF:1521:xe","david", "david");
            sentencia = conn.createStatement();
        } catch( SQLException e ) {
            System.out.println( "No hay conexión con la base de datos." );
            return;
        } 

        try {
            System.out.println( "Seleccionando..." );
            resultado = sentencia.executeQuery
            ("SELECT codigo FROM usuario");
            //Se recorren las tuplas retornadas
            while (resultado.next())
            {
                System.out.println(resultado.getInt("codigo"));
            }
            conn.close(); //Cierre de la conexión
            } catch( SQLException e ){       
             System.out.println("Error: " + 
              e.getMessage());
                }            
        System.out.println("Consulta finalizada.");



    }
}
