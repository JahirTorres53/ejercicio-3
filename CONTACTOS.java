import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class GestorContactos extends JFrame {

    private JTextField txtNombre, txtTelefono;
    private JButton btnAgregar, btnEliminar, btnModificar;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private static final String ARCHIVO = "contactos.txt";

    public GestorContactos() {
        setTitle("Gestor de Contactos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel superior con campos de texto y botones
        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelSuperior.add(txtNombre);

        panelSuperior.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelSuperior.add(txtTelefono);

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnModificar);

        // Panel inferior para eliminar
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnEliminar);

        // Tabla para mostrar contactos
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Teléfono"}, 0);
        tablaContactos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaContactos);

        // Layout principal
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        btnModificar.addActionListener(e -> modificarContacto());

        // Cargar contactos al iniciar
        cargarContactos();

        // Guardar contactos al cerrar
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                guardarContactos();
            }
        });
    }

    private void agregarContacto() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();

        if (!nombre.isEmpty() && !telefono.isEmpty()) {
            modeloTabla.addRow(new Object[]{nombre, telefono});
            txtNombre.setText("");
            txtTelefono.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Debe llenar ambos campos.");
        }
    }

    private void eliminarContacto() {
        int fila = tablaContactos.getSelectedRow();
        if (fila >= 0) {
            modeloTabla.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para eliminar.");
        }
    }

    private void modificarContacto() {
        int fila = tablaContactos.getSelectedRow();
        if (fila >= 0) {
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (!nombre.isEmpty() && !telefono.isEmpty()) {
                modeloTabla.setValueAt(nombre, fila, 0);
                modeloTabla.setValueAt(telefono, fila, 1);
                txtNombre.setText("");
                txtTelefono.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Debe llenar ambos campos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto para modificar.");
        }
    }

    private void cargarContactos() {
        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(";");
                    if (datos.length == 2) {
                        modeloTabla.addRow(new Object[]{datos[0], datos[1]});
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar contactos.");
            }
        }
    }

    private void guardarContactos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String nombre = modeloTabla.getValueAt(i, 0).toString();
                String telefono = modeloTabla.getValueAt(i, 1).toString();
                bw.write(nombre + ";" + telefono);
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar contactos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestorContactos().setVisible(true);
        });
    }
}
