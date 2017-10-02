package client.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;




public class Login implements ActionListener{
    public JFrame loginFrame = new JFrame("Mini-Canvas  Client Login");

    
    public void login_window(){
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowWidth = 440;
        int windowHeight = 300;
        
        JPanel icon = new JPanel();
        JPanel text = new JPanel();
        JLabel background = new JLabel();
        JLabel ip = new JLabel("   Your   IP  address:");
        JLabel port = new JLabel("   Your port number:");
        JTextField ipAddress = new JTextField(15);
        JTextField portNumber = new JTextField(15);
        JButton connect = new JButton(" Connect ");
        JButton exit = new JButton("     Exit    ");
        
		connect.addActionListener(this);
		connect.setActionCommand("connect");
		exit.addActionListener(this);
		exit.setActionCommand("exit");
        
        
        ImageIcon image = new ImageIcon(getClass().getResource("/icon/Loginbackground.jpg"));
        background.setIcon(image);
        icon.add(background);
        
        JPanel inputIp = new JPanel();
        inputIp.add(ip);
        inputIp.add(ipAddress);
        
        JPanel inputPort = new JPanel();
        inputPort.add(port);
        inputPort.add(portNumber);
        
        JPanel buttons = new JPanel();
        JPanel bbuttons = new JPanel();
        buttons.add(bbuttons, BorderLayout.CENTER);
        bbuttons.add(connect);
        bbuttons.add(exit);
        
        text.setLayout( new BorderLayout() );
        text.add(inputIp,BorderLayout.NORTH);
        text.add(inputPort,BorderLayout.CENTER);
        text.add(buttons,BorderLayout.SOUTH);
        
        loginFrame.setBounds((width - windowWidth) / 2,
                (height - windowHeight) / 2, windowWidth, windowHeight);
        loginFrame.setVisible(true);
        loginFrame.setLayout( new BorderLayout() );
        loginFrame.add(icon, BorderLayout.NORTH);
        loginFrame.add(text, BorderLayout.CENTER);
                
    }   
    
    public void actionPerformed(ActionEvent e) {  
        if(e.getActionCommand().equals("connect")){  
            //System.out.println("点击了black");
        	@SuppressWarnings("unused")
            WhiteBoard whiteBoard = new WhiteBoard("Mini-Canvas Client");
        	loginFrame.dispose();
        	
        }  
        if(e.getActionCommand().equals("exit")){  
            System.exit(0);  
        }  
    }
}