package es.urjc.code.dad;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {

	public static void main( String[] args ) {
		
		ServerSocket serverSocket = null;
		ServerSocket serverSocket2 = null;
		Socket socket = null;
		Socket socket2 = null;
		
		while(true){
			try {
			
				serverSocket = new ServerSocket (5555);
				serverSocket2 = new ServerSocket (4444);
			
				socket = serverSocket.accept();
				socket2 = serverSocket2.accept();
				BufferedReader leerJugadorInfo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String fichaNombreJugador = leerJugadorInfo.readLine();
				String fichaEquipoJugador = leerJugadorInfo.readLine();
				String fichaPosicionJugador = leerJugadorInfo.readLine();
				String fichaEdadJugador = leerJugadorInfo.readLine();
				String fichaNacionalidadJugador = leerJugadorInfo.readLine();
				String fichaValorMercadoJugador = leerJugadorInfo.readLine();
				
				leerJugadorInfo.close();
				File fichaJugador = new File("infoJugador.txt");
				FileWriter fwJugador = new FileWriter(fichaJugador);
				PrintWriter pwJugador = null;
				pwJugador = new PrintWriter(fwJugador);
				pwJugador.println(fichaNombreJugador);
				pwJugador.println(fichaEquipoJugador);
				pwJugador.println(fichaPosicionJugador);
				pwJugador.println(fichaEdadJugador);
				pwJugador.println(fichaNacionalidadJugador);
				pwJugador.println(fichaValorMercadoJugador);
				fwJugador.close();
			
		          byte [] mybytearray  = new byte [(int)fichaJugador.length()];
		          FileInputStream fis = new FileInputStream(fichaJugador);
		          BufferedInputStream bis = new BufferedInputStream(fis);
		          bis.read(mybytearray,0,mybytearray.length);
		          OutputStream os = socket2.getOutputStream();
		          os.write(mybytearray,0,mybytearray.length);
			    
		          System.out.println("Se envia fichero");
		          
			    fis.close();
			    bis.close();
			    os.close();
				socket.close();
				serverSocket.close();    
				serverSocket2.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}finally{
			}
		}
	}
	
}