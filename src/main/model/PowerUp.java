package model;

import java.util.Random;

public class PowerUp {

    private int speedBoost = 0;
    private int damageBoost = 0;
    private int healthBoost = 0;
    private int projectileNumberBoost = 0;

    // EFFCECTS: creates a powerup instance based on specifications
    public PowerUp(boolean speed, boolean damage,
                   boolean health, boolean projectile) {
        if (speed) {
            this.speedBoost = 1;
        }
        if (damage) {
            this.damageBoost = 1;
        }
        if (projectile) {
            this.projectileNumberBoost = 1;
        }
        if (health) {
            this.healthBoost = 10;
        }
    }

    // EFFECTS: return a randomized powerup
    public static PowerUp generatePowerUp() {
        Random rand = new Random();
        boolean speedRandom = rand.nextBoolean();
        boolean damageRandom = rand.nextBoolean();
        boolean healthRandom = rand.nextBoolean();
        boolean projectileRandom = rand.nextBoolean();
        return new PowerUp(speedRandom, damageRandom, healthRandom, projectileRandom);
    }

    // EFFECTS: returns powerup speedboost
    public int getSpeedBoost() {
        return this.speedBoost;
    }

    // EFFECTS: returns powerup damageboost
    public int getDamageBoost() {
        return this.damageBoost;
    }

    // EFFECTS: returns powerup healthboost
    public int getHealthBoost() {
        return this.healthBoost;
    }

    // EFFECTS: returns powerup projectileboost
    public int getProjectileNumberBoost() {
        return this.projectileNumberBoost;
    }
}
