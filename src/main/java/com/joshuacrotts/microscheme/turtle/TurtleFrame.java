package com.joshuacrotts.microscheme.turtle;

import javax.swing.*;
import java.awt.*;

public class TurtleFrame extends JFrame {

    /**
     *
     */
    private final TurtlePanel PANEL;

    /**
     *
     */
    private final int WIDTH;

    /**
     *
     */
    private final int HEIGHT;

    public TurtleFrame(final int width, final int height, final int cx, final int cy) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.PANEL = new TurtlePanel(new Turtle(cx, cy, 0), this.WIDTH, this.HEIGHT);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.PANEL, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public TurtlePanel getPanel() {
        return this.PANEL;
    }

    public Turtle getTurtle() {
        return this.PANEL.getTurtle();
    }
}
