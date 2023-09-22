package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Producto;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class FrmLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JButton btnIngresar;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setTitle("LOGIN CIBERTEC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("USUARIO : ");
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 12));
		lblUsuario.setBounds(55, 48, 81, 13);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(146, 45, 161, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblClave = new JLabel("CLAVE : ");
		lblClave.setFont(new Font("Verdana", Font.BOLD, 12));
		lblClave.setBounds(55, 98, 81, 13);
		contentPane.add(lblClave);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(340, 59, 132, 39);
		contentPane.add(btnIngresar);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(146, 95, 161, 19);
		contentPane.add(txtClave);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		//Metodo
		   ingreso();
	}
	void ingreso() {
		//1. Obtener conexion - llamando a persistence
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		//2. Crear un manejador de entidades 
		EntityManager em = fabrica.createEntityManager();
			
		
		//Proceso
		String usuar = txtUsuario.getText();
		String clav = txtClave.getText();
		
		String jpql4 =  "select u from Usuario u where u.usr_usua = :xusu and u.cla_usua = :xclav "; 
		
		List<Usuario> lstUsuarios = em.createQuery(jpql4, Usuario.class).setParameter("xusu",usuar).setParameter("xclav",clav).getResultList();
		
		
		for(Usuario u : lstUsuarios) {
			System.out.println("Codigo...:" + u.getCod_usua());
			System.out.println("Nombre...:" + u.getNom_usua() + "" + u.getApe_usua());
			System.out.println("Tipo...:" + u.getIdtipo() + " " +  u.getObjTipo().getDescripcion());
			System.out.println("usuario...:" + u.getUsr_usua());
			System.out.println("Clave...:" + u.getCla_usua());
			System.out.println("-----------------------------------");
			
		}
		
		
		//Prueba Usuario : admin@ciberfarma.com   clave: super
		
		// Verificar si se encontró algún usuario
		
		if (lstUsuarios.isEmpty()) {                                              //--El metodo isEmpity() : Se usa para comprobar si la lista esta vacia
		    // Si la lista está vacía, el usuario no fue encontrado en la base de datos
		    avisoer("Usuario o contraseña incorrecta");
		} else {
		    // Si se encontró al menos un usuario, se permite el acceso
		    aviso("WELCOME" + " " + usuar);

		    FrmManteProd mantenimiento = new FrmManteProd();
		    mantenimiento.setVisible(true);
		    this.dispose();
		}
	
	         em.close();  //Cerramos
				
					
	    }
		
	    //Para los avisos
			void aviso (String msg) {
				JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.YES_NO_CANCEL_OPTION);   //Para crear un aviso 
			}
			void avisoer (String msg) {
				JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);   //Para crear un aviso 
		}
}
