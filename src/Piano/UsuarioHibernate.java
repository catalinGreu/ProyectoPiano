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

		em.persist( u );		
	}

	public void delete ( Usuario u ){

		em.remove( u );
	}

	public Usuario findByPrimaryKey( Usuario u )  {

		TypedQuery<Usuario> q = em.createQuery("from Usuario where id_user=:id", Usuario.class);
		q.setParameter("id", u.getIDUser() );
		List<Usuario> resultList = q.getResultList();
		return resultList.get(0);
	}
	

	public List findAll( Usuario u ){
		TypedQuery<Usuario> q = em.createQuery("from Usuario", Usuario.class );
		List<Usuario> resultList = q.getResultList();
		return resultList;
	}

	public static void main(String[] args) {
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracle");
			EntityManager em = emf.createEntityManager();
			
			Usuario u = new Usuario("Catalin", "Greu", "cata_greu94", "12345");
			Melodia m = new Melodia("fur elise", u );
			Pulsacion p = new Pulsacion("1.DO3.wav", 45.0);
			Pulsacion d = new Pulsacion("3.Reb", 89.0 );
			
			u.addMelodia( m );
			m.addPulsacion( p );
			m.addPulsacion( d );
			EntityTransaction tx = em.getTransaction();
			
			tx.begin();
			em.persist( u );
			em.persist( m );
			
			em.persist( p ); //añado pulsaciones
			em.persist( d );
			
			tx.commit();
	}

}
