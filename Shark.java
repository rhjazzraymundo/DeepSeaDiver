import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shark here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Shark extends Actor
{
    private int speed;
    private int direction = 1; // 1 = right, -1 = left
    private GreenfootImage rightImage;
    private GreenfootImage leftImage;

    public Shark(int speed)
    {
        this.speed = speed;
        rightImage = getImage();
        leftImage = new GreenfootImage(rightImage);
        leftImage.mirrorHorizontally();
    }

    public void act()
    {
        setLocation(getX() + (speed * direction), getY());
        checkBounds();
    }

    private void checkBounds()
    {
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1)
        {
            direction *= -1;
            updateImageDirection();
        }
    }

    private void updateImageDirection()
    {
        if (direction == 1)
        {
            setImage(rightImage);
        }
        else
        {
            setImage(leftImage);
        }
    }
}