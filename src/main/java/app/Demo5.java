package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo5 {
        //Objetivo: listado de todos los Usuario
	
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
	
		
		
		//select * from tb_usuarios --> Lista
		
		String jpql = "select u from Usuario u";
		List<Usuario> lstUsuario = em.createQuery(jpql, Usuario.class).getResultList();
		
		
		
		//imprimir el listado 
		for (Usuario u : lstUsuario) {
			System.out.println("Código...: " + u.getCod_usua());
			System.out.println("Nombre...: " + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo...: " + u.getIdtipo());
			System.out.println("------------------");
		}
		
		em.close();
		
		
	}
}
