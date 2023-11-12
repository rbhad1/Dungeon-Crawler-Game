package com.example.cs2340c_team28.models.observers;

import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.List;

public class CollisionManager implements EnemyCollisionObserver {
    private final Player player;
    private final Game game;
    private List<Enemy> enemies;
    private long playerInvincibilityStartTime;
    private static final long INVINCIBILITY_DURATION = 1000;

    private static final double COLLISION_DISTANCE = 1.0;

    public CollisionManager(List<Enemy> enemies) {
        this.player = Player.getInstance();
        this.game = Game.getInstance();
        this.enemies = enemies;
        playerInvincibilityStartTime = 0;
    }

    public void checkCollisions() {
        for (Enemy enemy : enemies) {
            if (checkCollision(enemy)) {
                collisionOccurred();
            }
        }
    }

    private boolean checkCollision(Enemy enemy) {
        Position playerPosition = player.getPosition();
        Position enemyPosition = enemy.getPosition();

        if (playerPosition == null || enemyPosition == null) {
            return false;
        }

        // Calculate the distance between the enemy and the player
        double distance = calculateDistance(playerPosition, enemyPosition);

        return distance < COLLISION_DISTANCE;
    }

    private double calculateDistance(Position pos1, Position pos2) {
        int deltaX = pos2.getX() - pos1.getX();
        int deltaY = pos2.getY() - pos1.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    @Override
    public void collisionOccurred() {
        if (!isPlayerInvincible()) {
            int damage = getDamageBasedOnDifficulty();
            if (player.getHp() - damage <= 0) {
                player.setHp(0);
                return;
            }
            player.setHp(player.getHp() - damage);

            setPlayerInvincible();
            setInvincibilityStartTime(System.currentTimeMillis());
        }
    }

    public boolean isPlayerInvincible() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - getInvincibilityStartTime();

        return elapsedTime < INVINCIBILITY_DURATION;
    }

    public void setPlayerInvincible() {
        playerInvincibilityStartTime = System.currentTimeMillis();
    }

    private long getInvincibilityStartTime() {
        return playerInvincibilityStartTime;
    }

    private void setInvincibilityStartTime(long startTime) {
        this.playerInvincibilityStartTime = startTime;
    }

    private int getDamageBasedOnDifficulty() {
        Difficulty difficulty = game.getDifficulty();
        switch (difficulty) {
        case EASY: return 5;
        case MEDIUM: return 10;
        case HARD: return 15;
        default: return 0;
        }
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
}
