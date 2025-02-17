package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;

import model.Email;
import view.EmailMenuWindow;
import view.StartMenuView;

public class ListenerEmail implements MouseListener {

	private EmailMenuWindow vista;
	private JPanel panel;
	private Email email;
	private FTPClient client;
	private StartMenuView vStarMenu;

	public ListenerEmail(JPanel panel, Email email) {
		this.panel = panel;
		this.email = email;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {

		}
	}

	/**
	 * Seleccion de carpetas
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Color color = new Color(211, 238, 240);
		panel.setBackground(color);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!email.getIsRead()) {
			panel.setBackground(vista.WHITE);
		} else {
			panel.setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
