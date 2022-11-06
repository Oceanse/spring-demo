package com.demo.dependency_inject.demo3.weapon;

public class Gun implements Weapon {
    @Override
    public void attack() {
        System.out.println("gun attack.....");
    }
}
