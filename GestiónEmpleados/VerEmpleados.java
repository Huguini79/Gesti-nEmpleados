package GestiónEmpleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class VerEmpleados {

    public void mostrar_empleados() {
        
        JFrame frame_mostrar_empleados = new JFrame("Empleados");
        JTextArea textarea_ver_empleados = new JTextArea();
        textarea_ver_empleados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textarea_ver_empleados);
        frame_mostrar_empleados.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame_mostrar_empleados.requestFocusInWindow();
        frame_mostrar_empleados.setVisible(true);
        frame_mostrar_empleados.setSize(660, 550);
        frame_mostrar_empleados.setResizable(false);
        frame_mostrar_empleados.setLayout(new FlowLayout());

        frame_mostrar_empleados.add(textarea_ver_empleados);

        Login.Logear logear = new Login.Logear();
        Connection conn = logear.conn;

        String query = "USE "+ Login.Logear.nombre_base_de_datos_bueno;

        if(conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");
                StringBuilder resultado = new StringBuilder();
                while (rs.next()) {
                    resultado.append("Nombre")
                            .append(" - Edad")
                            .append(" - Cargo")
                            .append("\n")
                            .append(rs.getString("nombre")+"  ")
                            .append(rs.getString("edad")+"  ")
                            .append(rs.getString("cargo")+"  ")
                            .append("\n\n");
                }
                textarea_ver_empleados.append(resultado.toString());

            } catch(SQLException e9) {
                JOptionPane.showMessageDialog(null, "Hubo un error al mostrar los empleados: "+ e9.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica el internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void ver_empleados() {

        JFrame frame_ver_empleados = new JFrame("Ver Empleados");
        
        JButton boton_mostrar_empleados = new JButton("Mostrar Empleados");

        frame_ver_empleados.requestFocusInWindow();
        frame_ver_empleados.setVisible(true);
        frame_ver_empleados.setSize(660, 550);
        frame_ver_empleados.setResizable(false);
        frame_ver_empleados.setLayout(new FlowLayout());

        frame_ver_empleados.add(boton_mostrar_empleados);

        boton_mostrar_empleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrar_empleados();
            }
        });

    }
}