package Piano;

import java.awt.image.RescaleOp;
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
	public Melodia findByPrimaryKey( Melodia m ) {
		TypedQuery<Melodia> q = em.createQuery("from Melodia where id_melodia=:id", Melodia.class);
		q.setParameter("id", m.getId_melodia() );
		List<Melodia> resultList = q.getResultList();
		return resultList.get(0);

	}

	public List<Pulsacion> pulsacionesDeMelodia ( Melodia m ){

		List<Pulsacion> resultList = null;
		if ( m == null ) {

			System.out.println("La melodia es null");
		}

		else{
			TypedQuery<Pulsacion> q = em.createQuery("from Pulsacion where id_melodia=:id" , Pulsacion.class );
			q.setParameter("id", m.getId_melodia() );
			resultList = q.getResultList();	
			return resultList;
		}

		return resultList;
	}

	public List<Melodia> buscaMelodiasPorUsuario (Usuario u){

		TypedQuery<Melodia> q = em.createQuery("from Melodia where id_user=:id", Melodia.class );
		q.setParameter("id", u.getIDUser() );
		List<Melodia> resultList = q.getResultList();
		return resultList;
	}

	//	public List findAll( Melodia m ){
	//		TypedQuery<Melodia> q = em.createQuery("from Melodia", Melodia.class );
	//		List<Melodia> resultList = q.getResultList();
	//		return resultList;
	//	}



}
