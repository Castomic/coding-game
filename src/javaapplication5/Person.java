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
public class Person {
    public int x, y;
    private int width, height;
    private PImage image;
    private PApplet app;
    private int health;
    private int maxHealth;
    private int score;
    private boolean hasGoldenCudgel;
    private boolean isAlive;
    private int attackCooldown;
    private String type; // player, slow, fast, ranged, cudgel, health, scroll
    private static final int BASE_HEALTH = 100;
    private static final int ATTACK_COOLDOWN_MAX = 30;
    private static final int CUDGEL_DAMAGE = 25; // Cudgel damage per hit
    private static final float CUDGEL_RANGE = 60; // Attack range
    
    // Constructor
    public Person(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.maxHealth = BASE_HEALTH;
        this.health = BASE_HEALTH;
        this.score = 0;
        this.hasGoldenCudgel = false;
        this.isAlive = true;
        this.attackCooldown = 0;
     
        try {
             this.image = app.loadImage(imagePath);
             if (this.image != null) {
                 this.width = this.image.width;
                 this.height = this.image.height;
             }
        } catch (Exception e) {
            this.width = 40;
            this.height = 40;
        }
    }
    
    // Overloaded Constructor
    public Person(PApplet p, int x, int y, String imagePath, String type, int health) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.type = type;
        this.maxHealth = health;
        this.health = health;
        this.score = 0;
        this.hasGoldenCudgel = false;
        this.isAlive = true;
        this.attackCooldown = 0;
        
