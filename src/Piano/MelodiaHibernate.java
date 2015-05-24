package Piano;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class MelodiaHibernate {
	
	private EntityManager em;
	
	public MelodiaHibernate ( EntityManager entity  ){

		this.em = entity;
	}
	
	public void update ( Melodia m ){

		em.persist( m );		
	}

	public void insert ( Melodia m ){

		em.persist( m );		
	}

	public void delete ( Melodia m ){

		em.remove( m );
	}
	
	//igual ya ni me hacen falta estas clases
	private Melodia findByPrimaryKey( Melodia m ) {
		TypedQuery<Melodia> q = em.createQuery("from Melodia where id_melodia=:id", Melodia.class);
		q.setParameter("id", m.getId_melodia() );
		List<Melodia> resultList = q.getResultList();
		return resultList.get(0);

	}
	
//	public List findAll( Melodia m ){
//		TypedQuery<Melodia> q = em.createQuery("from Melodia", Melodia.class );
//		List<Melodia> resultList = q.getResultList();
//		return resultList;
//	}
	
	

}
