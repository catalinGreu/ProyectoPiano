package Piano;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PulsacionHibernate {
	
	private EntityManager em;
	
	public PulsacionHibernate ( EntityManager entity  ){

		this.em = entity;
	}
	
	public void update ( Pulsacion p ){

		em.persist( p );		
	}

	public void insert ( Pulsacion p ){

		em.persist( p );		
	}

	public void delete ( Pulsacion p ){

		em.remove( p );
	}
	
	private Pulsacion findByPrimaryKey( Pulsacion p ) {
		TypedQuery<Pulsacion> q = em.createQuery("from Pulsacion where id_pulsacion=:id", Pulsacion.class);
		q.setParameter("id", p.getIdPulsacion() );
		List<Pulsacion> resultList = q.getResultList();
		return resultList.get(0);

	}
	
	private List<Pulsacion> getPulsacionesDeMelodia ( Melodia m ){
		TypedQuery<Pulsacion> q = em.createQuery("select nota from Pulsacion where id_melodia=:id", Pulsacion.class );
		q.setParameter("id", m.getId_melodia() );
		List<Pulsacion> resultList = q.getResultList();
		
		return resultList;
		
	}
	
	public List findAll( Pulsacion p ){
		TypedQuery<Melodia> q = em.createQuery("from melodia", Melodia.class );
		List<Melodia> resultList = q.getResultList();
		return resultList;
	}

}
