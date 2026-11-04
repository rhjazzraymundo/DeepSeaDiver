import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartWorld extends World
{
    public StartWorld()
    {
        super(1200, 650, 1);
        setBackground("STARTBG.jpg");
        showInstructions();
    }

    private void showInstructions()
    {
        showText("SELECT GAME DURATION", getWidth() / 2, 220);
        showText("Press 1 for 1 Minute", getWidth() / 2, 280);
        showText("Press 2 for 2 Minutes", getWidth() / 2, 320);
        showText("Press 3 for 3 Minutes", getWidth() / 2, 360);
        showText("Press 4 for 4 Minutes", getWidth() / 2, 400);
        showText("Press 5 for 5 Minutes", getWidth() / 2, 440);
    }

    public void act()
    {
        String key = Greenfoot.getKey(); // Captures key presses reliably
        if (key != null)
        {
            if (key.equals("1")) startMatch(1);
            else if (key.equals("2")) startMatch(2);
            else if (key.equals("3")) startMatch(3);
            else if (key.equals("4")) startMatch(4);
            else if (key.equals("5")) startMatch(5);
        }
    }

    private void startMatch(int minutes)
    {
        Greenfoot.setWorld(new OceanWorld(minutes));
    }
}