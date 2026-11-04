import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class OceanWorld extends World
{
    private Submarine player1, player2;
    private int timeLeft;
    private GreenfootSound backgroundMusic;
    

    // Default constructor for Greenfoot editor testing
    public OceanWorld()
    {
        this(1); // Defaults to 1 minute
    }

    // Constructor receiving chosen minutes from StartWorld
    public OceanWorld(int minutes)
    {
        super(1200, 650, 1);
        this.timeLeft = minutes * 60 * 60;
        prepare();
        
        setBackground("oceanFloor.png");
        backgroundMusic = new GreenfootSound("game_bg.mp3");
        backgroundMusic.playLoop();
    }

    private void prepare()
    {
        player1 = new Submarine("a", "d", "w", "s", "submarine_green.png");
        player2 = new Submarine("left", "right", "up", "down", "submarine_orange.png");

        player1.setSpawnPoint(200, 250);
        player2.setSpawnPoint(600, 250);

        addObject(player1, 200, 250);
        addObject(player2, 600, 250);

        // Spawn Gold Coins
        int coinsToPlace = 5;
        for (int i = 0; i < coinsToPlace; i++)
        {
            addObject(new GoldCoin(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

        // Spawn 5 Heart Emblems
        int heartsToPlace = 5;
        for (int i = 0; i < heartsToPlace; i++)
        {
            addObject(new HeartEmblem(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

        // Spawn Sea Mines
        int minesToPlace = 4;
        for (int i = 0; i < minesToPlace; i++)
        {
            addObject(new SeaMine(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

        // Spawn Jellyfish
        addObject(new Jellyfish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        addObject(new Jellyfish(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));

        // Spawn Sharks
        addObject(new Shark(3), 200, 100);
        addObject(new Shark(4), 600, 400);

        updateHud();
    }

    public void act()
    {
        timeLeft--;
        int secondsLeft = timeLeft / 60;
        showText("Time: " + secondsLeft + "s", 400, 20);

        if (timeLeft <= 0)
        {
            endGameByScore();
        }
    }

    public void updateHud()
    {
        if (player1.isDead()) {
            showText("Green HP: 0%  Coins: " + player1.getScore(), 150, 20);
            showText("Respawning in: " + player1.getRespawnSeconds() + "s", 150, 45);
        } else {
            showText("Green HP: " + player1.getHealth() + "%  Coins: " + player1.getScore(), 150, 20);
            showText("", 150, 45);
        }

        if (player2.isDead()) {
            showText("Orange HP: 0%  Coins: " + player2.getScore(), 650, 20);
            showText("Respawning in: " + player2.getRespawnSeconds() + "s", 650, 45);
        } else {
            showText("Orange HP: " + player2.getHealth() + "%  Coins: " + player2.getScore(), 650, 20);
            showText("", 650, 45);
        }
    }

    public void respawnCoin()
    {
        addObject(new GoldCoin(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }

    public void respawnHeart()
    {
        addObject(new HeartEmblem(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }

    public void respawnMine()
    {
        addObject(new SeaMine(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }

    private void endGameByScore()
    {
        backgroundMusic.stop();
        int score1 = player1.getScore();
        int score2 = player2.getScore();

        if (score1 > score2) Greenfoot.setWorld(new WinWorld(1, score1, score2));
        else if (score2 > score1) Greenfoot.setWorld(new WinWorld(2, score2, score1));
        else Greenfoot.setWorld(new WinWorld(0, score1, score2));
    }
}