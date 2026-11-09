package controlador;

import modelo.*;
import vista.ventana;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//Definición de la clase logica_ventana que implementa eventos.
public class logica_ventana implements ActionListener {

    private ventana delegado; // Referencia a la ventana principal que contiene la GUI.
    private List<persona> contactos; // Lista de objetos persona que representa todos los contactos.

    // Constructor que inicializa la clase y configura los escuchadores de eventos para los componentes de la GUI.
    public logica_ventana(ventana delegado) {
        // Asigna la ventana recibida como parámetro a la variable de instancia delegado.
        this.delegado = delegado;
        // Carga los contactos almacenados al inicializar.
        cargarContactosRegistrados();
        // Registra los ActionListener para los botones de la GUI.
        delegado.btn_add.addActionListener(this);
        delegado.btn_modificar.addActionListener(this);
        delegado.btn_eliminar.addActionListener(this);
        //delegado.btn_exportar.addActionListener(this);
       // delegado.btn_buscar.addActionListener(this); // botón BUSCAR

        // Evento de búsqueda con Enter en el campo de texto
       // delegado.txt_buscar.addActionListener(e -> filtrar(delegado.txt_buscar.getText()));
    }

    // Método para limpiar los campos de entrada
    private void limpiarCampos() {
        delegado.txt_nombres.setText("");
        delegado.txt_telefono.setText("");
        delegado.txt_email.setText("");
        //delegado.chb_favorito.setSelected(false);
        //delegado.cmb_categoria.setSelectedIndex(0); // vuelve a "Elija una Categoria"
    }

    // Cargar contactos desde archivo y mostrarlos en la tabla
    //private void cargarContactosRegistrados() {
        //try {
           // delegado.barraProgreso.setValue(0);

            // Hilo para simular carga progresiva
           // new Thread(() -> {
                //for (int i = 0; i <= 100; i++) {
                    //try {
                    //    Thread.sleep(15);
                  //  } catch (InterruptedException ex) {
                //    }
              //      delegado.barraProgreso.setValue(i);
            //    }
          //  }).start();*/

            // Lee los contactos almacenados utilizando una instancia de personaDAO.
          //  contactos = new personaDAO(new persona()).leerArchivo();
            //delegado.modeloTabla.setRowCount(0);
            // Agrega cada contacto al modelo de la lista de contactos de la GUI.
            for (persona p : contactos) {
                delegado.modeloTabla.addRow(new Object[]{
                    p.getNombre(), p.getTelefono(), p.getEmail(),
                    p.getCategoria(), p.isFavorito() ? "Sí" : "No"
                });
            }
            actualizarEstadisticas();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(delegado, "Error al cargar contactos");
        }
    }

    // Filtrar contactos por nombre
    public void filtrar(String texto) {
        delegado.modeloTabla.setRowCount(0);
        boolean encontrado = false;
        for (persona p : contactos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                delegado.modeloTabla.addRow(new Object[]{
                    p.getNombre(), p.getTelefono(), p.getEmail(),
                    p.getCategoria(), p.isFavorito() ? "Sí" : "No"
                });
                encontrado = true;
            }
        }
        // Si no se encontró nada, volver a mostrar todos los contactos
        if (!encontrado) {
            for (persona p : contactos) {
                delegado.modeloTabla.addRow(new Object[]{
                    p.getNombre(), p.getTelefono(), p.getEmail(),
                    p.getCategoria(), p.isFavorito() ? "Sí" : "No"
                });
            }
        }
    }

    // Actualizar estadísticas en la pestaña correspondiente
    private void actualizarEstadisticas() {
        int total = contactos.size();
        long favoritos = contactos.stream().filter(persona::isFavorito).count();
        long familia = contactos.stream().filter(p -> p.getCategoria().equals("Familia")).count();
        long amigos = contactos.stream().filter(p -> p.getCategoria().equals("Amigos")).count();
        long trabajo = contactos.stream().filter(p -> p.getCategoria().equals("Trabajo")).count();

        delegado.lblStats.setText(
                "<html>Total: " + total
                + "<br>Favoritos: " + favoritos
                + "<br>Familia: " + familia
                + "<br>Amigos: " + amigos
                + "<br>Trabajo: " + trabajo + "</html>"
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delegado.btn_add) {
            persona p = new persona(
                    delegado.txt_nombres.getText(),
                    delegado.txt_telefono.getText(),
                    delegado.txt_email.getText(),
                    delegado.cmb_categoria.getSelectedItem().toString(),
                    delegado.chb_favorito.isSelected()
            );
            new personaDAO(p).escribirArchivo();
            cargarContactosRegistrados();
            JOptionPane.showMessageDialog(delegado, "Contacto agregado");
            limpiarCampos();// limpiar después de agregar
        }

        if (e.getSource() == delegado.btn_eliminar) {
            int fila = delegado.tablaContactos.getSelectedRow();
            if (fila != -1) {
                persona p = contactos.get(fila);
                try {
                    new personaDAO(p).eliminarArchivo(p);
                    cargarContactosRegistrados();
                    JOptionPane.showMessageDialog(delegado, "Contacto eliminado");
                    limpiarCampos(); // limpiar después de eliminar
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(delegado, "Error al eliminar");
                }
            }
        }

        if (e.getSource() == delegado.btn_modificar) {
            int fila = delegado.tablaContactos.getSelectedRow();
            if (fila != -1) {
                persona p = new persona(
                        delegado.txt_nombres.getText(),
                        delegado.txt_telefono.getText(),
                        delegado.txt_email.getText(),
                        delegado.cmb_categoria.getSelectedItem().toString(),
                        delegado.chb_favorito.isSelected()
                );
                try {
                    new personaDAO(p).modificarArchivo(fila, p);
                    cargarContactosRegistrados();
                    JOptionPane.showMessageDialog(delegado, "Contacto modificado");
                     limpiarCampos(); // limpiar después de modificar
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(delegado, "Error al modificar");
                }
            }
        }

       /* if (e.getSource() == delegado.btn_exportar) {
            try (FileWriter fw = new FileWriter("c:/gestionContactos/datosexportados.csv")) {
                for (int i = 0; i < delegado.modeloTabla.getRowCount(); i++) {
                    for (int j = 0; j < delegado.modeloTabla.getColumnCount(); j++) {
                        fw.write(delegado.modeloTabla.getValueAt(i, j).toString() + ";");
                    }
                    fw.write("\n");
                }
                JOptionPane.showMessageDialog(delegado, "Exportación realizada con éxito");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(delegado, "Error al exportar contactos");
            }
        }*/

        if (e.getSource() == delegado.btn_buscar) {
            filtrar(delegado.txt_buscar.getText());
        }
    }
}
