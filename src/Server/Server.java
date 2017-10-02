package Server;


import Server.UI.Startserver;

import javax.swing.JOptionPane;

public class Server {
	// The main class of this program
	public static void main(String[] args){
		Startserver start_server = new Startserver();
			try{
				start_server.startServer();
			}
			catch (Exception e){
				int n = JOptionPane.showConfirmDialog(null, "Input error, do you wish to try again?", "Instruction",JOptionPane.YES_NO_OPTION);
	            if(n == JOptionPane.YES_OPTION){
	            	
	            }else if(n == JOptionPane.NO_OPTION){
	            	System.exit(0);
	            }
			}
		}
	}

