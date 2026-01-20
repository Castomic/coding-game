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
  private PImage menuBg;
  private PImage gameBg;
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
  private int currentStoryPage = 0;
  private int knockback = 40;
  private Person[] enemies;
  private int enemyCount = 0;
  private int maxEnemies = 35;
  private int enemiesDefeated = 0;
  private int maxExtraEnemies = 15; // Max extra enemies that can spawn per level
  private int spawnTimer = 0; // Enemy spawn timers
  private int spawnDelay; // Changes per level
  private String nextSpawnType = "slow"; // Alternates between enemy types
  private Person[] healthPotions;
  private Person[] scrolls;
  private Person goldenCudgel;
  private int potionCount = 0;
  private int scrollCount = 0;
  private int maxPotions = 8;
  private int maxScrolls = 18;
  private Wall[] walls;
  private int wallCount = 0;
  private int maxWalls = 20;
  private int currentLevel = 1;
  private boolean cudgelPickedUp = false;
  private boolean allScrollsCollected = false;
  private HighScoreManager highScoreManager;
  private int[][] spawnPositions;
  private int spawnPosCount = 0;
  private static final int WORLD_WIDTH = 1200;
  private static final int WORLD_HEIGHT = 1200;
  private static final float PLAYER_SPEED = 5;
 
  // World size
  public void settings() {
    size(1200, 1200);
  }

  public void setup() {
    background(255);
    textSize(20);
    menuBg = loadImage("menu_bg.png");
    gameBg = loadImage("game_bg.png");
    highScoreManager = new HighScoreManager();
    
    // Load story images
    loadStoryImages();
    
    // Initialize arrays
    enemies = new Person[maxEnemies];
    healthPotions = new Person[maxPotions];
    scrolls = new Person[maxScrolls];
    walls = new Wall[maxWalls];
    
    // Create player
    person = new Person(this, 100, 100, "images/person.png", "player", 100);
    
    setupLevel(1);
  }
  
  public void draw() {
    background(255);
    switch (stage) {
        case 0: // Menu
            image(menuBg, 0, 0, width, height);
            drawMenu();
            break;

        case 1: // Intro Story (8 pages)
            drawIntroStory();
            break;

        case 2: // Game
            updateGame();
            drawGame();
            break;

        case 3: // Ending 1 - Normal ending (with cudgel, 7 pages)
            drawEnding1();
            break;

        case 4: // Ending 2 - Passive ending (without cudgel, 9 pages)
            drawEnding2();
            break;
    }
  }
  
  private void drawMenu() {
    fill(0);
    // Show high score
    textSize(50);
    text("High Score: " + highScoreManager.getHighScore(), width / 2 - 150, 100);
  }
  
  // Introduction story
  private void drawIntroStory() {
    PImage currentPage = null;
    
    switch (currentStoryPage) {
        case 0:
            currentPage = storypage1;
            break;
        case 1:
            currentPage = storypage2;
            break;
        case 2:
            currentPage = storypage3;
            break;
        case 3:
            currentPage = storypage4;
            break;
        case 4:
            currentPage = storypage5;
            break;
        case 5:
            currentPage = storypage6;
            break;
        case 6:
            currentPage = storypage7;
            break;
        case 7:
            currentPage = storypage8;
            break;
    }
    
    if (currentPage != null) {
        image(currentPage, 0, 0, width, height);
    } else {
        fill(0);
        textSize(24);
        text("Story Page " + (currentStoryPage + 1), width / 2, height / 2);
    }
  }
  
  // Ending sotry 1
  private void drawEnding1() {
    PImage currentPage = null;
      
    switch (currentStoryPage) {
        case 0:
            currentPage = ending1page1;
            break;
        case 1:
            currentPage = ending1page2;
            break;
        case 2:
            currentPage = ending1page3;
            break;
        case 3:
            currentPage = ending1page4;
            break;
        case 4:
            currentPage = ending1page5;
            break;
        case 5:
            currentPage = ending1page6;
            break;
        case 6:
            currentPage = ending1page7;
            break;
    }
    
    if (currentPage != null) {
        image(currentPage, 0, 0, width, height);
    } else {
        fill(0);
        textSize(24);
        text("Victory! Final Score: " + person.getScore(), width / 2, height / 2);
    }
  }
  
  // Ending Story 2
  private void drawEnding2() {
    PImage currentPage = null;
    
    switch (currentStoryPage) {
        case 0:
            currentPage = ending2page1;
            break;
        case 1:
            currentPage = ending2page2;
            break;
        case 2:
            currentPage = ending2page3;
            break;
        case 3:
            currentPage = ending2page4;
            break;
        case 4:
            currentPage = ending2page5;
            break;
        case 5:
            currentPage = ending2page6;
            break;
        case 6:
            currentPage = ending2page7;
            break;
        case 7:
            currentPage = ending2page8;
            break;
        case 8:
            currentPage = ending2page9;
            break;
    }
    
    if (currentPage != null) {
        image(currentPage, 0, 0, width, height);
    } else {
        fill(0);
        textSize(24);
        text("Passive Ending - Final Score: " + person.getScore(), width / 2, height / 2);
    }
  }
  
  private void drawGame() {
    // Background image
    image(gameBg, 0, 0, width, height);
    
    // Draw walls
    for (int i = 0; i < wallCount; i++) {
      walls[i].draw();
    }
    
    // Draw items
    for (int i = 0; i < potionCount; i++) {
        if (healthPotions[i] != null && healthPotions[i].isAlive()) {
            healthPotions[i].draw();
        }
    }
    for (int i = 0; i < scrollCount; i++) {
        if (scrolls[i] != null && scrolls[i].isAlive()) {
            scrolls[i].draw();
        }
    }
    if (goldenCudgel != null && goldenCudgel.isAlive()) {
        goldenCudgel.draw();
    }
    // Draw player
    person.draw();
    
    // Draw enemies
    for (int i = 0; i < enemyCount; i++) {
        if (enemies[i] != null && enemies[i].isAlive()) {
            enemies[i].draw();
        }
    }
    // Draw UI
    drawUI();
  }
  
  private void drawUI() {
    fill(255);
    textSize(30);

    // Health
    text("Health: " + person.getHealth() + "/100", 10, 30);

    // Score
    text("Score: " + person.getScore(), 10, 70);

    // High Score
    text("High Score: " + highScoreManager.getHighScore(), 10, 100);

    // Golden Cudgel status
    if (person.hasGoldenCudgel()) {
        text("Golden Cudgel: YES", 10, 120);
    } else {
        text("Golden Cudgel: NO", 10, 150);
    }
    
    // Level
    text("Level: " + currentLevel, 10, 180);

    // Enemies defeated
    text("Enemies Defeated: " + enemiesDefeated, 10, 210);
  }
  private void updateGame() {
    // Update player
    if (person.isAlive()) {
        // Player movement
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
        
        // Prevents player from going through wall
        for (int i = 0; i < wallCount; i++) {
            if (walls[i].isCollidingWith(person.x, person.y, person.getWidth(), person.getHeight())) {
                // Push player back (doesn't push player ingame, just stops player from moving into wall)
                if (keyPressed && keyCode == LEFT) {
                    person.setX(person.getX() + 5);
                }
                if (keyPressed && keyCode == RIGHT) {
                    person.setX(person.getX() - 5);
                }
                if (keyPressed && keyCode == UP) {
                    person.setY(person.getY() + 5);
                }
                if (keyPressed && keyCode == DOWN)
                    person.setY(person.getY() - 5);
            }
        }
        
        for (int i = 0; i < enemyCount; i++) {
            if (enemies[i].isAlive()) {
                enemies[i].updateAI(person.x, person.y);
                
                // Checks collision then deals damage
                if (person.isCollidingWith(enemies[i])) {
                    person.takeDamageFromEnemy(enemies[i].getType());
                    
                    // Enemy knockback
                    float dx = enemies[i].getX() - person.getX();
                    float dy = enemies[i].getY() - person.getY();
                    
                    float length = sqrt(dx * dx + dy * dy);
                    if (length != 0) {
                        dx /= length;
                        dy /= length;
                    }
                    enemies[i].setX(enemies[i].getX() + (int)(dx * knockback));
                    enemies[i].setY(enemies[i].getY() + (int)(dy * knockback));
                    // Keep enemy inside the world
                    keepInBounds(enemies[i]);
                }
            }
        }
        
        // Check item collection
        checkItemCollection();
        
        // Handle enemy spawning
        handleEnemySpawning();
        
        // Check level completion
        checkLevelCompletion();
    }
  }
  
  private void setupLevel(int level) {
    // Clear existing enemies but keep the array
    for (int i = 0; i < enemyCount; i++) {
        enemies[i] = null;
    }
    enemyCount = 0;
    enemiesDefeated = 0;
    
    // Clear items
    for (int i = 0; i < potionCount; i++) {
        healthPotions[i] = null;
    }
    potionCount = 0;
    
    for (int i = 0; i < scrollCount; i++) {
        scrolls[i] = null;
    }
    scrollCount = 0;
    wallCount = 0;
    
    switch (level) {
        case 1:
            spawnDelay = 420; // 7 seconds
            break;
        case 2:
            spawnDelay = 360; // 6 seconds
            break; 
        case 3:
            spawnDelay = 360; // 6 seconds
            break;
        case 4:
            spawnDelay = 300; // 5 seconds
            break;
        case 5:
            spawnDelay = 300; // 5 seconds
            break;
    }
    
    spawnTimer = spawnDelay;
    nextSpawnType = "slow";
    
    // Create walls (different layout per level)
    createWallLayout(level);
    
    // Create enemies
    createEnemiesForLevel(level);
    
    // Create items
    createItemsForLevel(level);
    
    // Spawn the golden cudgel only on level 1
    if (level == 1) {
        goldenCudgel = new Person(this, (int) random(100, WORLD_WIDTH - 100), (int) random(100, WORLD_HEIGHT - 100), "images/cudgel.png", "cudgel", 1);
        cudgelPickedUp = false;
    } else {
        goldenCudgel = null;
    }
    
    allScrollsCollected = false;
  }
  
  private void checkItemCollection() {
      // Health Potions
      for (int i = 0; i < potionCount; i++) {
          if (healthPotions[i] != null && healthPotions[i].isAlive() && person.collidesWith(healthPotions[i].x, healthPotions[i].y, healthPotions[i].getWidth(), healthPotions[i].getHeight())) {
              person.heal(25); // Potions heal 25 health
              healthPotions[i].setAlive(false);
          }
      }
      
      for (int i = 0; i < scrollCount; i++) {
          if (scrolls[i] != null && scrolls[i].isAlive() && person.collidesWith(scrolls[i].x, scrolls[i].y, scrolls[i].getWidth(), scrolls[i].getHeight())) {
              person.addScore(50); // Scrolls give 50 points
              scrolls[i].setAlive(false);
          }
      }
      
      // Golden Cudgel (only in level 1)
      if (goldenCudgel != null && goldenCudgel.isAlive() && person.collidesWith(goldenCudgel.x, goldenCudgel.y, goldenCudgel.getWidth(), goldenCudgel.getHeight())) {
          person.acquireGoldenCudgel();
          person.addScore(100); // Cudgel gives 100 points
          goldenCudgel.setAlive(false);
          cudgelPickedUp = true;
      }
      // Check if all scrolls are collected
      allScrollsCollected = true;
      for (int i = 0; i < scrollCount; i++) {
          if (scrolls[i] != null && scrolls[i].isAlive()) {
              allScrollsCollected = false;
              break;
          }
      }
      
  }
  
  
  private void createEnemiesForLevel(int level) {
    int totalEnemies = getTotalEnemiesForLevel(level);
      
    switch (level) {
        case 1:
            // 8 slow enemies only
            for (int i = 0; i < 8; i++) {
                spawnEnemyAt(i, "slow");
            }
            enemyCount = 8;
            break;

        case 2:
            // 12 enemies: 6 slow, 6 fast
            for (int i = 0; i < 6; i++) {
                spawnEnemyAt(i, "slow");
            }
            for (int i = 6; i < 12; i++) {
                spawnEnemyAt(i, "fast");
            }
            enemyCount = 12;
            break;

        case 3:
            // 15 enemies: 7 slow, 8 ranged
            for (int i = 0; i < 7; i++) {
                spawnEnemyAt(i, "slow");
            }
            for (int i = 7; i < 15; i++) {
                spawnEnemyAt(i, "ranged");
            }
            enemyCount = 15;
            break;

        case 4:
            // 18 enemies: 6 slow, 6 fast, 6 ranged
            for (int i = 0; i < 6; i++) {
                spawnEnemyAt(i, "slow");
            }
            for (int i = 6; i < 12; i++) {
                spawnEnemyAt(i, "fast");
            }
            for (int i = 12; i < 18; i++) {
                spawnEnemyAt(i, "ranged");
            }
            enemyCount = 18;
            break;

        case 5:
            // 20 enemies: 7 slow, 7 fast, 6 ranged
            for (int i = 0; i < 7; i++) {
                spawnEnemyAt(i, "slow");
            }
            for (int i = 7; i < 14; i++) {
                spawnEnemyAt(i, "fast");
            }
            for (int i = 14; i < 20; i++) {
                spawnEnemyAt(i, "ranged");
            }
            enemyCount = 20;
            break;
    }
  }
  
  private void handleEnemySpawning() {
    // Only spawn if we haven't reached the max extra enemies
    if (enemiesDefeated < getTotalEnemiesForLevel(currentLevel) + maxExtraEnemies) {
        // Check if it's time to spawn a new enemy
        if (spawnTimer <= 0) {
            for (int i = 0; i < enemyCount; i++) {
                if (enemies[i] == null || !enemies[i].isAlive()) {
                    spawnEnemyAt(i);
                    break;
                }
            }
        } else {
            spawnTimer--;
        }
    }
  }
  
  private void spawnEnemyAt(int index) {
    // Get safe spawn position (not on player)
    int[] spawnPos = getSafeSpawnPosition();
    if (spawnPos == null)
        return;
    
    String spawnType = getNextSpawnType();
    
    switch (spawnType) {
        case "slow":
            enemies[index] = new Person(this, spawnPos[0], spawnPos[1], "images/slowdemon.png", "slow", 80);
            break;
        case "fast":
            enemies[index] = new Person(this, spawnPos[0], spawnPos[1], "images/fastdemon.png", "fast", 40);
            break;
        case "ranged":
            enemies[index] = new Person(this, spawnPos[0], spawnPos[1], "images/rangeddemon.png", "ranged", 60);
            break;
    }
  }
  
  // Spawns an enemy based on index in array
  private void spawnEnemyAt(int index, String type) {
    int[] pos = getSafeSpawnPosition();
    // Position if no safe location is available
    if (pos == null) {
        pos = new int[]{100 + index * 50, 100 + index * 30};
    }
    
    // Create enemy based on type
    switch (type) {
        case "slow":
            enemies[index] = new Person(this, pos[0], pos[1], "images/slowdemon.png", "slow", 80);
            break;
        case "fast":
            enemies[index] = new Person(this, pos[0], pos[1], "images/fastdemon.png", "fast", 40);
            break;
        case "ranged":
            enemies[index] = new Person(this, pos[0], pos[1], "images/rangeddemon.png", "ranged", 60);
            break;
    }
  }
  
  
  private int getTotalEnemiesForLevel(int level) {
    switch (level) {
        case 1:
            return 8; // 8 enemies
        case 2:
            return 12; // 12 enemies
        case 3:
            return 15; // 15 enemies
        case 4:
            return 18; // 18 enemies
        case 5:
            return 20; // 20 enemies
        default:
            return 8;
    }
  }
  
  // Creates the amount of items for each level
  private void createItemsForLevel(int level) {
    int potions = 0;
    int numScrolls = 0;
    
    switch (level) {
        case 1:
            potions = 2;
            numScrolls = 7;
            break;
        case 2:
            potions = 4;
            numScrolls = 10;
            break;
        case 3:
            potions = 5;
            numScrolls = 13;
            break;
        case 4:
            potions = 7;
            numScrolls = 15;
            break;
        case 5:
            potions = 8;
            numScrolls = 18;
            break;
    }
    // Create potions
    for (int i = 0; i < potions; i++) {
        int[] pos = getSafeSpawnPosition();
        if (pos == null) {
            pos = new int[]{200 + i * 100, 200 + i * 50};
        }
        healthPotions[potionCount++] = new Person(this, pos[0], pos[1], "images/health.png", "health", 1);
    }
    
    // Create scrolls
    for (int i = 0; i < numScrolls; i++) {
        int[] pos = getSafeSpawnPosition();
        if (pos == null) {
            pos = new int[]{300 + i * 60, 300 + i * 40};
        }
        scrolls[scrollCount++] = new Person(this, pos[0], pos[1], "images/scroll.png", "scroll", 1);
    }
  }
  
  // Wall layout
  private void createWallLayout(int level) {
    switch (level) {
        case 1:
            walls[wallCount++] = new Wall(this, 200, 200, 100, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 400, 20, 100, "images/wall.png");
            walls[wallCount++] = new Wall(this, 600, 300, 80, 80, "images/wall.png");
            walls[wallCount++] = new Wall(this, 800, 500, 100, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 300, 700, 20, 100, "images/wall.png");
            break;
        case 2:
            walls[wallCount++] = new Wall(this, 150, 150, 100, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 350, 250, 20, 150, "images/wall.png");
            walls[wallCount++] = new Wall(this, 550, 350, 80, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 750, 200, 20, 120, "images/wall.png");
            walls[wallCount++] = new Wall(this, 200, 600, 150, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 500, 600, 20, 100, "images/wall.png");
            walls[wallCount++] = new Wall(this, 700, 500, 100, 20, "images/wall.png");
            break;
        case 3:
            walls[wallCount++] = new Wall(this, 100, 100, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 100, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 200, 300, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 100, 500, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 300, 500, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 600, 200, 20, 300, "images/wall.png");
            walls[wallCount++] = new Wall(this, 700, 400, 150, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 800, 100, 20, 200, "images/wall.png");
            break;
        case 4:
            walls[wallCount++] = new Wall(this, 50, 50, 300, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 50, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 150, 250, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 50, 400, 20, 300, "images/wall.png");
            walls[wallCount++] = new Wall(this, 200, 400, 150, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 300, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 500, 200, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 700, 300, 20, 300, "images/wall.png");
            walls[wallCount++] = new Wall(this, 800, 100, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 600, 500, 150, 20, "images/wall.png");
        case 5:
            walls[wallCount++] = new Wall(this, 500, 100, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 300, 800, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 800, 700, 150, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 50, 50, 300, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 50, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 150, 250, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 50, 400, 20, 300, "images/wall.png");
            walls[wallCount++] = new Wall(this, 200, 400, 150, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 400, 300, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 500, 200, 200, 20, "images/wall.png");
            walls[wallCount++] = new Wall(this, 700, 300, 20, 300, "images/wall.png");
            walls[wallCount++] = new Wall(this, 800, 100, 20, 200, "images/wall.png");
            walls[wallCount++] = new Wall(this, 600, 500, 150, 20, "images/wall.png");
            break;
    }
  }
  
  private void loadStoryImages() {
    try {
        // Intro story (8 pages)
        storypage1 = loadImage("images/story1.png");
        storypage2 = loadImage("images/story2.png");
        storypage3 = loadImage("images/story3.png");
        storypage4 = loadImage("images/story4.png");
        storypage5 = loadImage("images/story5.png");
        storypage6 = loadImage("images/story6.png");
        storypage7 = loadImage("images/story7.png");
        storypage8 = loadImage("images/story8.png");

        // Ending 1 (7 pages)
        ending1page1 = loadImage("images/ending1_1.png");
        ending1page2 = loadImage("images/ending1_2.png");
        ending1page3 = loadImage("images/ending1_3.png");
        ending1page4 = loadImage("images/ending1_4.png");
        ending1page5 = loadImage("images/ending1_5.png");
        ending1page6 = loadImage("images/ending1_6.png");
        ending1page7 = loadImage("images/ending1_7.png");

        // Ending 2 (9 pages)
        ending2page1 = loadImage("images/ending2_1.png");
        ending2page2 = loadImage("images/ending2_2.png");
        ending2page3 = loadImage("images/ending2_3.png");
        ending2page4 = loadImage("images/ending2_4.png");
        ending2page5 = loadImage("images/ending2_5.png");
        ending2page6 = loadImage("images/ending2_6.png");
        ending2page7 = loadImage("images/ending2_7.png");
        ending2page8 = loadImage("images/ending2_8.png");
        ending2page9 = loadImage("images/ending2_9.png");
    } catch (Exception e) {
        System.err.println("Error loading images: " + e.getMessage());
    }
  }
  
  // Finds a safe spawn point
  private int[] getSafeSpawnPosition() {
    for (int attempts = 0; attempts < 10; attempts++) {
        int x = (int) random(50, WORLD_WIDTH - 50);
        int y = (int) random(50, WORLD_HEIGHT - 50);
        
        // Check distance from player
        if (dist(x, y, person.x, person.y) > 150) {
            return new int[]{x, y};
        }
    }
    return null;
  }
  
  // Switches between different demon types based on level
  private String getNextSpawnType() {
    String type = nextSpawnType;
      
    switch (currentLevel) {
        case 1:
            nextSpawnType = "slow";
            break;
        case 2:
            if (nextSpawnType.equals("slow")) {
                nextSpawnType = "fast";
            } else if (nextSpawnType.equals("fast")) {
                nextSpawnType = "slow";
            }
            break;
        case 3:
            if (nextSpawnType.equals("slow")) {
                nextSpawnType = "ranged";
            } else if (nextSpawnType.equals("ranged")) {
                nextSpawnType = "slow";
            }
            break;
        case 4:
        case 5:
            // Cycle through all three types
            if (nextSpawnType.equals("slow")) {
                nextSpawnType = "fast";
            } else if (nextSpawnType.equals("fast")) {
                nextSpawnType = "ranged";
            } else {
                nextSpawnType = "slow";
            }
            break;
      }
    return type;
  }
  
  
  private void checkLevelCompletion() {
    // Check if all scrolls are collected
    if (allScrollsCollected) {
        // If cudgel wasn't picked up in level 1 and we're past level 1, it's too late
        if (currentLevel == 1 && !cudgelPickedUp && goldenCudgel == null) {
            goldenCudgel = null;
        } 
        // Next level
        currentLevel++;
        if (currentLevel > 5) {
            highScoreManager.saveHighScore(person.getScore());
            if (cudgelPickedUp) {
                stage = 3; // Ending 1 (with cudgel)
            } else {
                stage = 4; // Ending 2 (without cudgel)
            }
            currentStoryPage = 0;
        } else {
            setupLevel(currentLevel);
        }
      }
  }
  
  // Keeps entity from going off screen
  private void keepInBounds(Person entity) {
    if (entity.getX() < 0) {
        entity.setX(0);
    }
    if (entity.getX() > WORLD_WIDTH - entity.getWidth()) {
        entity.setX(WORLD_WIDTH - entity.getWidth());
    }
    if (entity.getY() < 0) {
        entity.setY(0);
    }
    if (entity.getY() > WORLD_HEIGHT - entity.getHeight())
        entity.setY(WORLD_HEIGHT - entity.getHeight());
  }
  
  private void resetGame() {
    // Reset player
    person = new Person(this, 100, 100, "images/player.png", "player", 100);
    
    // Reset game variables
    currentLevel = 1;
    currentStoryPage = 0;
    enemiesDefeated = 0;
    cudgelPickedUp = false;
    allScrollsCollected = false;
    
    // Re-setup level 1
    setupLevel(1);
  }
  
  @Override
  public void keyPressed() {
    switch (stage) {
        case 0: // Menu
            if (keyCode == ENTER) {
                stage = 1; // Go to intro story
                currentStoryPage = 0;
            }
            break;

        case 2: // Game
            // Attack with spacebar (only with cudgel)
            if (keyCode == ENTER && person.hasGoldenCudgel() && person.canAttack()) {
                person.attack();
                performAttack();
            }
            break;
    }
  }
  
  private void performAttack() {
    // Cudgel does 25 damage per hit
    int cudgelDamage = 25;
      
    // Check all enemies in range
    for (int i = 0; i < enemyCount; i++) {
        if (enemies[i] != null && enemies[i].isAlive() && person.isCollidingWith(enemies[i])) {
            enemies[i].takeDamage(cudgelDamage);

            // Give points based on enemy type
            switch (enemies[i].getType()) {
                case "slow":
                    person.addScore(15);
                    break;
                case "fast":
                    person.addScore(20);
                    break;
                case "ranged":
                    person.addScore(20);
                    break;
            }
            if (!enemies[i].isAlive()) {
                enemiesDefeated++;
                spawnTimer = spawnDelay; // Reset spawn timer
            }
        }
    }
  }
  public void mousePressed() {
    switch (stage) {
        case 1: // Intro Story (8 pages)
            currentStoryPage++;
            if (currentStoryPage >= 8) {
                stage = 2; // Start game
                currentStoryPage = 0;
            }
            break;

        case 3: // Ending 1 (7 pages)
            currentStoryPage++;
            if (currentStoryPage >= 7) {
                stage = 0; // Back to menu
                resetGame();
            }
            break;

        case 4: // Ending 2 (9 pages)
            currentStoryPage++;
            if (currentStoryPage >= 9) {
                stage = 0; // Back to menu
                resetGame();
            }
            
    }
  }
}//end class

