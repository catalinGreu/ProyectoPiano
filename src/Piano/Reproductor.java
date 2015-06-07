package Piano;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Piano.Reproductor.HeAcabadoListener;

public class Reproductor {

	private List<Pulsacion> listaQueDeboTocar;
	protected static final int MILISEGUNDOSDEMUESTREO = 10;
	private Clip clip;

	private boolean parar;

	public Reproductor(){}

	public interface HeAcabadoListener{
		public void heAcabado();
	}

	public void tocaMelodia( final HeAcabadoListener l ) {

		Thread t = new Thread( new Runnable(){
			@Override
			public void run() {
				Reproductor.this.run(l);
			}
		});
		t.start();
	}

	private void run(HeAcabadoListener l) {
		int tickPorElQueVoy = 0;
		fuera:
		while( listaQueDeboTocar.size() > 0 ){

			if( meHanPedidoQuePare() ){
				System.out.println("ME SALGO PORQUE YA NO HAY QUE SEGUIR TOCANDO");
				break fuera;
			}
			Pulsacion p = listaQueDeboTocar.get( 0 );
			
			if( p.getTick() <= tickPorElQueVoy ){
				playSound( p.getNombreNota() );
				listaQueDeboTocar.remove( 0 );
			}
			else{
				System.out.println();
			}
			tickPorElQueVoy += 1;
			try {
				Thread.sleep( MILISEGUNDOSDEMUESTREO );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Ya he acabado");

		if ( l == null ) {
			return;
		}
		else{
			l.heAcabado();
		}
	}

	public boolean meHanPedidoQuePare() {

		return this.parar;		
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

	public void setListaPulsaciones( List<Pulsacion> l ){

		this.listaQueDeboTocar = l;

	}
	public void setPararMelodia( boolean parar ){
		System.out.println("***********PARA POR DIOSSSS**********************:" + parar );
		this.parar = parar;
	}


}

