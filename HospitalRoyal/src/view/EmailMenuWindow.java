package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

import controller.ListenerClose;
import controller.ListenerEmail;
import controller.ListenerSearch;
import controller.ListenerUpdate;
import model.Email;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Scrollbar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class EmailMenuWindow {

	public static final Color WHITE = new Color(255, 255, 255);
	private Color headerColor;
	private JFrame frame;
	private JPanel emailBox;
	private JTextField txtSearch;
	private JButton btnAdd, btnRecharge, btnClose, btnSearch;
	private SMTPClient client;
	private StartMenuView vStartMenu;
	String user;

	public EmailMenuWindow(String user, StartMenuView vStartMenu) {
		this.user = user;
		client = new SMTPClient();
		this.vStartMenu = vStartMenu;
		try {
			client.connect("localhost");
			if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
				client.disconnect();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(WHITE);
		frame.setBounds(100, 100, 429, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		headerColor = new Color(255, 194, 121);

		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("/iconos/anadir.png")));
		btnAdd.setFocusPainted(false);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btnAdd.setBorder(emptyBorder);
		btnAdd.setBackground(headerColor);
		btnAdd.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){  
					NewEmailView newEmail = new NewEmailView(client,user);
			 }
		});

		
		btnRecharge = new JButton("");
		btnRecharge.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("/iconos/recargar.png")));
		btnRecharge.setFocusPainted(false);
		btnRecharge.setBorder(emptyBorder);
		btnRecharge.setBackground(headerColor);
		btnRecharge.addActionListener(new ListenerUpdate());

		
		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("/iconos/cerrar.png")));
		btnClose.setFocusPainted(false);
		btnClose.setBorder(emptyBorder);
		btnClose.setBackground(headerColor);
		btnClose.addActionListener(new ListenerClose(frame,client,vStartMenu));

		txtSearch = new JTextField();
		txtSearch.setColumns(10);

		btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(EmailMenuWindow.class.getResource("/iconos/buscar.png")));
		btnSearch.setFocusPainted(false);
		btnSearch.setBorder(emptyBorder);
		btnSearch.setBackground(headerColor);
		btnSearch.addKeyListener(new ListenerSearch(txtSearch, user));

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnRecharge, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
						.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(btnClose,
								GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(15)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSearch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(btnSearch, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(btnRecharge, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36,
												Short.MAX_VALUE)
										.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		panel.setBackground(headerColor);
		panel.setLayout(gl_panel);

		emailBox = new JPanel();

		emailBox.setLayout(new BoxLayout(emailBox, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(emailBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);

	}

	// Agregar un correo
	public void viewEmails(Email email) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

		panel.add(new JLabel(email.getUser()));
		panel.add(new JLabel(email.getSubject()));
		panel.add(new JLabel(email.getFecha()));

		if (!email.getIsRead()) {
			panel.setBackground(WHITE);
		}

		panel.addMouseListener(new ListenerEmail(panel, email));
		emailBox.add(panel);
	}

	// vaciar el buzon
	public void restartEmailBox() {
		emailBox.removeAll();
	}

	public JTextField getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(JTextField txtSearch) {
		this.txtSearch = txtSearch;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JButton getBtnRecharge() {
		return btnRecharge;
	}

	public void setBtnRecharge(JButton btnRecharge) {
		this.btnRecharge = btnRecharge;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(JButton btnClose) {
		this.btnClose = btnClose;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
