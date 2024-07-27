package com.demo.di.demo3;

import com.demo.di.demo3.weapon.Weapon;

public class Solider {
    Weapon weapon;

    public Solider() {
        System.out.println("Solider()  is called");
    }

    public void setWeapon(Weapon weapon) {
        System.out.println("setWeapon(Weapon weapon)  is called");
        this.weapon = weapon;
    }

    public void play() {
        weapon.attack();
    }
}
