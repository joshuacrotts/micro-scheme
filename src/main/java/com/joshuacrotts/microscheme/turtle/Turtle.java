//package com.joshuacrotts.microscheme.turtle;
//
//import org.antlr.v4.runtime.misc.Pair;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.geom.Line2D;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class Turtle {
//
//    private final ArrayList<Pair<Shape, Color>> SHAPES;
//
//    private double x;
//
//    private double y;
//
//    private double angle;
//
//    private Color color;
//
//    private boolean isPenDown;
//
//    private BufferedImage image;
//
//    private final int WIDTH = 32;
//
//    private final int HEIGHT = 32;
//
//    public Turtle(final double x, final double y, final double angle) {
//        this.x = x;
//        this.y = y;
//        this.angle = angle;
//        this.isPenDown = false;
//        try {
//            this.image = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("turtle.png")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.SHAPES = new ArrayList<>();
//        this.color = Color.RED;
//    }
//
//    public void drawTurtle(Graphics2D g2) {
//        for (Pair<Shape, Color> s : this.SHAPES) {
//            g2.setColor(s.b);
//            g2.draw(s.a);
//        }
//        g2.setColor(Color.BLACK);
//        g2.rotate(this.angle, x + WIDTH / 2.0, y + HEIGHT / 2.0);
//        g2.drawImage(this.image, (int) this.x, (int) this.y, this.WIDTH, this.HEIGHT, null);
//        g2.rotate(-this.angle, x + WIDTH / 2.0, y + HEIGHT / 2.0);
//    }
//
//    public void moveForward(double delta) {
//        double oldX = this.x;
//        double oldY = this.y;
//        this.x += (delta * Math.cos(Math.toRadians(this.angle)));
//        this.y += (delta * Math.sin(Math.toRadians(this.angle)));
//        if (this.isPenDown) {
//            this.SHAPES.add(new Pair<>(new Line2D.Double(oldX + this.WIDTH / 2.0, oldY + this.HEIGHT / 2.0, this.x + this.WIDTH / 2.0, this.y + this.HEIGHT / 2.0), this.color));
//        }
//
//    }
//
//    public void turnLeft() {
//        this.angle = (this.angle - 90) % 360;
//        System.out.println(angle);
//    }
//
//    public void setPenColor(Color color) {
//       //this.color = color;
//    }
//
//    public void setPenDown(boolean down) {
//        this.isPenDown = down;
//    }
//}
