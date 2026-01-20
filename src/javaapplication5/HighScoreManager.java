/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication5;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
/**
 *
 * @author Aaron
 */
public class HighScoreManager {
    private int highScore;
    
    public HighScoreManager() {
        highScore = loadHighScore();
    }
    
    private int loadHighScore() {
        int score = 0;
        
        try {
            Scanner input = new Scanner(new File("highscore.txt"));
            if (input.hasNextInt()) {
                score = input.nextInt();
            }
            input.close();
        } catch (IOException ioException) {
            System.err.println( "java Exception: " + ioException);
        }
        return score;
    }
    
    public void saveHighScore(int score) {
        if (score > highScore) {
            highScore = score;
            
            try {
                FileWriter writer = new FileWriter("highscore.txt", false);
                PrintWriter output = new PrintWriter(writer);
                output.println(highScore);
                output.close();
            } catch (IOException ioException) {
                System.err.println( "java Exception: " + ioException);
            }
        }
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public boolean isNewHighScore(int score) {
        return score > highScore;
    }
}
