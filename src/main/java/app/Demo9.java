package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import model.Usuario;

public class Demo9 {
        //Objetivo: listado de todos los Usuarios segun el tipo de Uusuario(filtro)
	
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		String usuario = JOptionPane.showInputDialog("Ingrese usuario: ");
		String clave = JOptionPane.showInputDialog("Ingrese la clave: ");
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
	
	
		
//select * from tb_usuarios where idtipo = ?

		String jpql = "select u from Usuario u where u.usr_usu = xusr and xcla";
		try {
		Usuario u =
				em.createQuery(jpql, Usuario.class).
				setParameter("xusr", "admin@ciberfarma.com").
				setParameter("xcla", "super").
				getSingleResult(); 
		
		
		//imprimir el listado 
		FrmManteProd v = new FrmManteProd();
		v.setVisible(true);
			//dispose();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuario o clave incorrecto");
		}
		
			
		
		em.close();
		
		
	}
}