        try {
             this.image = app.loadImage(imagePath);
             if (this.image != null) {
                 this.width = this.image.width;
                 this.height = this.image.height;
             }
        } catch (Exception e) {
            this.width = 40;
            this.height = 40;
        }
    }
    
    // Constructor
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    // Overloaded Constructor
    public void move(int dx, int dy, int worldWidth, int worldHeight) {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
        }
        if (x > worldWidth - width) {
            x = worldWidth - width;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > worldHeight - height)
            y = worldHeight - height;
    }
    
    
    public void draw() {
        if (image != null) {
            app.image(image, x, y);
        } else {
             switch(type) {
                case "slow":
                    app.fill(100, 100, 100); // Gray
                    break;
                case "fast":
                    app.fill(255, 100, 0); // Orange
                    break;
                case "ranged":
                    app.fill(128, 0, 128); // Purple
                    break;
                case "health":  
                    app.fill(255, 0, 0); // Red
                    break;
                case "scroll":
                    app.fill(255, 255, 224); // Light Yellow
                    break;
                case "cudgel":    
                    app.fill(255, 215, 0); // Gold
                    break;
                default:    
                    app.fill(255, 255, 0); // Player
             }
             app.rect(x, y, width, height);
        }
        drawHealthBar();
    }
    
    public void drawHealthBar() {
        if (!type.equals("player") && !type.equals("slow") && !type.equals("fast") && !type.equals("ranged")) 
            return;
        
        int barWidth = 40;
        int barHeight = 4;
        float healthPercent = health * 1f / maxHealth;
        
        app.fill(100);
        app.rect(x + width / 2 - barWidth / 2, y - 15, barWidth, barHeight);
        
        app.fill(255, 0, 0);
        app.rect(x + width / 2 - barWidth / 2, y - 15, barWidth * healthPercent, barHeight);
    }
    public boolean isCollidingWith(Person other) {
        int centerX = x+(width/2);
        int centerY = y+(height/2);
        int otherCenterX = other.x+(other.width/2);
        int otherCenterY = other.y+(other.width/2);
        float d = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);
        return d < 32;
    }
    
    public boolean collidesWith(float otherX, float otherY, float otherWidth, float otherHeight) {
        if (!isAlive)
            return false;
        
        float centerX = this.x + this.width / 2;
        float centerY = this.y + this.height / 2;
        float otherCenterX = otherX + otherWidth / 2;
        float otherCenterY = otherY + otherHeight / 2;
        
        float distance = (float) Math.sqrt(Math.pow(centerX - otherCenterX, 2) + Math.pow(centerY - otherCenterY, 2));
        
        float minDistance = (this.width + otherWidth) / 2;

        return distance < minDistance;
    }
    
    public boolean isClicked(int mouseX, int mouseY) {
        int centerX = x + (width / 2);
        int centerY = y + (height / 2);
        float d = app.dist(mouseX, mouseY, centerX, centerY);
        return d < 16;
    }
    
    public void takeDamage(int damage) {
        if (!isAlive) 
            return;
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
            
    }
    public void takeDamageFromEnemy(String enemyType) {
        if (!isAlive || !type.equals("player")) return;

        int damage = 0;   
        switch(enemyType) {
            case "slow":
                damage = 15;
                break;
            case "fast":
                damage = 15;
                break;
            case "ranged":
                damage = 20;
                break;
            default:
                damage = 10;        
        }
        health = health - damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }
    
    public void heal(int amount) {
        if (!isAlive)
            return;
        
        health = health + amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    
    public void addScore(int points) {
        this.score += points;
    }
    
    public void attack() {
        if (hasGoldenCudgel && attackCooldown == 0) {
            attackCooldown = ATTACK_COOLDOWN_MAX;
        }
    }
    
    public boolean canAttack() {
        return hasGoldenCudgel && attackCooldown == 0;
    }
    
    public void acquireGoldenCudgel() {
        this.hasGoldenCudgel = true;
    }

    public boolean canHit(Person enemy) {
        if (!hasGoldenCudgel || !isAlive || !enemy.isAlive()) 
            return false;
        float playerCenterX = this.x + this.width / 2;
        float playerCenterY = this.y + this.height / 2;
        float enemyCenterX = enemy.x + enemy.width / 2;
        float enemyCenterY = enemy.y + enemy.height / 2;
        float distance = PApplet.dist(playerCenterX, playerCenterY, enemyCenterX, enemyCenterY);
        
        return distance <= CUDGEL_RANGE;
    }
    
    public void drawWeapon() {
        if (!hasGoldenCudgel) 
            return;
        
        // Draw a golden line representing the cudgel
        app.stroke(255, 215, 0);
        app.strokeWeight(5);
        app.line(x + width, y + height/2, x + width + 40, y + height/2);
    }
    
    public void drawAttackEffect() {
        if (!hasGoldenCudgel || !canAttack()) return;
        
        // Draw a golden circle around player showing attack range
        app.noFill();
        app.stroke(255, 215, 0, 100);
        app.ellipse(x + width/2, y + height/2, CUDGEL_RANGE * 2, CUDGEL_RANGE * 2);
    }
    
    public void updateAI(float playerX, float playerY) {
        if (!isAlive || type.equals("player"))
            return;
        
        switch (type) {
            case "slow":
                // Slow demons move directly toward player
                float dx = playerX - x;
                float dy = playerY - y;
                float distance = (float) Math.sqrt(dx * dx + dy * dy);
                
                if (distance > 0) {
                    dx = (dx / distance) * 1.0f;
                    dy = (dy / distance) * 1.0f;
                    x += dx;
                    y += dy;
                }
                break;
            case "fast":
                if (x < playerX) {
                    x += 1.5f;
                }
                if (x > playerX) {
                    x -= 1.5f;
                }
                if (y < playerY) {
                    y += 1.5f;
                }
                if (y > playerY)
                    y -= 1.5f;
                break;
            case "ranged":
                // Ranged demons keep distance
                float dist = app.dist(x, y, playerX, playerY);
                if (dist < 100) {
                    // Move away if too close
                    if (x < playerX) {
                        x -= 1.5f;
                    }
                    if (x > playerX) {
                        x += 1.5f;
                    }
                    if (y < playerY) {
                        y -= 1.5f;
                    }
                    if (y > playerY)
                        y += 1.5f;
                } else if (dist > 200) {
                    // Move closer if too far
                    if (x < playerX) {
                        x += 1.5f;
                    }
                    if (x > playerX) {
                        x -= 1.5f;
                    }
                    if (y < playerY) {
                        y += 1.5f;
                    }
                    if (y > playerY)
                        y -= 1.5f;
                }
                break;
        }
    }
    
    public void update() {
        if (attackCooldown > 0) {
            attackCooldown--;
        }
    }
    // Getter methods
    
    public boolean hasGoldenCudgel() {
        return hasGoldenCudgel;
    }

    public boolean isAlive() {
        return isAlive;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getWeaponDamage() {
        return CUDGEL_DAMAGE;
    }
    
    public float getWeaponRange() {
        return CUDGEL_RANGE;
    }
    
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getScore() {
        return score;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public String getType() {
        return type;
    }
    
    // Setter methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
    
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}

