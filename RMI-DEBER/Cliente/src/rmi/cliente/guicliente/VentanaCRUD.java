package rmi.cliente.guicliente;

import rmi.cliente.clase.Cliente;
import rmi.servidor.clase.Persona;

import javax.swing.*;
import java.awt.*;

public class VentanaCRUD extends JFrame {
    private JTextField id, nombre, correo, cargo, sueldo;

    public VentanaCRUD() {
        setTitle("Gestión de Empleados");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        id = new JTextField(); nombre = new JTextField();
        correo = new JTextField(); cargo = new JTextField();
        sueldo = new JTextField();

        add(new JLabel("ID:")); add(id);
        add(new JLabel("Nombre:")); add(nombre);
        add(new JLabel("Correo:")); add(correo);
        add(new JLabel("Cargo:")); add(cargo);
        add(new JLabel("Sueldo:")); add(sueldo);

        JButton insertar = new JButton("Insertar");
        insertar.addActionListener(e -> {
            try {
                Persona p = new Persona(0, nombre.getText(), correo.getText(), cargo.getText(), Double.parseDouble(sueldo.getText()));
                JOptionPane.showMessageDialog(this, Cliente.insertar(p));
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        JButton consultar = new JButton("Consultar");
        consultar.addActionListener(e -> {
            try {
                Persona p = Cliente.consultar(Integer.parseInt(id.getText()));
                if (p != null) {
                    nombre.setText(p.getNombre());
                    correo.setText(p.getCorreo());
                    cargo.setText(p.getCargo());
                    sueldo.setText(String.valueOf(p.getSueldo()));
                } else {
                    JOptionPane.showMessageDialog(this, "No encontrado");
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        JButton actualizar = new JButton("Actualizar");
        actualizar.addActionListener(e -> {
            try {
                Persona p = new Persona(Integer.parseInt(id.getText()), nombre.getText(), correo.getText(), cargo.getText(), Double.parseDouble(sueldo.getText()));
                JOptionPane.showMessageDialog(this, Cliente.actualizar(p));
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        JButton eliminar = new JButton("Eliminar");
        eliminar.addActionListener(e -> {
            try {
                JOptionPane.showMessageDialog(this, Cliente.eliminar(Integer.parseInt(id.getText())));
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        add(insertar); add(consultar);
        add(actualizar); add(eliminar);

        setVisible(true);
    }
}
