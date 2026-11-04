import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jellyfish here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Jellyfish extends Actor
{
    public void act()
{
    setLocation(getX() + Greenfoot.getRandomNumber(3) - 1,
                getY() + Greenfoot.getRandomNumber(3) - 1);
}
}
