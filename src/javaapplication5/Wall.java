/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication5;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author Aaron
 */
public class Wall {
    private PApplet app;
    private int x, y;
    private int width, height;
    private PImage wall;
    
    // Constructor
    public Wall(PApplet app, int x, int y, int width, int height, String imagePath) {
        this.app = app;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        // Tries to load wall image
        try {
            this.wall = app.loadImage(imagePath);
        } catch (Exception e) {
            this.wall = null;
        }
    }
    // Draws wall
    public void draw() {
        if (wall != null) {
            app.image(wall, x, y, width, height);
        } else {
            // colour if image fails to load
            app.fill(210, 180, 140);
            app.rect(x, y, width, height);
        }
    }
    
    // Checks for collision
    public boolean isCollidingWith(int objX, int objY, int objWidth, int objHeight) {
        return objX < x + width && objX + objWidth > x && objY < y + height && objY + objHeight > y;
    }
    
    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
