package Piano;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.Timer;

@Entity

public class Pulsacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int id_pulsacion;	

	@ManyToOne
	@JoinColumn(name="id_melodia")
	private Melodia melodia;

	@Column String nota;

	@Column private double tick;

	public Pulsacion(){}

	public Pulsacion( String nota, double tick ){

		this.nota = nota;
		this.tick = tick;	

	}
	
	public int getIdPulsacion(){
		
		return this.id_pulsacion;
	}

	public String getNombreNota() {
		return this.nota;
	}

	public void setNota( String nota ) {
		this.nota = nota;
	}

	public Melodia getMelodia() {
		return this.melodia;
	}
	public void setMelodia( Melodia m ){
		this.melodia = m;
		
	}

	//	public void setId_cancion( int id_cancion ) {
	//		
	//		this.melodia.getId_melodia() = id_cancion;
	//	}

	public double getTick() {
		return this.tick;
	}

	public void setTick( double t ) {
		this.tick = t;
	}
	
	public void setIDMelodia(int idMelody ){
		
		this.melodia.setId_melodia(idMelody);
	}

	@Override
	public String toString() {
		return String.format( "Tecla: %s --> segundo: %f", nota, tick );
	}

	public static void main(String[] args) {
		Pulsacion p1 = new Pulsacion("1.DO3.wav", 12);
		Pulsacion p2 = new Pulsacion("14.Reb4.wav", 20);
		Pulsacion p3 = new Pulsacion("23.Sib4.wav", 40);

		List<Pulsacion> listaPulsadas = new ArrayList<Pulsacion>();

		listaPulsadas.add(p1);
		listaPulsadas.add(p2);
		listaPulsadas.add(p3);

		for (Pulsacion nota : listaPulsadas) {

			System.out.println(nota);
		}

	}	


}
