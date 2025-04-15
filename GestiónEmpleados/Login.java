package GestiónEmpleados;

import javax.swing.*;

import GestiónEmpleados.Login.Logear;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Login {

    static class Logear {

        public static Connection conn = null;
    
        public static String ip_bueno;
        public static int puerto_bueno;
        public static String usuario_bueno;
        public static String contrasena_bueno;
        public static String nombre_base_de_datos_bueno;
    
        public void login(String ip, int puerto, String usuario, String contrasena, String nombre_base_de_datos, JFrame frame_iniciar_sesion) {
            String url = "jdbc:mysql://"+ip+":"+puerto+"/"+nombre_base_de_datos;
            try {
                conn = DriverManager.getConnection(url, usuario, contrasena);
                JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos", "Conexión exitosa a la base de datos", JOptionPane.INFORMATION_MESSAGE);
    
                ip_bueno = ip;
                puerto_bueno = puerto;
                usuario_bueno = usuario;
                contrasena_bueno = contrasena;
                nombre_base_de_datos_bueno = nombre_base_de_datos;
                
                GestionEmpleados gestion_empleados = new GestionEmpleados();
                gestion_empleados.iniciar_programa();
                
                frame_iniciar_sesion.dispose();
    
            } catch(SQLException e1) {
                JOptionPane.showMessageDialog(null, "Hubo un error al conectarse a la base de datos: "+ e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }

    public void iniciar_login() {
        JFrame frame_iniciar_sesion = new JFrame("Iniciar sesión a la base de datos");
        JLabel label_ip = new JLabel("Introduce la dirección IP de la base de datos:");
        JTextField textfield_ip = new JTextField();
        JLabel label_puerto = new JLabel("Introduce el puerto:");
        JTextField textfield_puerto = new JTextField();
        JLabel label_usuario = new JLabel("Introduce el nombre de usuario de la base de datos:");
        JTextField textfield_usuario = new JTextField();
        JLabel label_contrasena = new JLabel("Introduce la contraseña de la base de datos:");
        JTextField textfield_contrasena = new JTextField();
        JLabel label_nombre_base_de_datos = new JLabel("Introduce el nombre de la base de datos:");
        JTextField textfield_nombre_base_de_datos = new JTextField();
        
        JButton boton_enviar_login = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_componentes_3 = new JPanel();
        JPanel panel_componentes_4 = new JPanel();
        JPanel panel_componentes_5 = new JPanel();
        JPanel panel_componentes_6 = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_ip);
        panel_componentes_1.add(textfield_ip);

        panel_componentes_2.setVisible(true);
        panel_componentes_2.setSize(660, 50);
        panel_componentes_2.setLayout(new FlowLayout());

        panel_componentes_2.add(label_puerto);
        panel_componentes_2.add(textfield_puerto);

        panel_componentes_3.setVisible(true);
        panel_componentes_3.setSize(660, 50);
        panel_componentes_3.setLayout(new FlowLayout());

        panel_componentes_3.add(label_usuario);
        panel_componentes_3.add(textfield_usuario);

        panel_componentes_4.setVisible(true);
        panel_componentes_4.setSize(660, 50);
        panel_componentes_4.setLayout(new FlowLayout());

        panel_componentes_4.add(label_contrasena);
        panel_componentes_4.add(textfield_contrasena);

        panel_componentes_5.setVisible(true);
        panel_componentes_5.setSize(660, 50);
        panel_componentes_5.setLayout(new FlowLayout());

        panel_componentes_5.add(label_nombre_base_de_datos);
        panel_componentes_5.add(textfield_nombre_base_de_datos);

        panel_componentes_6.setVisible(true);
        panel_componentes_6.setSize(660, 50);
        panel_componentes_6.setLayout(new FlowLayout());

        panel_componentes_6.add(boton_enviar_login);

        frame_iniciar_sesion.requestFocusInWindow();
        frame_iniciar_sesion.setVisible(true);
        frame_iniciar_sesion.setSize(660, 550);
        frame_iniciar_sesion.setResizable(false);
        frame_iniciar_sesion.setLayout(new BoxLayout(frame_iniciar_sesion.getContentPane(), BoxLayout.Y_AXIS));

        frame_iniciar_sesion.add(panel_componentes_1);
        frame_iniciar_sesion.add(panel_componentes_2);
        frame_iniciar_sesion.add(panel_componentes_3);
        frame_iniciar_sesion.add(panel_componentes_4);
        frame_iniciar_sesion.add(panel_componentes_5);
        frame_iniciar_sesion.add(panel_componentes_6);

        textfield_ip.setPreferredSize(new Dimension(123, 80));
        textfield_puerto.setPreferredSize(new Dimension(123, 80));
        textfield_usuario.setPreferredSize(new Dimension(123, 80));
        textfield_contrasena.setPreferredSize(new Dimension(123, 80));
        textfield_nombre_base_de_datos.setPreferredSize(new Dimension(123, 80));

        boton_enviar_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = textfield_ip.getText();
                int puerto = Integer.parseInt(textfield_puerto.getText());
                String usuario = textfield_usuario.getText();
                String contrasena = textfield_contrasena.getText();
                String nombre_base_de_datos = textfield_nombre_base_de_datos.getText();
                Logear logear = new Logear();
                logear.login(ip, puerto, usuario, contrasena, nombre_base_de_datos, frame_iniciar_sesion);
            }
        });

    }
}