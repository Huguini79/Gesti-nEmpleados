package GestiónEmpleados;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Empleado {
    public static void empleado() {

        JFrame frame_empleado = new JFrame("Empleados");
        
        JButton boton_agregar = new JButton("Agregar Empleado");
        JButton boton_editar = new JButton("Editar Empleado");
        JButton boton_eliminar = new JButton("Eliminar Empleado");
        
        JPanel panel_botones = new JPanel();
        panel_botones.setVisible(true);
        panel_botones.setSize(660, 200);
        panel_botones.setLayout(new FlowLayout());

        panel_botones.add(boton_agregar);
        panel_botones.add(boton_editar);
        panel_botones.add(boton_eliminar);

        frame_empleado.requestFocusInWindow();
        frame_empleado.setVisible(true);
        frame_empleado.setSize(660, 550);
        frame_empleado.setResizable(false);
        frame_empleado.setLayout(new FlowLayout());

        frame_empleado.add(panel_botones);

        boton_agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Agregar agregar = new Agregar();
                agregar.agregar();
            }
        });
        boton_editar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Editar editar = new Editar();
                editar.editar();
            }
        });
        boton_eliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Eliminar eliminar = new Eliminar();
                eliminar.eliminar();
            }
        });

    }
}

class Agregar {
    public void agregar() {
        JFrame frame_agregar = new JFrame("Agregar Empleado");

        JLabel label_nombre_empleado = new JLabel("Introduce el nombre del empleado:");
        JTextField textfield_nombre_de_empleado = new JTextField();

        JLabel label_edad_empleado = new JLabel("Introduce la edad del empleado:");
        JTextField textfield_edad_empleado = new JTextField();

        JLabel label_cargo_empleado = new JLabel("Introduce el cargo del empleado:");
        JTextField textfield_cargo_empleado = new JTextField();

        JButton boton_enviar_agregar = new JButton("Enviar");
        
        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_componentes_3 = new JPanel();
        JPanel panel_boton_enviar = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_nombre_empleado);
        panel_componentes_1.add(textfield_nombre_de_empleado);

        panel_componentes_2.setVisible(true);
        panel_componentes_2.setSize(660, 50);
        panel_componentes_2.setLayout(new FlowLayout());

        panel_componentes_2.add(label_edad_empleado);
        panel_componentes_2.add(textfield_edad_empleado);

        panel_componentes_3.setVisible(true);
        panel_componentes_3.setSize(660, 50);
        panel_componentes_3.setLayout(new FlowLayout());

        panel_componentes_3.add(label_cargo_empleado);
        panel_componentes_3.add(textfield_cargo_empleado);

        panel_boton_enviar.setVisible(true);
        panel_boton_enviar.setSize(660, 50);
        panel_boton_enviar.setLayout(new FlowLayout());

        panel_boton_enviar.add(boton_enviar_agregar);

        frame_agregar.requestFocusInWindow();
        frame_agregar.setVisible(true);
        frame_agregar.setSize(660, 550);
        frame_agregar.setResizable(false);
        frame_agregar.setLayout(new BoxLayout(frame_agregar.getContentPane(), BoxLayout.Y_AXIS));

        textfield_nombre_de_empleado.setPreferredSize(new Dimension(123, 80));
        textfield_edad_empleado.setPreferredSize(new Dimension(123, 80));
        textfield_cargo_empleado.setPreferredSize(new Dimension(123, 80));

        frame_agregar.add(panel_componentes_1);
        frame_agregar.add(panel_componentes_2);
        frame_agregar.add(panel_componentes_3);
        frame_agregar.add(panel_boton_enviar);

        boton_enviar_agregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre_empleado = textfield_nombre_de_empleado.getText();
                String edad_empleado = textfield_edad_empleado.getText();
                String cargo_empleado = textfield_cargo_empleado.getText();

                String query = "INSERT INTO empleados (nombre, edad, cargo) VALUES ('" + nombre_empleado + "', '" + edad_empleado + "', '" + cargo_empleado + "')";

