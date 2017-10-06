package Server;


import Server.UI.Startserver;
import Server.UI.WhiteBoardS;
import client.UI.Login;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Server {
	// The main class of this program
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Startserver start_server = new Startserver();
					start_server.startServer();
					//start_server.startFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		}
	}

