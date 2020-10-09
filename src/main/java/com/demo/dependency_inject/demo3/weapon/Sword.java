package com.demo.dependency_inject.demo3.weapon;

public class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("sword atttack.....");
    }
}
