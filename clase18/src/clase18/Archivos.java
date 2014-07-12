package clase18;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Archivos extends JFrame implements ActionListener{

	JTextArea salida;
	JTextField entrada;
	
	
	public Archivos(){
		entrada = new JTextField("Escrie el archivo/directorio");
		entrada.addActionListener(this);
		
		salida = new JTextArea();
		ScrollPane scroll = new ScrollPane();
		scroll.add(salida);
		
		
		Container contenedor = getContentPane();
		contenedor.add(entrada,BorderLayout.NORTH);
		contenedor.add(scroll,BorderLayout.CENTER);
		setSize(400,400);
		setVisible(true);
		
		
		
	}
	
	
	public static void main(String[] arg){
		Archivos archivo = new Archivos();
		archivo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File archivo = new File(e.getActionCommand());
		
		if(archivo.exists()){
		
			salida.setText(archivo.getName()+"existe\n");
			
			if(archivo.isFile()){
					salida.append("Es un archivo\n");
				}else{
					salida.append("NO es un archivo\n");
				}
			
			if(archivo.isDirectory()){
					salida.append("Es un directorio\n");
				}else{
					salida.append("No es un directorio\n");
				}
			
			if(archivo.isFile()){
				try {
					BufferedReader leer = new BufferedReader(new FileReader(archivo));
					StringBuffer buffer = new StringBuffer();
					String texto;
					
					salida.append("\n\n\n");
					
					while((texto = leer.readLine())!=null){
						buffer.append(texto+"\n");
					}
					
					salida.append(buffer.toString());
					
					
					
				} catch (IOException ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "ERROR EN ARCHIVO","Esto es error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
	
		}//if archivo exist1
		
		
		if (archivo.isDirectory()){
			String directorio[] = archivo.list();
			salida.append("\nArchivos del directorio:\n");
			for (int i=0; i<directorio.length;i++){
				salida.append(directorio[i]+"\n");
			}
			
		}
		
	}
	
}
