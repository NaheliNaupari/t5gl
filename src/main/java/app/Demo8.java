package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo8 {
        //Objetivo: listado de todos los Usuarios segun el tipo de Uusuario(filtro)
	
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
	
		
		
		//select * from tb_usuarios where idtipo = ?
		
				String jpql = "select u from Usuario u where u.usr_usu = xusr and xcla";
				List<Usuario> lstUsuarios =
						em.createQuery(jpql, Usuario.class).
						setParameter("xusr", "admin@ciberfarma.com").
						setParameter("xcla", "super").
						getResultList();
				
				
				//imprimir el listado 
				for (Usuario u : lstUsuarios) {
					System.out.println("Código...: " + u.getCod_usua());
					System.out.println("Nombre...: " + u.getNom_usua() + " " + u.getApe_usua());
					System.out.println("Tipo...: " + u.getIdtipo() + "  " + u.getObjTipo().getDescripcion());
					System.out.println("------------------");
				}
				
				em.close();
				
				
			}
		}
