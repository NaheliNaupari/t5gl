package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
        //registro de un nuevo Usuario
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		//2. crear un manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		// proceso 
		Usuario u = new Usuario();
		u.setCod_usua(0);
		
		//insert into
		//OjO !! si el proceso es registrar/act/elimi -> Transaccion
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			System.out.println("Registro OK!!!");
		} catch (Exception e) {
			System.out.println("Error:" + e.getCause().getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		em.close();
		
		
	}
}
