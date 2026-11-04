import greenfoot.*;

public class WinWorld extends World
{
    private GreenfootSound victorySoundEffct;
    private GreenfootSound victorySound;
    
    public WinWorld(int winner, int winnerScore, int loserScore)
    {
        super(1200, 650, 1);
        setBackground("bgg.jpg");

        GreenfootImage bg = getBackground();

        bg.setFont(new Font("Arial", true, false, 48));
        String title;
        
        // Dynamically set text color based on winner
        if (winner == 0)
        {
            title = "IT'S A TIE!";
            bg.setColor(Color.WHITE);
        }
        else if (winner == 1)
        {
            title = "GREEN WINS!";
            bg.setColor(Color.GREEN);
        }
        else
        {
            title = "ORANGE WINS!";
            bg.setColor(Color.ORANGE);
        }
        
        drawCentered(bg, title, 260);

        // Draw score and restart prompt in white text
        bg.setColor(Color.WHITE);
        bg.setFont(new Font("Arial", false, false, 28));
        drawCentered(bg, "Score: " + winnerScore + " - " + loserScore, 330);
        drawCentered(bg, "Press R to play again", 400);
        
        victorySoundEffct = new GreenfootSound("win.mp3");
        victorySoundEffct.play();
        victorySound = new GreenfootSound("win_screen_bg.mp3");
        victorySound.play();
    }

    private void drawCentered(GreenfootImage image, String text, int y)
    {
        GreenfootImage textImage = new GreenfootImage(text, image.getFont().getSize(), image.getColor(), new Color(0, 0, 0, 0));
        int x = (getWidth() - textImage.getWidth()) / 2;
        image.drawImage(textImage, x, y);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("r")) {
            victorySound.stop();
            Greenfoot.setWorld(new StartWorld()); // Returns to timer selection
        }
    }
}