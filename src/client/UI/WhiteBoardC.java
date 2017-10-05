package client.UI;

import client.file.FileHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.*;

// Draw the main frame of the white board
public class WhiteBoardC extends JFrame implements ActionListener {

    private static final long serialVersionUID = -2551980583852173918L;
    private JToolBar buttonpanel;
    // define the button panel
    private JMenuBar bar;
    private JMenu file, color, stroke, help;
    // four main menu of the button panel
    private JMenuItem newfile, openfile, savefile, exit;
    private JMenuItem helpin, helpmain, colorchoice, strokeitem;
    private Icon nf, sf, of, ex;
    // The icon objects of the button panel
    private JLabel startbar;
    private DrawArea drawarea;
    private Help helpobject;
    private FileHandler fileclass;

    private JPanel userinfo;
    private JScrollPane chat;

    String[] fontName;
    // Define the name of the icons in the button panel
    private String names[] = {"newfile", "openfile", "savefile", "pen",
            "line", "rect", "frect", "oval", "foval", "circle", "fcircle",
            "roundrect", "froundrect", "rubber", "color", "stroke", "word"};
    private Icon icons[];

    // Show instruction when the mouse moves above the button
    private String tiptext[] = {"create a picture", "open a picture",
            "save the picture", "freely draw", "draw a straight line",
            "draw a hollow rectangle", "draw a solid rectangle",
            "draw a hollow oval", "draw a solid oval", "draw a hollow circle",
            "draw a solid circle", "draw a rounded corner rectangle",
            "draw a solid rounded corner rectangle", "eraser", "color",
            "brush size", "text input"};
    JButton button[]; // define button group in toolbar
    private JCheckBox bold, italic;

    private JComboBox stytles;

