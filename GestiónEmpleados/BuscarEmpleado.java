package GestiónEmpleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class BuscarEmpleado {

    public void enviar_buscar_empleado(String nombre_empleado) {
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
                ResultSet rs = stmt.executeQuery("SELECT * FROM empleados WHERE nombre LIKE '%"+nombre_empleado+"%'");
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
                JOptionPane.showMessageDialog(null, "Hubo un error al mostrar el empleado: "+ e9.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica el internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscar_empleado() {
        JFrame frame_buscar_empleado = new JFrame("Buscar Empleado");
        JLabel label_buscar_empleado = new JLabel("Introduce la inicial , el nombre entero o el nombre casi entero del empleado:");
        JTextField textfield_buscar_empleado = new JTextField();
        
        JButton boton_enviar_buscar_empleado = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_boton_enviar_buscar_empleado = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_buscar_empleado);
        panel_componentes_1.add(textfield_buscar_empleado);

        panel_boton_enviar_buscar_empleado.setVisible(true);
        panel_boton_enviar_buscar_empleado.setSize(660, 50);
        panel_boton_enviar_buscar_empleado.setLayout(new FlowLayout());

        panel_boton_enviar_buscar_empleado.add(boton_enviar_buscar_empleado);

        frame_buscar_empleado.requestFocusInWindow();
        frame_buscar_empleado.setVisible(true);
        frame_buscar_empleado.setSize(660, 550);
        frame_buscar_empleado.setResizable(false);
        frame_buscar_empleado.setLayout(new BoxLayout(frame_buscar_empleado.getContentPane(), BoxLayout.Y_AXIS));

        frame_buscar_empleado.add(panel_componentes_1);
        frame_buscar_empleado.add(panel_boton_enviar_buscar_empleado);

        textfield_buscar_empleado.setPreferredSize(new Dimension(123, 80));

        boton_enviar_buscar_empleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre_empleado = textfield_buscar_empleado.getText();
                enviar_buscar_empleado(nombre_empleado);
            }
        });

    }
}