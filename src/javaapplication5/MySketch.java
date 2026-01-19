/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication5;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author 342800661
 */
import processing.core.PApplet;

public class MySketch extends PApplet {
  private Person person;
  private PImage storypage1;
  private PImage storypage2;
  private PImage storypage3;
  private PImage storypage4;
  private PImage storypage5;
  private PImage storypage6;
  private PImage storypage7;
  private PImage storypage8;
  private PImage ending1page1;
  private PImage ending1page2;
  private PImage ending1page3;
  private PImage ending1page4;
  private PImage ending1page5;
  private PImage ending1page6;
  private PImage ending1page7;
  private PImage ending2page1;
  private PImage ending2page2;
  private PImage ending2page3;
  private PImage ending2page4;
  private PImage ending2page5;
  private PImage ending2page6;
  private PImage ending2page7;
  private PImage ending2page8;
  private PImage ending2page9;
  private int stage = 0;
  private static final int WORLD_WIDTH = 1200;
  private static final int WORLD_HEIGHT = 1200;
  private static final float PLAYER_SPEED = 5;
 
  public void settings() {
    size(1200, 1200);
  }

  public void setup() {
    background(255);
    textSize(20);
    this.storypage1 = this.loadImage("images/story1.png");
    person = new Person(this, 0, 200, "images/person.png");
  }
  
  public void draw() {
    background(255);
        
        
    if (stage == 1) {
        person.draw();
        if (keyPressed) {
            if (keyCode == LEFT) {
              person.move(-5, 0, WORLD_WIDTH, WORLD_HEIGHT);
            } else if (keyCode == RIGHT) {
              person.move(5, 0, WORLD_WIDTH, WORLD_HEIGHT);
            } else if (keyCode == UP) {
              person.move(0, -5, WORLD_WIDTH, WORLD_HEIGHT);
            } else if (keyCode == DOWN) {
              person.move(0, 5, WORLD_WIDTH, WORLD_HEIGHT);
            }
        } 
    }
   

  }
  public void keyPressed() {
      if (stage == 0) {
          if (keyCode == ENTER) {
              stage = 1;
          } 
      }
  }
  public void mousePressed() {
      if (stage == 1) {
              storypage1 = storypage2;

      }
  }
}//end class