    public WhiteBoardC(String string) {
        // TODO constructor of main interface
        super(string);
        // initial menu
        file = new JMenu("file");
        color = new JMenu("color");
        stroke = new JMenu("brush");
        help = new JMenu("help");
        bar = new JMenuBar();// initial menu

        bar.add(file);
        bar.add(color);
        bar.add(stroke);
        bar.add(help);

        setJMenuBar(bar);

        // Define short cut keys for the buttons
        file.setMnemonic('F');
        color.setMnemonic('C');
        stroke.setMnemonic('S');
        help.setMnemonic('H');

        // File menu initialization
        try {
            Reader reader = new InputStreamReader(getClass()
                    .getResourceAsStream("/icon"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Can't read image from the disk", "error",
                    JOptionPane.ERROR_MESSAGE);
        }
        // Show the icons for the sub-buttons of File menu
        nf = new ImageIcon(getClass().getResource("/icon/newfile.jpg"));
        sf = new ImageIcon(getClass().getResource("/icon/savefile.jpg"));
        of = new ImageIcon(getClass().getResource("/icon/openfile.jpg"));
        ex = new ImageIcon(getClass().getResource("/icon/exit.jpg"));
        newfile = new JMenuItem("new", nf);
        openfile = new JMenuItem("open", of);
        savefile = new JMenuItem("save", sf);
        exit = new JMenuItem("exit", ex);

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(exit);

        // Add short cut keys for the buttons
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
                InputEvent.CTRL_MASK));
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                InputEvent.CTRL_MASK));

        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        exit.addActionListener(this);

        // Initialization of the color palate
        colorchoice = new JMenuItem("color palete");
        colorchoice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.CTRL_MASK));
        colorchoice.addActionListener(this);
        color.add(colorchoice);
        // Initialization of the Help menu
        helpmain = new JMenuItem("User document");
        helpin = new JMenuItem("About this program");

        // Add function to the help menu
        help.add(helpmain);
        help.addSeparator(); // Set a line between sub-menu
        help.add(helpin);
        helpin.addActionListener(this);
        helpmain.addActionListener(this);

        // Initialization for stroke menu
        strokeitem = new JMenuItem("set brush");
        strokeitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                InputEvent.CTRL_MASK));
        stroke.add(strokeitem);
        strokeitem.addActionListener(this);

        // Initialization for the toolbar
        buttonpanel = new JToolBar(JToolBar.HORIZONTAL);
        icons = new ImageIcon[names.length];
        button = new JButton[names.length];
        for (int i = 0; i < names.length; i++) {
            // read icons for the button to the buffer
            icons[i] = new ImageIcon(getClass().getResource(
                    "/icon/" + names[i] + ".jpg"));
            // Create the buttons
            button[i] = new JButton("", icons[i]);
            // Show instructions when the mouse moves over the icon
            button[i].setToolTipText(tiptext[i]);
            buttonpanel.add(button[i]);
            button[i].setBackground(Color.white);
            if (i < 3) {
                button[i].addActionListener(this);
            } else if (i <= 16) {
                button[i].addActionListener(this);
            }

        }
        // Handles the styles of the characters
        CheckBoxHandler CHandler = new CheckBoxHandler();
        bold = new JCheckBox("bold");
        bold.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        // Set font for the character "bold"
        bold.addItemListener(CHandler);
        italic = new JCheckBox("italic");
        italic.addItemListener(CHandler);
        // Set font for the character "italic"
        italic.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        // Get available fonts from the computer
        fontName = ge.getAvailableFontFamilyNames();
        stytles = new JComboBox(fontName); // Initialization for the font lists
        stytles.addItemListener(CHandler);
        stytles.setMaximumSize(new Dimension(400, 50));
        stytles.setMinimumSize(new Dimension(250, 40));
        stytles.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        // Set font for the lists

        buttonpanel.add(bold);
        buttonpanel.add(italic);
        buttonpanel.add(stytles);

        // Initialization for the start bar
        startbar = new JLabel("White Board");

        userinfo = new JPanel();
        userinfo.setPreferredSize(new Dimension(200, 10));
        setVisible(true);
        validate();

        chat = new JScrollPane();
        // chat.add(new Label("Message",Label.CENTER));
        chat.setPreferredSize(new Dimension(400, 10));
        // chat.setLayout( new BorderLayout() );
        // chat.add( createRecvArea(),BorderLayout.NORTH);
        // chat.add( createSendArea(),BorderLayout.CENTER);
        // chat.add( createSendButtonArea(),BorderLayout.SOUTH);
        setVisible(true);
        validate();

        // Initialization for the canvas
        drawarea = new DrawArea(this);
        helpobject = new Help(this);
        fileclass = new FileHandler(this, drawarea);
        setVisible(true);
        validate();

        Container con = getContentPane(); // Get the canvas implemented
        con.add(buttonpanel, BorderLayout.NORTH);
        con.add(drawarea, BorderLayout.CENTER);
        con.add(startbar, BorderLayout.SOUTH);
        con.add(userinfo, BorderLayout.WEST);
        con.add(chat, BorderLayout.EAST);

        Toolkit tool = getToolkit();
        Dimension dim = tool.getScreenSize();// Get the size of current screen
        setBounds(0, 0, dim.width, dim.height - 40);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    // The characters shown in the start bar
    public void setStratBar(String s) {
        startbar.setText(s);
    }

    /*
      The functions of the button
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 3; i <= 13; i++) {
            if (e.getSource() == button[i]) {
                drawarea.setCurrentShapeType(DrawArea.ShapeType.values()[i-3]);
                drawarea.repaint();
            }

        }
        if (e.getSource() == newfile || e.getSource() == button[0]){
            // new file
            fileclass.newFile();
        } else if (e.getSource() == openfile || e.getSource() == button[1]){
            // open file
            fileclass.openFile();
        } else if (e.getSource() == savefile || e.getSource() == button[2]){
            // save file
            fileclass.saveFile();
        } else if (e.getSource() == exit){
            // exit
            System.exit(0);
        } else if (e.getSource() == button[14]){
            // color plate
            drawarea.chooseColor();// Choose your color
        } else if (e.getSource() == button[15] || e.getSource() == strokeitem){
            // Brush size
            drawarea.setStroke();
        } else if (e.getSource() == button[16]){
            // add text
            JOptionPane.showMessageDialog(null,
                    "please click on canvs to confirm position of textinput",
                    "hints", JOptionPane.INFORMATION_MESSAGE);
            drawarea.setCurrentShapeType(DrawArea.ShapeType.WORD);
            drawarea.repaint();
        } else if (e.getSource() == helpin){
            // Help info
            helpobject.AboutBook();
        } else if (e.getSource() == helpmain){
            // About this program
            helpobject.MainHeip();
        }

    }

    // About the font of the character
    public class CheckBoxHandler implements ItemListener {

        public void itemStateChanged(ItemEvent ie) {
            // TODO the font of the character
            if (ie.getSource() == bold) // Bold font
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                    drawarea.setFont(1, Font.BOLD);
                else
                    drawarea.setFont(1, Font.PLAIN);
            } else if (ie.getSource() == italic) // Italic font
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                    drawarea.setFont(2, Font.ITALIC);
                else
                    drawarea.setFont(2, Font.PLAIN);

            } else if (ie.getSource() == stytles) // System font
            {
                drawarea.setStytle(fontName[stytles.getSelectedIndex()]);
            }
        }

    }
}