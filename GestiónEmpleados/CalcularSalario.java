package GestiónEmpleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalcularSalario {
    public static void calcular_salario() {
        JFrame frame_calcular_salario = new JFrame("Calcular Salario");
        JLabel label_dinero_por_hora = new JLabel("Introduce el dinero que gana el empleado por hora:");
        JTextField textfield_dinero_por_hora = new JTextField();
        JLabel label_horas_semanales = new JLabel("Introduce las horas semanales del empleado:");
        JTextField textfield_horas_semanales = new JTextField();
        JButton boton_enviar_salario = new JButton("Enviar");

        JPanel panel_componentes_1 = new JPanel();
        JPanel panel_componentes_2 = new JPanel();
        JPanel panel_boton_enviar_salario = new JPanel();

        panel_componentes_1.add(label_dinero_por_hora);
        panel_componentes_1.add(textfield_dinero_por_hora);

        panel_componentes_1.setLayout(new FlowLayout());

        panel_componentes_2.add(label_horas_semanales);
        panel_componentes_2.add(textfield_horas_semanales);

        panel_componentes_2.setLayout(new FlowLayout());

        panel_boton_enviar_salario.add(boton_enviar_salario);
        
        panel_boton_enviar_salario.setLayout(new FlowLayout());

        frame_calcular_salario.requestFocusInWindow();
        frame_calcular_salario.setVisible(true);
        frame_calcular_salario.setSize(660, 550);
        frame_calcular_salario.setResizable(false);
        frame_calcular_salario.setLayout(new BoxLayout(frame_calcular_salario.getContentPane(), BoxLayout.Y_AXIS));

        textfield_dinero_por_hora.setPreferredSize(new Dimension(123, 80));
        textfield_horas_semanales.setPreferredSize(new Dimension(123, 80));

        frame_calcular_salario.add(panel_componentes_1);
        frame_calcular_salario.add(panel_componentes_2);
        frame_calcular_salario.add(panel_boton_enviar_salario);

        boton_enviar_salario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double dinero_por_hora = Double.parseDouble(textfield_dinero_por_hora.getText());
                double horas_semanales = Double.parseDouble(textfield_horas_semanales.getText());

                double multi_horas_semanales = horas_semanales * 4;
                double operacion_final = dinero_por_hora * multi_horas_semanales;

                JOptionPane.showMessageDialog(null, "Salario neto del empleado al final del mes: "+ operacion_final, "Salario neto del empleado", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
}