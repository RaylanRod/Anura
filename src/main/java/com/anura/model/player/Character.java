package com.anura.model.player;

public class Character {
    // fields and attributes
    private String name;
    private int maxHealth;
    private int currentHealth;

    public Character(){}

    public Character(String name) {
        this.name = name;
    }

    public Character(String name, int hp){
        super();
        this.maxHealth = hp;
    }

    // methods
    public void talk(){

    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public String toString(){
        return this.name;
    }
}