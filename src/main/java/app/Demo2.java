package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo2 {
        //registro de un nuevo Usuario
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		//2. crear un manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		// proceso 
		Usuario u = new Usuario(123, "Naheli","Naupari", "nahe", "familia", "2003/03/22", 1, 1);
		//insert into
		//OjO !! si el proceso es registrar/act/elimi -> Transaccion
		try {
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
			System.out.println("Actualizacion OK!!!");
		} catch (Exception e) {
			System.out.println("Error:" + e.getCause().getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		em.close();
		
		
	}
}
