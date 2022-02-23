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
    private Color drawColor;

    /**
     * Current color used to fill shape.
     */
    private Color fillColor;

    /**
     * Flag to keep track of if the pen is down. True by default.
     */
    private boolean isPenDown;

    /**
     * Flag to keep track of if we fill polygons or not.
     */
    private boolean isPenFilled;

    /**
     * Keeps track of the current shape we're building.
     */
    private Path2D currentFillShape;

    public Turtle(final double cx, final double cy, final double angle) {
        this.SHAPES = new ArrayList<>();
        this.cx = cx;
        this.cy = cy;
        this.angle = angle;
        this.isPenDown = true;
        this.isPenFilled = false;
        this.penWidth = this.DEFAULT_PEN_WIDTH;
        this.drawColor = Color.BLACK;
        this.fillColor = Color.BLACK;
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw the saved shapes.
        this.drawSavedShapes(g2);

        // Draw the turtle.
        this.drawTurtle(g2);
    }

    public void moveForward(double delta) {
        double oldCX = this.cx;
        double oldCY = this.cy;
        this.cx += (delta * Math.cos(Math.toRadians(this.angle)));
        this.cy += (delta * Math.sin(Math.toRadians(this.angle)));
        this.addMovementShape(oldCX, oldCY);
    }

    public void goTo(double newX, double newY) {
        double oldCX = this.cx;
        double oldCY = this.cy;
        this.cx = newX;
        this.cy = newY;
        this.addMovementShape(oldCX, oldCY);
    }

    public void turn(double angle) {
        this.angle += angle;
    }

    public void setPenColor(Color color) {
       this.drawColor = color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void setPenDown(boolean down) {
        this.isPenDown = down;
    }

    public void setPenFilled(boolean fill) {
        this.isPenFilled = fill;
    }

    public void beginFill() {
        this.currentFillShape = new Path2D.Double();
        this.currentFillShape.moveTo(this.cx, this.cy);
    }

    public void endFill() {
        this.SHAPES.add(new TurtleShape(this.currentFillShape, drawColor, fillColor, new BasicStroke(this.penWidth), true));
        this.currentFillShape = null;
    }

    public void setPenWidth(int width) {
        this.penWidth = width;
    }

    public double getX() {
        return this.cx;
    }

    public double getY() {
        return this.cy;
    }

    public void addPath(double px, double py) {
        this.currentFillShape.lineTo(px, py);
    }

    private void addMovementShape(double oldCX, double oldCY) {
        if (this.currentFillShape != null) {
            this.addPath(cx, cy);
        } else if (this.isPenDown) {
            this.SHAPES.add(new TurtleShape(new Line2D.Double(oldCX, oldCY, this.cx, this.cy),
                    this.drawColor, this.fillColor, new BasicStroke(this.penWidth), this.isPenFilled));
        }
    }

    private void drawSavedShapes(Graphics2D g2) {
        for (int i = 0; i < this.SHAPES.size(); i++) {
            TurtleShape s = this.SHAPES.get(i);
            g2.setStroke(s.stroke);
            if (s.filled) {
                g2.setColor(s.fillColor);
                g2.fill(s.shape);
                g2.setColor(s.drawColor);
                g2.draw(s.shape);
            } else {
                g2.setColor(s.drawColor);
                g2.draw(s.shape);
            }
        }
    }

    private void drawTurtle(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.rotate(Math.toRadians(this.angle), cx, cy);
        Path2D poly = new Path2D.Double();
        poly.moveTo(cx - 4, cy - 4);
        poly.lineTo(cx - 4, cy + 4);
        poly.lineTo(cx + 9, cy);
        g2.fill(poly);
        g2.rotate(-Math.toRadians(this.angle), cx, cy);
    }

    private static class TurtleShape {
        private final Shape shape;
        private final Color drawColor;
        private final Color fillColor;
        private final Stroke stroke;
        private final boolean filled;

        public TurtleShape(Shape shape, Color drawColor, Color fillColor, Stroke stroke, boolean filled) {
            this.shape = shape;
            this.drawColor = drawColor;
            this.fillColor = fillColor;
            this.stroke = stroke;
            this.filled = filled;
        }
    }
}
