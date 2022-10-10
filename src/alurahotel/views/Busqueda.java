package alurahotel.views;

import alurahotel.controller.HuespedesController;
import alurahotel.controller.ReservasController;
import alurahotel.model.Huespedes;
import alurahotel.model.Reservas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloR;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private ReservasController reservasController;
	private HuespedesController huespedesController;
	private ArrayList<Reservas> reservas;
	private ArrayList<Huespedes> huespedes;
	private int tablaElegida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/alurahotel/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		panel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane pane = (JTabbedPane) e.getSource();
				tablaElegida = pane.getSelectedIndex();
			}
		});
		contentPane.add(panel);

		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/alurahotel/imagenes/reservado.png")), tbReservas, null);
		modeloR = (DefaultTableModel) tbReservas.getModel();
		modeloR.addColumn("Numero de Reserva");
		modeloR.addColumn("Fecha Check In");
		modeloR.addColumn("Fecha Check Out");
		modeloR.addColumn("Valor");
		modeloR.addColumn("Forma de Pago");
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/alurahotel/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/alurahotel/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);


        this.reservasController = new ReservasController();
        obtenerReservas();
		this.huespedesController = new HuespedesController();
		obtenerHuespedes();
	}

	private void listarReservas(ArrayList<Reservas> reservas) {
		modeloR.getDataVector().clear();
		ArrayList<Reservas> reservasTable = reservas;
		reservasTable.forEach(reserva -> modeloR.addRow(new Object[] {
				reserva.getId(), reserva.getFecha_entrada(), reserva.getFecha_salida(),
				reserva.getValor(), reserva.getForma_pago()
		}));
		tbReservas.updateUI();
	}

	private void listarHuespedes(ArrayList<Huespedes> huespedes) {
		modeloH.getDataVector().clear();
		ArrayList<Huespedes> huespesTable = huespedes;
		huespesTable.forEach(huesped -> modeloH.addRow(new Object[] {
				huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFecha_nacimiento(),
				huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReserva_id()
		}));
		tbHuespedes.updateUI();
	}

	private void obtenerReservas() {
		this.reservas = this.reservasController.listar();
		listarReservas(this.reservas);
	}

	private void obtenerHuespedes() {
		this.huespedes = this.huespedesController.listar();
		listarHuespedes(this.huespedes);
	}

	private void modificarReservas() {
		Integer filaElegida = tbReservas.getSelectedRow();

		Integer id = (Integer) modeloR.getValueAt(filaElegida, 0);
		Date fecha_entrada = Date.valueOf(modeloR.getValueAt(filaElegida, 1).toString());
		Date fecha_salida = Date.valueOf(modeloR.getValueAt(filaElegida, 2).toString());
		String valor = (String) modeloR.getValueAt(filaElegida, 3);
		String forma_pago = (String) modeloR.getValueAt(filaElegida, 4);

		var modificado = this.reservasController.modificar(id,fecha_entrada,fecha_salida,valor,forma_pago);
		obtenerReservas();
		JOptionPane.showMessageDialog(this, String.format("%d Reserva modificada con exito!", modificado));
	}

	private void modificarHuespedes() {
		Integer filaElegida = tbHuespedes.getSelectedRow();

		Integer id = (Integer) modeloH.getValueAt(filaElegida, 0);
		String nombre = (String) modeloH.getValueAt(filaElegida, 1);
		String apellido = (String) modeloH.getValueAt(filaElegida, 2);
		Date fecha_nacimiento = Date.valueOf(modeloH.getValueAt(filaElegida, 3).toString());
		String nacionalidad = (String) modeloH.getValueAt(filaElegida, 4);
		String telefono = (String) modeloH.getValueAt(filaElegida, 5);
		Integer reserva_id = (Integer) modeloH.getValueAt(filaElegida, 6);

		var modificado = this.huespedesController.modificar(id,nombre,apellido,fecha_nacimiento,
				nacionalidad,telefono,reserva_id);
		obtenerHuespedes();
		JOptionPane.showMessageDialog(this, String.format("%d Huesped modificado con exito!", modificado));
	}

	private void eliminarReservas() {
		Integer filaElegida = tbReservas.getSelectedRow();

		Integer id = (Integer) modeloR.getValueAt(filaElegida, 0);

		int eliminado = this.reservasController.eliminar(id);
		obtenerReservas();
		JOptionPane.showMessageDialog(this, String.format("%d Reserva eliminada con exito!", eliminado));
	}

	private void eliminarHuespedes() {
		Integer filaElegida = tbHuespedes.getSelectedRow();

		Integer id = (Integer) modeloH.getValueAt(filaElegida, 0);

		int eliminado = this.huespedesController.eliminar(id);
		obtenerHuespedes();
		JOptionPane.showMessageDialog(this, String.format("%d Reserva eliminada con exito!", eliminado));
	}

	private void buscarReservas() {
		String palabra = txtBuscar.getText();
		ArrayList<Reservas> resultado = new ArrayList<>();
		this.reservas.forEach(reservas -> {
			if (reservas.getId().toString().equals(palabra)){
				resultado.add(reservas);
			}
		});
		if (resultado.isEmpty()){
			JOptionPane.showMessageDialog(this, "No se encontro la reserva");
			listarReservas(this.reservas);
		} else {
			listarReservas(resultado);
		}
	}

	private void buscarHuespedes() {
		String palabra = txtBuscar.getText();
		ArrayList<Huespedes> resultado = new ArrayList<>();
		this.huespedes.forEach(huespedes -> {
			if (huespedes.getApellido().equals(palabra)){
				resultado.add(huespedes);
			}
		});
		if (resultado.isEmpty()){
			JOptionPane.showMessageDialog(this, "No se encontro el huesped");
			listarHuespedes(this.huespedes);
		} else {
			listarHuespedes(resultado);
		}
	}

	private void modificar() {
		if (tablaElegida == 0){
			modificarReservas();
		} else if (tablaElegida == 1) {
			modificarHuespedes();
		}
	}

	private void eliminar() {
		if (tablaElegida == 0) {
			eliminarReservas();
		} else if (tablaElegida == 1) {
			eliminarHuespedes();
		}
	}

	private void buscar() {
		if (tablaElegida == 0) {
			buscarReservas();
		} else if (tablaElegida == 1) {
			buscarHuespedes();
		}
	}

	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
