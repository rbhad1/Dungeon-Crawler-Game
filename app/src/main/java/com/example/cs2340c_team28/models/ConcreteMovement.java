package com.example.cs2340c_team28.models;

public class ConcreteMovement implements MovementStrategy {
    public void moveUp() {
        int y = Player.getUniquePlayerInstance().getY();
        Player.getUniquePlayerInstance().setY(y + 32);
    }
    @Override
    public void moveDown() {
        int y = Player.getUniquePlayerInstance().getY();
        Player.getUniquePlayerInstance().setY(y - 32);
    }
    @Override
    public void moveLeft() {
        int x = Player.getUniquePlayerInstance().getX();
        Player.getUniquePlayerInstance().setX(x - 32);
    }
    @Override
    public void moveRight() {
        int x = Player.getUniquePlayerInstance().getX();
        Player.getUniquePlayerInstance().setX(x + 32);
    }
}
