package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import model.ArchivoFtp;

public class ListenerModificarNombre implements KeyListener {

	private ArchivoFtp archivo;
	private JTextField nombre;
	FTPClient client;
	private String user;
	DataOutputStream outputStream;
	public ListenerModificarNombre(ArchivoFtp archivo, JTextField nombre, FTPClient client, String user, DataOutputStream outputStream) {
		this.archivo = archivo;
		this.nombre = nombre;
		this.client = client;
		this.user = user;
		this.outputStream = outputStream;
	}

	private void comprobarNombre() {
		String text = nombre.getText();
		String nombreanterior = archivo.getNombre();
		if (text.trim().length() > 0) {
			archivo.setNombre(text);
			cambiarnombre(text, nombreanterior);

		} else {
			nombre.setText(archivo.getNombre());
		}
		nombre.setEditable(false);
	}

	private void cambiarnombre(String newName, String name) {
		FTPFile[] fileList;
		try {
			fileList = client.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].getName().equals(name)) {
					client.rename(fileList[i].getName(), newName);
				}
			}
			System.out.println("RENAME SUCCESFULL");
			outputStream.writeUTF("7");
			outputStream.writeUTF(name);
			outputStream.writeUTF(newName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			comprobarNombre();
		} else {
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
