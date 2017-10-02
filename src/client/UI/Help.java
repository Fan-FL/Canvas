package client.UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Help extends JFrame {
    private WhiteBoard whiteboard = null;

    public Help(WhiteBoard wb) {
        whiteboard = wb;
    }

    public void MainHeip() {
        JOptionPane.showMessageDialog(this, "A shared white board", "Mini-canvas", JOptionPane.WARNING_MESSAGE);
    }

    public void AboutBook() {
        JOptionPane.showMessageDialog(whiteboard, "Mini-canvas" + "\n" + "    Version 1.0" + "\n"
                        + "    Distributed system project" + "\n",
                "Mini-canvas", JOptionPane.WARNING_MESSAGE);
    }
}

