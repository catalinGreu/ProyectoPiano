package Piano;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity

public class Melodia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OnDelete(action =  OnDeleteAction.CASCADE)
	private int id_melodia;	
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private Usuario usuario;
	
	@Column
	private String nombre_Melodia;
	
	@OneToMany(mappedBy="melodia")
	List<Pulsacion> listaPulsaciones;
	
	public List<Pulsacion> getPulsaciones (){

		if ( listaPulsaciones == null) {

			listaPulsaciones = new ArrayList<Pulsacion>();
		}
		return listaPulsaciones;

	}	
	
	public Melodia (){}
	
	public Melodia ( String nomMelody, Usuario user ){
		
		setNombreMelodia( nomMelody );
		setUser( user );
//		setId_melodia( idMelody );		
	}
	
	public void addPulsacion( Pulsacion p ){
		
		if ( !getPulsaciones().contains( p )) {
				getPulsaciones().add( p );
		}
		
		if ( p.getMelodia() != this ) {
			p.setMelodia( this );
			
		}
		getPulsaciones().add( p );
				
	}
	
	public int getId_melodia() {
		return id_melodia;
	}
	
	public void setId_melodia( int id_melodia ) {
		this.id_melodia = id_melodia;
	}
	
	public Usuario getUser() {
		return this.usuario;
	}
	
	public void setUser( Usuario idUser ) {
		
		this.usuario = idUser;
		
	}	

	public String getNombreMelodia() {
		return nombre_Melodia;
	}

	public void setNombreMelodia(String nombre) {
		this.nombre_Melodia = nombre;
	}
	
	@Override
	public String toString() {
		
		return String.format("Id: %s - Nombre Melodia: \"%s\" ", this.id_melodia, this.nombre_Melodia );
	}

}
