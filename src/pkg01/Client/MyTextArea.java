package pkg01.Client;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JTextArea {

    public Image image = null;

    public MyTextArea() {

        setOpaque(false);

        setForeground(Color.BLACK);

        image = Toolkit.getDefaultToolkit().createImage(
                getClass().getResource("bglys2.png"));
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }
}
