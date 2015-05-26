package Piano;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Reproductor {
	
	private List<Pulsacion> listaQueDeboTocar;
	protected static final int MILISEGUNDOSDEMUESTREO = 10;
	private Clip clip;
	public Reproductor(){}
	
	public void tocaMelodia() {
		
		int tickPorElQueVoy = 0;
		while( listaQueDeboTocar.size() > 0 ){
			Pulsacion p = listaQueDeboTocar.get( 0 );
			System.out.println( p );
			if( p.getTick() <= tickPorElQueVoy ){
				playSound( p.getNombreNota() );
				listaQueDeboTocar.remove( 0 );
			}
			else{
				System.out.println("En este tick no toco");
				System.out.println();
			}
			tickPorElQueVoy += 1;
			
			try {
				Thread.sleep( MILISEGUNDOSDEMUESTREO );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void playSound( String nombreNota ) {

		
		URL url = getClass().getResource(nombreNota);

		try{

			clip = AudioSystem.getClip();				 

			clip.open( AudioSystem.getAudioInputStream( url ));

			clip.start();

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void setListaPulsaciones(List<Pulsacion> l ){
		
		this.listaQueDeboTocar = l;
		
	}

}
