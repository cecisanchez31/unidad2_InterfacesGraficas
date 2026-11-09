package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Definición de la clase pública "personaDAO"
public class personaDAO {
    
    // Declaración de atributos privados de la clase "personaDAO"
    private File archivo; // Archivo donde se almacenarán los datos de los contactos
    private persona persona; // Objeto "persona" que se gestionará

    // Constructor público de la clase "personaDAO" que recibe un objeto "persona" como parámetro
    public personaDAO(persona persona) {
        this.persona = persona; // Asigna el objeto "persona" recibido al atributo de la clase
        archivo = new File("c:/gestionContactos/datosContactos.csv"); // Establece la ruta donde se alojará el archivo
        // Llama al método para preparar el archivo
        prepararArchivo();
    }
    
    // Método privado para gestionar el archivo utilizando la clase File
    private void prepararArchivo() {
        try {
            File directorio = archivo.getParentFile();
            if (!directorio.exists()) directorio.mkdir();
            // Verifica si el directorio existe
            if (!archivo.exists()) {
                archivo.createNewFile();// Si el directorio no existe, se crea

                try (FileWriter fw = new FileWriter(archivo, true)) {
                    fw.write("NOMBRE;TELEFONO;EMAIL;CATEGORIA;FAVORITO\n");
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public boolean escribirArchivo() {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(persona.datosContacto() + "\n");
            return true;
        } catch (IOException e) { return false; }
    }
    
    // Método público para leer los datos del archivo
    public List<persona> leerArchivo() throws IOException {
        List<persona> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) { primera = false; continue; }
                String[] datos = linea.split(";");
                if (datos.length == 5) {
                    personas.add(new persona(
                        datos[0], datos[1], datos[2], datos[3],
                        Boolean.parseBoolean(datos[4])
                    ));
                }
            }
        }
        return personas;
    }

    private void reescribirArchivo(List<persona> personas) throws IOException {
        try (FileWriter fw = new FileWriter(archivo, false)) {
            fw.write("NOMBRE;TELEFONO;EMAIL;CATEGORIA;FAVORITO\n");
            for (persona p : personas) fw.write(p.datosContacto() + "\n");
        }
    }

    public void eliminarArchivo(persona contactoEliminar) throws IOException {
        List<persona> personas = leerArchivo();
        personas.removeIf(p -> p.datosContacto().equals(contactoEliminar.datosContacto()));
        reescribirArchivo(personas);
    }

    public void modificarArchivo(int index, persona contactoNuevo) throws IOException {
        List<persona> personas = leerArchivo();
        if (index >= 0 && index < personas.size()) {
            personas.set(index, contactoNuevo);
            reescribirArchivo(personas);
        }
    }
}
