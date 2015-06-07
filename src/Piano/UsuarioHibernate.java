package Piano;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class UsuarioHibernate {

	private EntityManager em;

	public UsuarioHibernate ( EntityManager entity  ){

		this.em = entity;
	}

	public void update ( Usuario u ){

		em.persist( u );		
	}

	public void insert ( Usuario u ){

		em.persist(u);

	}

	public void delete ( Usuario u ){

		em.remove( u );
	}

	public Usuario findByPrimaryKey( Usuario u )  {

		TypedQuery<Usuario> q = em.createQuery("from Usuario where id_user=:id", Usuario.class);
		q.setParameter("id", u.getIDUser() );
		List<Usuario> resultList = q.getResultList();

		if ( resultList.isEmpty() ) {

			System.out.println("El usuario no existe");
			return null;
		}

		return resultList.get(0);
	}


	public List findAll( Usuario u ){
		TypedQuery<Usuario> q = em.createQuery("from Usuario", Usuario.class );
		List<Usuario> resultList = q.getResultList();
		return resultList;
	}


}
