package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.logica_ventana;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.ResourceBundle;


public class ventanaContactos extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTextField txtNombre;
    private JTextField txtContacto;
    private JTextField txtEmail;
    private JTable table;
 // Internacionalización
    private ResourceBundle mensajes;

    // Labels
    private JLabel lblBuscar;
    private JLabel lblNombre;
    private JLabel lblContacto;
    private JLabel lblEmail;
    private JLabel lblIdioma;

    // Botones
    private JButton btnBuscar;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;

    private JButton btnFavoritos;
    private JButton btnFamilia;
    private JButton btnAmigos;
    private JButton btnTrabajo;

    private JComboBox<String> cboIdioma;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ventanaContactos frame = new ventanaContactos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ventanaContactos() {

    	// Idioma inicial Español
        Locale locale = new Locale("es");

        mensajes = ResourceBundle.getBundle(
                "idiomas.mensajes",
                locale);

        setTitle("GESTIÓN DE CONTACTOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(102, 192, 209));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        // PANEL BUSQUEDA
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(new Color(51, 204, 153));
        panelBusqueda.setBounds(20, 11, 640, 50);
        panelBusqueda.setLayout(null);
        contentPane.add(panelBusqueda);

        lblBuscar = new JLabel();
        lblBuscar.setBounds(10, 15, 70, 20);
        panelBusqueda.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(80, 10, 200, 25);
        panelBusqueda.add(txtBuscar);
        
        //BOTON BUSCAR
        btnBuscar = new JButton();
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBuscar.setBounds(290, 10, 50, 25);
        panelBusqueda.add(btnBuscar);
        ImageIcon iconoOriginal4 = new ImageIcon(
                getClass().getResource("/Resources/buscar.png"));
        Image imagenRedimensionada4 = iconoOriginal4.getImage()
                .getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnBuscar.setIcon(new ImageIcon(imagenRedimensionada4));

        lblIdioma = new JLabel();
        lblIdioma.setBounds(450, 15, 50, 20);
        panelBusqueda.add(lblIdioma);

        cboIdioma = new JComboBox<>();
        cboIdioma.addItem("ES");
        cboIdioma.addItem("EN");
        cboIdioma.addItem("FR");
        cboIdioma.addActionListener(e -> {

            String idioma =
                    cboIdioma.getSelectedItem().toString();

            cambiarIdioma(idioma);
        });
        cboIdioma.setBounds(510, 10, 60, 25);
        panelBusqueda.add(cboIdioma);

        // PANEL DATOS
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBackground(new Color(220, 230, 140));
        panelDatos.setBorder(new LineBorder(Color.GRAY));
        panelDatos.setBounds(20, 90, 560, 200);
        contentPane.add(panelDatos);

        lblNombre = new JLabel();
        lblNombre.setBounds(20, 25, 80, 20);
        panelDatos.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 260, 25);
        panelDatos.add(txtNombre);

        lblContacto = new JLabel();
        lblContacto.setBounds(20, 70, 80, 20);
        panelDatos.add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setBounds(120, 65, 260, 25);
        panelDatos.add(txtContacto);

        lblEmail = new JLabel();
        lblEmail.setBounds(30, 112, 80, 20);
        panelDatos.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 110, 260, 25);
        panelDatos.add(txtEmail);

        // BOTONES CRUD
        
        // BOTON AGREGAR
        btnAgregar = new JButton();
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnAgregar.setBounds(20, 152, 80, 28);
        panelDatos.add(btnAgregar);
        ImageIcon iconoOriginal = new ImageIcon(
                getClass().getResource("/Resources/addcontacts_83750.png"));
        Image imagenRedimensionada = iconoOriginal.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnAgregar.setIcon(new ImageIcon(imagenRedimensionada));
        
        // BOTON EDITAR
        btnEditar = new JButton();
        btnEditar.setBounds(130, 150, 100, 30);
        panelDatos.add(btnEditar);
        ImageIcon iconoOriginal2 = new ImageIcon(
                getClass().getResource("/Resources/texteditor_pencil_detext_836.png"));
        Image imagenRedimensionada2 = iconoOriginal2.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnEditar.setIcon(new ImageIcon(imagenRedimensionada2));
        
     // BOTON ELIMINAR
        btnEliminar = new JButton();
        btnEliminar.setBounds(260, 150, 100, 30);
        panelDatos.add(btnEliminar);
        ImageIcon iconoOriginal3 = new ImageIcon(
                getClass().getResource("/Resources/file-delete_114438.png"));
        Image imagenRedimensionada3 = iconoOriginal3.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnEliminar.setIcon(new ImageIcon(imagenRedimensionada3));

     // PESTAÑAS LATERALES
        
        //PESTAÑA FAVORITOS
        btnFavoritos = new JButton();
        btnFavoritos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnFavoritos.setBounds(600, 260, 50, 50);
        contentPane.add(btnFavoritos);
        ImageIcon iconoOriginal5 = new ImageIcon(
                getClass().getResource("/Resources/portafolio-favorito.png"));
        Image imagenRedimensionada5 = iconoOriginal5.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnFavoritos.setIcon(new ImageIcon(imagenRedimensionada5));
        
        //PESTAÑA FAMILIA
        btnFamilia = new JButton();
        btnFamilia.setBounds(600, 200, 50, 50);
        contentPane.add(btnFamilia);
        ImageIcon iconoOriginal7 = new ImageIcon(
                getClass().getResource("/Resources/casa.png"));
        Image imagenRedimensionada7 = iconoOriginal7.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnFamilia.setIcon(new ImageIcon(imagenRedimensionada7));
        
        //PESTAÑA AMIGOS
        btnAmigos = new JButton();
        btnAmigos.setBounds(600, 140, 50, 50);
        contentPane.add(btnAmigos);
        ImageIcon iconoOriginal8 = new ImageIcon(
                getClass().getResource("/Resources/hacer-amigos.png"));
        Image imagenRedimensionada8 = iconoOriginal8.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnAmigos.setIcon(new ImageIcon(imagenRedimensionada8));
        
        
      //PESTAÑA TRABAJO
        btnTrabajo = new JButton();
       btnTrabajo.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       	}
       });
        btnTrabajo.setBounds(600, 80, 50, 50);
        contentPane.add(btnTrabajo);
        ImageIcon iconoOriginal9 = new ImageIcon(
                getClass().getResource("/Resources/trabajar.png"));
        Image imagenRedimensionada9 = iconoOriginal9.getImage()
                .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnTrabajo.setIcon(new ImageIcon(imagenRedimensionada9));

        // TABLA

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 320, 640, 160);
        contentPane.add(scrollPane);

        table = new JTable();

        table.setModel(new DefaultTableModel(
            new Object[][] {
                {"Value 1", "Value 2", "Value 3"},
                {"Value 4", "Value 5", "Value 6"},
            },
            new String[] {
                "NOMBRES", "CONTACTO", "EMAIL"
            }
        ));

        scrollPane.setViewportView(table);
        actualizarTextos();
    }
    private void cambiarIdioma(String idioma) {

        Locale locale;

        switch (idioma) {

        case "EN":
            locale = new Locale("en");
            break;

        case "FR":
            locale = new Locale("fr");
            break;

        default:
            locale = new Locale("es");
        }

        mensajes = ResourceBundle.getBundle(
                "idiomas.mensajes",
                locale);

        actualizarTextos();
    }
    private void actualizarTextos() {

        // Título ventana
        setTitle(
                mensajes.getString("titulo"));

        // Etiquetas
        lblBuscar.setText(
                mensajes.getString("buscar"));

        lblNombre.setText(
                mensajes.getString("nombres"));

        lblContacto.setText(
                mensajes.getString("contacto"));

        lblEmail.setText(
                mensajes.getString("email"));

        lblIdioma.setText(
                mensajes.getString("idioma"));

        // Tooltips
        btnBuscar.setToolTipText(
                mensajes.getString("ttBuscar"));

        btnAgregar.setToolTipText(
                mensajes.getString("ttAgregar"));

        btnEditar.setToolTipText(
                mensajes.getString("ttEditar"));

        btnEliminar.setToolTipText(
                mensajes.getString("ttEliminar"));

        btnFavoritos.setToolTipText(
                mensajes.getString("ttFavoritos"));

        btnFamilia.setToolTipText(
                mensajes.getString("ttFamilia"));

        btnAmigos.setToolTipText(
                mensajes.getString("ttAmigos"));

        btnTrabajo.setToolTipText(
                mensajes.getString("ttTrabajo"));

        // Cabeceras tabla
        table.getColumnModel().getColumn(0)
                .setHeaderValue(
                mensajes.getString("nombres"));

        table.getColumnModel().getColumn(1)
                .setHeaderValue(
                mensajes.getString("contacto"));

        table.getColumnModel().getColumn(2)
                .setHeaderValue(
                mensajes.getString("email"));

        table.getTableHeader().repaint();
    }
}