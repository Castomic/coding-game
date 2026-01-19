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
    private float x, y;
    private int width, height;
    private PImage wall;
    
    public Wall(PApplet app, float x, float y, int width, int height, String imagePath) {
        this.app = app;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        try {
            this.wall = app.loadImage(imagePath);
        } catch (Exception e) {
            this.wall = null;
        }
    }
    
    public void draw() {
        if (wall != null) {
            app.image(wall, x, y, width, height);
        } else {
            app.fill(210, 180, 140);
            app.rect(x, y, width, height);
        }
    }
    
    public boolean isCollidingWith(float objX, float objY, int objWidth, int objHeight) {
        return objX < x + width && objX + objWidth > x && objY < y + height && objY + objHeight > y;
    }
    
    // Getter
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
