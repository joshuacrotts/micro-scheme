package com.joshuacrotts.microscheme.turtle;

import org.antlr.v4.runtime.misc.Pair;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class Turtle {

    private final int DEFAULT_PEN_WIDTH = 1;

    /**
     * Current list of shapes drawn by this turtle.
     */
    private final ArrayList<TurtleShape> SHAPES;

    /**
     * Center x coordinate of turtle.
     */
    private double cx;

    /**
     * Center y coordinate of turtle.
     */
    private double cy;

    /**
     * Current angle of turtle.
     */
    private double angle;

    /**
     * Current width/stroke of the pen.
     */
    private int penWidth;

    /**
     * Current color of pen.
     */
    private Color color;

    /**
     * Flag to keep track of if the pen is down. True by default.
     */
    private boolean isPenDown;

    /**
     * Flag to keep track of if we fill polygons or not.
     */
    private boolean isPenFilled;

    public Turtle(final double cx, final double cy, final double angle) {
        this.SHAPES = new ArrayList<>();
        this.cx = cx;
        this.cy = cy;
        this.angle = angle;
        this.isPenDown = true;
        this.isPenFilled = false;
        this.penWidth = this.DEFAULT_PEN_WIDTH;
        this.color = Color.BLACK;
    }

    public void drawTurtle(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < this.SHAPES.size(); i++) {
            TurtleShape s = this.SHAPES.get(i);
            g2.setStroke(s.stroke);
            g2.setColor(s.color);
            if (s.filled) {
                g2.fill(s.shape);
            } else {
                g2.draw(s.shape);
            }
        }
        g2.setColor(Color.BLACK);
        g2.rotate(Math.toRadians(this.angle), cx, cy);
        Path2D poly = new Path2D.Double();
        poly.moveTo(cx - 4, cy - 4);
        poly.lineTo(cx - 4, cy + 4);
        poly.lineTo(cx + 9, cy);
        g2.fill(poly);
        g2.rotate(-Math.toRadians(this.angle), cx, cy);
    }

    public void moveForward(double delta) {
        double oldCX = this.cx;
        double oldCY = this.cy;
        this.cx += (delta * Math.cos(Math.toRadians(this.angle)));
        this.cy += (delta * Math.sin(Math.toRadians(this.angle)));
        if (this.isPenDown) {
            this.SHAPES.add(new TurtleShape(new Line2D.Double(oldCX, oldCY, this.cx, this.cy),
                    this.color, new BasicStroke(this.penWidth), this.isPenFilled));
        }
    }

    public void goTo(double newX, double newY) {
        double oldCX = this.cx;
        double oldCY = this.cy;
        this.cx = newX;
        this.cy = newY;
        if (this.isPenDown) {
            this.SHAPES.add(new TurtleShape(new Line2D.Double(oldCX, oldCY, this.cx, this.cy), this.color, new BasicStroke(this.penWidth), this.isPenFilled));
        }
    }

    public void turn(double angle) {
        this.angle += angle;
    }

    public void setPenColor(Color color) {
       this.color = color;
    }

    public void setPenDown(boolean down) {
        this.isPenDown = down;
    }

    public void setPenFilled(boolean fill) {
        this.isPenFilled = fill;
    }

    public void setPenWidth(int width) {
        this.penWidth = width;
    }

    private static class TurtleShape {
        private Shape shape;
        private Color color;
        private Stroke stroke;
        private boolean filled;

        public TurtleShape(Shape shape, Color color, Stroke stroke, boolean filled) {
            this.shape = shape;
            this.color = color;
            this.stroke = stroke;
            this.filled = filled;
        }
    }
}
