package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Demo7 {
        //Objetivo: listado de todos los Productos
	
	public static void main(String[] args) {
		//1. obtener conexion -> llamar al persistence-unit
		EntityManagerFactory fabrica =
				Persistence.createEntityManagerFactory("jpa_sesion01");
		EntityManager em = fabrica.createEntityManager();
	
		
		
		//select * from tb_usuarios --> Lista
		
		String jpql = "select p from Producto p";
		List<Producto> lstProducto = em.createQuery(jpql, Producto.class).getResultList();
		
		
		
		//imprimir el listado 
		for (Producto p : lstProducto) {
			System.out.println("Id...: " + p.getId_prod());
			System.out.println("Descripcion...: " + p.getDes_prod() + " " + p.getStk_prod());
			System.out.println("Categoria...: " + p.getIdcategoria() + "  " + p.getObjCategoria().getDescripcion());
			System.out.println("Proveedor...: " + p.getIdproveedor() + "  " + p.getObjProveedor().getNombre_rs());
			System.out.println("------------------");
		}
		
		em.close();
		
		
	}
}
