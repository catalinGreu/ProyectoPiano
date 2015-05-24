package Piano;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id 
	private String id_user;

	@Column 
	String nombre;

	@Column 
	String apellido;
	
	@Column 
	String password;

	@OneToMany(mappedBy="usuario")
	private List<Melodia> listaMelodias;

	public List<Melodia> getMelodias (){

		if ( listaMelodias == null) {

			listaMelodias = new ArrayList<Melodia>();
		}
		return listaMelodias;

	}

	public void addMelodia( Melodia m ){

		if ( !getMelodias().contains( m )) {
			getMelodias().add( m );			
		}

		if ( m.getUser() != this ) {
			m.setUser(this);
		}

	}

	public void removeMelodia( Melodia m ){

		getMelodias().remove(m);
		m.setUser( null );

	}
	public Usuario( String nom, String ape, String ID, String pass ){
		this.nombre = nom;
		this.apellido = ape;
		this.id_user = ID;
		this.password = pass;

	}

	public Usuario(){}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido( String apellido ) {
		this.apellido = apellido;
	}

	public String getIDUser() {
		return id_user;
	}

	public void setIDUser( String ID ) {
		this.id_user = ID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}
	
	//equals si ID y Password son iguales
	@Override
	public boolean equals( Object obj ) {
		Usuario u = (Usuario) obj;
		
		if ( u.getIDUser().equals( this.getIDUser() ) ) {
			
			if ( u.getPassword().equals( this.getPassword() )) {
				
				return true;
			}			
		}
		
		return false;
	
	}

	@Override
	public String toString() {

		return String.format("Usuario: %s, Apellido: %s, ID: %s" + this.nombre, this.apellido, this.id_user);
	}

}
