package Piano;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

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

	private void run( HeAcabadoListener l ) {
		int tickPorElQueVoy = 0;
		//		fuera:
		while( listaQueDeboTocar.size() > 0 ){

			if( meHanPedidoQuePare() ){
				System.out.println("ME SALGO PORQUE YA NO HAY QUE SEGUIR TOCANDO");
				//					break fuera;
				l.heAcabado();
				return;
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

			//Thread.sleep(clip.getMicrosecondLength()/1000);
		}
		catch(Exception e){

			e.printStackTrace();
		}


		//		URL url = getClass().getResource(nombreNota);		
		//
		//		try {
		//
		//			AudioInputStream soundIn = AudioSystem.getAudioInputStream(url);
		//			AudioFormat format = soundIn.getFormat();
		//			DataLine.Info info = new DataLine.Info(Clip.class, format);
		//			clip = (Clip) AudioSystem.getLine(info);
		//			clip.open(soundIn);
		//			clip.start();
		//			while( clip.isRunning() ){
		//
		//				Thread.yield();
		//			}
		//
		//		} catch (Exception e) {
		//
		//			e.printStackTrace();
		//		}

	}

	public void setListaPulsaciones( List<Pulsacion> l ){

		this.listaQueDeboTocar = l;

	}
	public void setPararMelodia( boolean parar ){
		System.out.println("***********PARA POR DIOSSSS**********************:" + parar );
		this.parar = parar;
	}


}

