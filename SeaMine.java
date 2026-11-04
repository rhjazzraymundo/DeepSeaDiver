import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SeaMine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

public class SeaMine extends Actor
{
    private boolean exploding = false;
    private int explosionTimer = 0;

    public void act()
    {
        if (exploding)
        {
            explosionTimer--;
            if (explosionTimer <= 0)
            {
                OceanWorld world = (OceanWorld) getWorld();
                world.removeObject(this);
                world.respawnMine();
            }
        }
    }

    public void explode()
    {
        if (!exploding)
        {
            exploding = true;
            explosionTimer = 15; // roughly a quarter second
            setImage("mine_explosion.png");
            Greenfoot.playSound("mine_explosion1.mp3");
        }
    }

    public boolean isExploding()
    {
        return exploding;
    }
}