                Login.Logear logear = new Login.Logear();
                Connection conn = logear.conn;

                if(conn != null) {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate(query);
                        JOptionPane.showMessageDialog(null, "Empleado insertado en la base de datos exitosamente.", "Empleado insertado en la base de datos exitosamente", JOptionPane.INFORMATION_MESSAGE);

                    } catch(SQLException e2) {
                        JOptionPane.showMessageDialog(null, "Hubo un error al insertar al empleado en la base de datos: "+ e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica tu conexión a Internet.", "Error", JOptionPane.ERROR);
                }

            }
        });
    }
}

class Editar {

    public void enviar_editar_nombre(String nombre_original, String nombre_nuevo) {

        String query = "UPDATE empleados SET nombre = '" + nombre_nuevo + "' WHERE nombre = '" + nombre_original + "'";

        Login.Logear logear = new Login.Logear();
        Connection conn = logear.conn;

        if(conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "El nombre del empleado fue editado exitosamente", "Nombre del empleado editado exitosamente", JOptionPane.INFORMATION_MESSAGE);

            } catch(SQLException e4) {
                JOptionPane.showMessageDialog(null, "Hubo un error al editar el nombre del empleado: "+ e4.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica tu internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editar_nombre() {

        JFrame frame_editar_nombre = new JFrame("Editar nombre");
        
        JLabel label_nombre_empleado_original = new JLabel("Introduce el nombre original del empleado:");
        JTextField textfield_nombre_empleado_original = new JTextField();
        JLabel label_nombre_empleado_nuevo = new JLabel("Introduce el nuevo nombre del empleado:");
        JTextField textfield_nombre_empleado_nuevo = new JTextField();

        JButton boton_enviar_editar_nombre = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_boton_enviar_editar_nombre = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_nombre_empleado_original);
        panel_componentes_1.add(textfield_nombre_empleado_original);

        panel_componentes_2.setVisible(true);
        panel_componentes_2.setSize(660, 50);
        panel_componentes_2.setLayout(new FlowLayout());

        panel_componentes_2.add(label_nombre_empleado_nuevo);
        panel_componentes_2.add(textfield_nombre_empleado_nuevo);

        panel_boton_enviar_editar_nombre.setVisible(true);
        panel_boton_enviar_editar_nombre.setSize(660, 50);
        panel_boton_enviar_editar_nombre.setLayout(new FlowLayout());

        panel_boton_enviar_editar_nombre.add(boton_enviar_editar_nombre);

        frame_editar_nombre.requestFocusInWindow();
        frame_editar_nombre.setVisible(true);
        frame_editar_nombre.setSize(660, 550);
        frame_editar_nombre.setResizable(false);
        frame_editar_nombre.setLayout(new BoxLayout(frame_editar_nombre.getContentPane(), BoxLayout.Y_AXIS));

        frame_editar_nombre.add(panel_componentes_1);
        frame_editar_nombre.add(panel_componentes_2);
        frame_editar_nombre.add(panel_boton_enviar_editar_nombre);

        textfield_nombre_empleado_original.setPreferredSize(new Dimension(123, 80));
        textfield_nombre_empleado_nuevo.setPreferredSize(new Dimension(123, 80));

        boton_enviar_editar_nombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre_original = textfield_nombre_empleado_original.getText();
                String nombre_nuevo = textfield_nombre_empleado_nuevo.getText();
                enviar_editar_nombre(nombre_original, nombre_nuevo);
            }
        });
    }

    public void enviar_edad_nueva(String nombre_empleado, String edad_nueva) {
        
        String query = "UPDATE empleados SET edad = '" + edad_nueva + "' WHERE nombre = '" + nombre_empleado + "'";

        Login.Logear logear = new Login.Logear();
        Connection conn = logear.conn;

        if(conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "La edad del empleado ha sido editada exitosamente", "Edad del empleado editada exitosamente.", JOptionPane.INFORMATION_MESSAGE);

            } catch(SQLException e5) {
                JOptionPane.showMessageDialog(null, "Hubo un error al editar la edad del empleado: "+ e5.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica el internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editar_edad() {

        JFrame frame_editar_edad = new JFrame("Editar edad");

        JLabel label_nombre_empleado = new JLabel("Introduce el nombre del empleado:");
        JTextField textfield_nombre_empleado = new JTextField();
        JLabel label_edad_nueva = new JLabel("Introduce la edad nueva del empleado:");
        JTextField textfield_edad_nueva = new JTextField();

        JButton boton_enviar_edad_nueva = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_boton_enviar_edad_nueva = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_nombre_empleado);
        panel_componentes_1.add(textfield_nombre_empleado);

        panel_componentes_2.setVisible(true);
        panel_componentes_2.setSize(660, 50);
        panel_componentes_2.setLayout(new FlowLayout());

        panel_componentes_2.add(label_edad_nueva);
        panel_componentes_2.add(textfield_edad_nueva);

        panel_boton_enviar_edad_nueva.setVisible(true);
        panel_boton_enviar_edad_nueva.setSize(660, 50);
        panel_boton_enviar_edad_nueva.setLayout(new FlowLayout());

        panel_boton_enviar_edad_nueva.add(boton_enviar_edad_nueva);

        frame_editar_edad.requestFocusInWindow();
        frame_editar_edad.setVisible(true);
        frame_editar_edad.setSize(660, 550);
        frame_editar_edad.setResizable(false);
        frame_editar_edad.setLayout(new BoxLayout(frame_editar_edad.getContentPane(), BoxLayout.Y_AXIS));

        frame_editar_edad.add(panel_componentes_1);
        frame_editar_edad.add(panel_componentes_2);
        frame_editar_edad.add(panel_boton_enviar_edad_nueva);

        textfield_nombre_empleado.setPreferredSize(new Dimension(123, 80));
        textfield_edad_nueva.setPreferredSize(new Dimension(123, 80));

        boton_enviar_edad_nueva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre_empleado = textfield_nombre_empleado.getText();
                String edad_nueva = textfield_edad_nueva.getText();
                enviar_edad_nueva(nombre_empleado, edad_nueva);
            }
        });
    }

    public void enviar_editar_cargo(String nombre_empleado, String cargo_nuevo) {

        String query = "UPDATE empleados SET cargo = '" + cargo_nuevo + "' WHERE nombre = '" + nombre_empleado + "'";

        Login.Logear logear = new Login.Logear();
        Connection conn = logear.conn;

        if(conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "El cargo del empleado ha sido editado exitosamente:", "Cargo del empleado editado exitosamente", JOptionPane.INFORMATION_MESSAGE);

            } catch(SQLException e6) {
                JOptionPane.showMessageDialog(null, "Hubo un error al editar el cargo del empleado: "+ e6.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica el internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editar_cargo() {
        
        JFrame frame_editar_cargo = new JFrame("Editar Cargo");
        JLabel label_nombre_empleado = new JLabel("Introduce el nombre del empleado:");
        JTextField textfield_nombre_empleado = new JTextField();
        JLabel label_cargo_nuevo = new JLabel("Introduce el nuevo cargo:");
        JTextField textfield_cargo_nuevo = new JTextField();

        JButton boton_enviar_cargo = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_boton_enviar_cargo = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_nombre_empleado);
        panel_componentes_1.add(textfield_nombre_empleado);

        panel_componentes_2.setVisible(true);
        panel_componentes_2.setSize(660, 50);
        panel_componentes_2.setLayout(new FlowLayout());

        panel_componentes_2.add(label_cargo_nuevo);
        panel_componentes_2.add(textfield_cargo_nuevo);

        panel_boton_enviar_cargo.setVisible(true);
        panel_boton_enviar_cargo.setSize(660, 50);
        panel_boton_enviar_cargo.setLayout(new FlowLayout());

        panel_boton_enviar_cargo.add(boton_enviar_cargo);

        frame_editar_cargo.requestFocusInWindow();
        frame_editar_cargo.setVisible(true);
        frame_editar_cargo.setSize(660, 550);
        frame_editar_cargo.setResizable(false);
        frame_editar_cargo.setLayout(new BoxLayout(frame_editar_cargo.getContentPane(), BoxLayout.Y_AXIS));

        frame_editar_cargo.add(panel_componentes_1);
        frame_editar_cargo.add(panel_componentes_2);
        frame_editar_cargo.add(panel_boton_enviar_cargo);

        textfield_nombre_empleado.setPreferredSize(new Dimension(123, 80));
        textfield_cargo_nuevo.setPreferredSize(new Dimension(123, 80));

        boton_enviar_cargo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre_empleado = textfield_nombre_empleado.getText();
                String cargo_nuevo = textfield_cargo_nuevo.getText();
                enviar_editar_cargo(nombre_empleado, cargo_nuevo);
            }
        });

    }

    public void editar() {

        JFrame frame_editar = new JFrame("Editar empleado");
        
        JButton boton_editar_nombre = new JButton("Editar nombre de empleado");
        JButton boton_editar_edad = new JButton("Editar edad de empleado");
        JButton boton_editar_cargo = new JButton("Editar cargo de empleado");

        JPanel panel_botones = new JPanel();
        panel_botones.setVisible(true);
        panel_botones.setSize(660, 220);
        panel_botones.setLayout(new FlowLayout());

        panel_botones.add(boton_editar_nombre);
        panel_botones.add(boton_editar_edad);
        panel_botones.add(boton_editar_cargo);

        frame_editar.requestFocusInWindow();
        frame_editar.setVisible(true);
        frame_editar.setSize(660, 550);
        frame_editar.setResizable(false);
        frame_editar.setLayout(new BoxLayout(frame_editar.getContentPane(), BoxLayout.Y_AXIS));

        frame_editar.add(panel_botones);

        boton_editar_nombre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar_nombre();
            }
        });
        boton_editar_edad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar_edad();
            }
        });
        boton_editar_cargo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar_cargo();
            }
        });

    }
}

