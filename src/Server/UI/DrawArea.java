package Server.UI;

import client.UI.WhiteBoard;
import client.shape.*;
import client.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/*
    The DrawArea class is about the painting action of the mouse
 */
public class DrawArea extends JPanel {
    private WhiteBoard whiteboard = null;
    public java.util.List<Shape> shapeList = new java.util.ArrayList<Shape>(); // drawing graphs

    private int currentChoice = 3; // Set default pen as Pencil
    private Shape currentShape = null;
    private Shape lastShape = null;
    private Color color = Color.black; // current color of the pen
    private int R, G, B;

    private int f1, f2;
    private String stytle; // Current character style
    private float stroke = 1.0f; // set brush size and initialize it as 1.0

    public DrawArea(WhiteBoard wb) {
        whiteboard = wb;
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        // Set the cursor as a crossing
        setBackground(Color.white); // Set the background as white
        addMouseListener(new MouseA()); // Add mouse activity
        addMouseMotionListener(new MouseB());
        createNewShape();

    }

    // Get and set functions
    public void setColor(Color color) {
        this.color = color;
    }

    public void setStroke(float f) {
        stroke = f;
    }

    public String getStytle() {
        return stytle;
    }

    public void setStytle(String stytle) {
        this.stytle = stytle;
    }

    public void clearShapeList() {
        this.shapeList.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (!shapeList.isEmpty()) {
            for (Shape shape : shapeList) {
                draw(g2d, shape);
            }
        }
    }

    /*
        // Pass the pen object to sub-classes
     */
    private void draw(Graphics2D g2d, Shape i) {
        i.draw(g2d);
    }

    public void addShape(Shape shape){
        System.out.println(shapeList.size());
        this.shapeList.add(shape);
    }

    private void createNewShape() {
        if (currentChoice == 14) {
            setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        }
        Shape shape = null;
        switch (currentChoice) {
            case 3:
                shape = new Pencil();
                break;
            case 4:
                shape = new Line();
                break;
            case 5:
                shape = new Rect();
                break;
            case 6:
                shape = new FillRect();
                break;
            case 7:
                shape = new Oval();
                break;
            case 8:
                shape = new FillOval();
                break;
            case 9:
                shape = new Circle();
                break;
            case 10:
                shape = new FillCircle();
                break;
            case 11:
                shape = new RoundRect();
                break;
            case 12:
                shape = new FillRoundRect();
                break;
            case 13:
                shape = new Rubber();
                break;
            case 14:
                shape = new Word();
                break;
        }
        if (shape != null) {
            shape.type = currentChoice;
            shape.R = R;
            shape.G = G;
            shape.B = B;
            shape.stroke = stroke;
            this.lastShape = this.currentShape;
            this.currentShape = shape;
        }
    }


    /*
        Choose current color
     */
    public void chooseColor() {
        color = JColorChooser.showDialog(whiteboard, "Please choose color", color);
        try {
            R = color.getRed();
            G = color.getGreen();
            B = color.getBlue();
        } catch (Exception e) {
            R = 0;
            G = 0;
            B = 0;
        }
    }

    /*
     The size of the pen
     */
    public void setStroke() {
        String input;
        input = JOptionPane.showInputDialog("Please enter size of brush( >0 )");
        try {
            stroke = Float.parseFloat(input);

        } catch (Exception e) {
            stroke = 1.0f;

        }

    }

    /*
        Text input
     */
    public void setCurrentChoice(int i) {
        currentChoice = i;
    }

    /*
        Font of the characters
     */
    public void setFont(int i, int font) {
        if (i == 1) {
            f1 = font;
        } else
            f2 = font;
    }

    /*
        The corresponding responses for the mouse actions
     */
    class MouseA extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent me) {
            // TODO mouse enter
            whiteboard.setStratBar("Mouse enters in：[" + me.getX() + " ,"
                    + me.getY() + "]");
        }

        @Override
        public void mouseExited(MouseEvent me) {
            // TODO mouse exits
            whiteboard.setStratBar("Mouse exits from：[" + me.getX() + " ,"
                    + me.getY() + "]");
        }

        @Override
        public void mousePressed(MouseEvent me) {
            createNewShape();
            addShape(currentShape);
            whiteboard.setStratBar("Mouse clicked at：[" + me.getX() + " ,"
                    + me.getY() + "]");
            // Set instruction for start bar
            currentShape.x1 = currentShape.x2 = currentShape.ix = me.getX();
            currentShape.y1 = currentShape.y2 = currentShape.iy = me.getY();

            // Function for text input
            if (currentChoice == 14) {
                currentShape.x1 = me.getX();
                currentShape.y1 = me.getY();
                String input;
                input = JOptionPane.showInputDialog("Please enter your input！");
                currentShape.s1 = input;
                currentShape.x2 = f1;
                currentShape.y2 = f2;
                currentShape.s2 = stytle;
                currentChoice = 14;
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            whiteboard.setStratBar("Mouse loosen at：[" + me.getX() + " ,"
                    + me.getY() + "]");
            if (currentChoice == 3 || currentChoice == 13) {
                currentShape.x1 = me.getX();
                currentShape.y1 = me.getY();
            } else if(currentChoice != 14){
                if (me.getY() >= currentShape.iy) {
                    currentShape.x1 = me.getX();
                    currentShape.y1 = me.getY();
                    currentShape.x2 = currentShape.ix;
                    currentShape.y2 = currentShape.iy;
                } else {
                    currentShape.x2 = me.getX();
                    currentShape.y2 = me.getY();
                    currentShape.x1 = currentShape.ix;
                    currentShape.y1 = currentShape.iy;
                }
            }
            repaint();
        }

    }

    /*
        The corresponding reaction for scroll and drag of the mouse
     */
    class MouseB extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent me) // Drag mouse operation
        {
            whiteboard.setStratBar("Mouse dragged at：[" + me.getX() + " ,"
                    + me.getY() + "]");
            if (currentChoice == 3 || currentChoice == 13) {
                createNewShape(); // Create new graph object
                lastShape.x1 = currentShape.x2 = currentShape.x1 = me
                        .getX();
                lastShape.y1 = currentShape.y2 = currentShape.y1 = me
                        .getY();
                addShape(currentShape);
            } else if(currentChoice != 14){
                if (me.getY() >= currentShape.iy) {
                    currentShape.x1 = me.getX();
                    currentShape.y1 = me.getY();
                    currentShape.x2 = currentShape.ix;
                    currentShape.y2 = currentShape.iy;
                } else {
                    currentShape.x2 = me.getX();
                    currentShape.y2 = me.getY();
                    currentShape.x1 = currentShape.ix;
                    currentShape.y1 = currentShape.iy;
                }

            }
            repaint();
        }

        /*
         Mouse movement operation
         */
        @Override
        public void mouseMoved(MouseEvent me) {
            whiteboard.setStratBar("Mouse moves at：[" + me.getX() + " ,"
                    + me.getY() + "]");
        }
    }

}
