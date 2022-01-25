//package com.joshuacrotts.minischeme.turtle;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class TurtleFrame extends JFrame {
//
//    /**
//     *
//     */
//    private final TurtlePanel PANEL;
//
//    /**
//     *
//     */
//    private final int WIDTH = 600;
//
//    /**
//     *
//     */
//    private final int HEIGHT = 600;
//
//    /**
//     *
//     */
//    private final TurtleInputField FIELD;
//
//    /**
//     *
//     */
//    private final Turtle TURTLE;
//
//    public TurtleFrame() {
//        this.TURTLE = new Turtle(250, 250, 0);
//        this.PANEL = new TurtlePanel(this.TURTLE, this.WIDTH, this.HEIGHT);
//        this.FIELD = new TurtleInputField(this.TURTLE);
//        this.setLayout(new BorderLayout());
//        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.add(this.PANEL, BorderLayout.CENTER);
//        this.add(this.FIELD, BorderLayout.SOUTH);
//        this.pack();
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//    }
//}