class Eliminar {

    public void enviar_eliminar(String nombre_empleado) {

        String query = "DELETE FROM empleados WHERE nombre = '" + nombre_empleado + "'";

        Login.Logear logear = new Login.Logear();
        Connection conn = logear.conn;


        if(conn != null) {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado exitosamente.", "Empleado eliminado exitosamente", JOptionPane.INFORMATION_MESSAGE);

            } catch(SQLException e7) {
                JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el empleado: "+ e7.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Conexión interrumpida, verifica el internet.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminar() {
        
        JFrame frame_eliminar = new JFrame("Eliminar Empleado");

        JLabel label_nombre_empleado = new JLabel("Introduce el nombre del empleado:");
        JTextField textfield_nombre_empleado = new JTextField();

        JButton boton_enviar = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_boton_enviar = new JPanel();

        panel_componentes_1.setVisible(true);
        panel_componentes_1.setSize(660, 50);
        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_1.add(label_nombre_empleado);
        panel_componentes_1.add(textfield_nombre_empleado);

        panel_boton_enviar.setVisible(true);
        panel_boton_enviar.setSize(660, 50);
        panel_boton_enviar.setLayout(new FlowLayout());


        panel_boton_enviar.add(boton_enviar);


        frame_eliminar.requestFocusInWindow();
        frame_eliminar.setVisible(true);
        frame_eliminar.setSize(660, 550);
        frame_eliminar.setResizable(false);
        frame_eliminar.setLayout(new BoxLayout(frame_eliminar.getContentPane(), BoxLayout.Y_AXIS));

        frame_eliminar.add(panel_componentes_1);
        frame_eliminar.add(panel_boton_enviar);

        textfield_nombre_empleado.setPreferredSize(new Dimension(123, 80));

        boton_enviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nombre_empleado = textfield_nombre_empleado.getText();
                enviar_eliminar(nombre_empleado);

            }
        });

    }
}