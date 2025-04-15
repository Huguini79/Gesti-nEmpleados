package GestiónEmpleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionEmpleados {

    public void iniciar_programa() {

       String query = "CREATE TABLE IF NOT EXISTS empleados (" +
                "nombre VARCHAR(100), " +
                "edad VARCHAR(100), " +
                "cargo VARCHAR(100)" +
                ");";

        String query2 = "CREATE TABLE IF NOT EXISTS eventos (" +
                "evento VARCHAR(100), " +
                "fecha VARCHAR(100)" +
                ");";


                Login.Logear logear = new Login.Logear();
                Connection conn = logear.conn;

                if(conn != null) {
                    try {
                        Statement stmt = conn.createStatement();
                        Statement stmt2 = conn.createStatement();
                        stmt.executeUpdate(query);
                        stmt2.executeUpdate(query2);

                    } catch(SQLException e8) {
                        JOptionPane.showMessageDialog(null, "Hubo un error al crear la tabla empleados y la de eventos: "+ e8.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica tu internet.", "Error", JOptionPane.ERROR_MESSAGE);
                }


        JFrame frame_principal = new JFrame("Gestión de Empleados");

        JLabel label_bienvenida = new JLabel("Bienvenido al software de Gestión de Empleados");
        JButton boton_empleados = new JButton("Empleados");
        JButton boton_registro_de_empleados = new JButton("Registro de Empleados");
        JButton boton_calcular_salario = new JButton("Calcular Salario");
        JButton boton_ver_empleados = new JButton("Ver empleados");
        JButton boton_buscar_empleado = new JButton("Buscar empleado");
        JButton boton_cerrar_sesion = new JButton("Cerrar Sesión");

        JPanel panel_principal = new JPanel();
        JPanel panel_botones = new JPanel();

        panel_principal.setVisible(true);
        panel_principal.setSize(660, 200);
        panel_principal.setLayout(new FlowLayout());

        panel_principal.add(label_bienvenida);

        panel_botones.setVisible(true);
        panel_botones.setSize(660, 200);
        panel_botones.setLayout(new FlowLayout());

        panel_botones.add(boton_empleados);
        panel_botones.add(boton_registro_de_empleados);
        panel_botones.add(boton_calcular_salario);
        panel_botones.add(boton_ver_empleados);
        panel_botones.add(boton_buscar_empleado);
        panel_botones.add(boton_cerrar_sesion);

        frame_principal.requestFocusInWindow();
        frame_principal.setVisible(true);
        frame_principal.setFocusable(false);
        frame_principal.setSize(660, 550);
        frame_principal.setResizable(false);
        frame_principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_principal.setLayout(new BoxLayout(frame_principal.getContentPane(), BoxLayout.Y_AXIS));
        
        frame_principal.add(panel_principal);
        frame_principal.add(panel_botones);

        boton_empleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado();
                empleado.empleado();
            }
        });
        
        boton_registro_de_empleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroEmpleado registro_empleado = new RegistroEmpleado();
                registro_empleado.registro_empleado();
            }
        });
        boton_calcular_salario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalcularSalario calcular_salario = new CalcularSalario();
                calcular_salario.calcular_salario();
            }
        });

        boton_ver_empleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerEmpleados ver_empleados = new VerEmpleados();
                ver_empleados.ver_empleados();
            }
        });

        boton_buscar_empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuscarEmpleado buscar_empleado = new BuscarEmpleado();
                buscar_empleado.buscar_empleado();
            }
        });

        boton_cerrar_sesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pregunta_salir = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE QUE QUIERE CERRAR LA SESIÓN EN EL PROGRAMA?, TODOS LOS DATOS NO GUARDADOS SERÁN PERDIDOS.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                if(pregunta_salir == JOptionPane.YES_OPTION) {
                    System.exit(0);

                } else {
                    JOptionPane.showMessageDialog(null, "Ok, puede continuar con el software tranquilamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }
    public static void main(String[] args) {
        Login login = new Login();
        login.iniciar_login();
    }
}