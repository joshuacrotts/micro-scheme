//package com.joshuacrotts.microscheme.turtle;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TurtleInputField extends JTextField implements ActionListener {
//
//    /**
//     *
//     */
//    private final Turtle TURTLE;
//
//    public TurtleInputField(final Turtle turtle) {
//        this.addActionListener(this);
//        this.TURTLE = turtle;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String cmd = this.getText();
//        String[] args = cmd.split(" ");
//        if (args[0].equals("moveForward")) {
//            System.out.println("Moving forward");
//            this.TURTLE.moveForward(Integer.parseInt(args[1]));
//        } else if (args[0].equals("turnLeft")) {
//            System.out.println("Turning left");
//            this.TURTLE.turnLeft();
//        } else if (args[0].equals("setPenDown")) {
//            System.out.println("Setting pen down");
//            this.TURTLE.setPenDown(Boolean.parseBoolean(args[1]));
//        } else if (args[0].equals("setPenColor")) {
//            this.TURTLE.setPenColor(Color.getColor(args[1]));
//        }
//    }
//}
