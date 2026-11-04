import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Submarine extends Actor
{
    private int speed = 3;
    private int turnSpeed = 4;
    private String leftKey, rightKey, upKey, downKey;
    private int myScore = 0;
    private int myHealth = 100;

    private boolean isInvincible = false;
    private int invincibilityTimer = 0;

    private boolean isDead = false;
    private int respawnTimer = 0;
    private int spawnX, spawnY;

    public Submarine(String leftKey, String rightKey, String upKey, String downKey, String imageFile)
    {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.downKey = downKey;
        setImage(imageFile);
    }

    public void setSpawnPoint(int x, int y)
    {
        this.spawnX = x;
        this.spawnY = y;
    }

    public void act()
    {
        if (isDead)
        {
            handleRespawn();
            return;
        }

        checkKeys();
        keepInBounds();
        checkCoinCollision();
        checkHeartCollision();
        checkMineCollision();
        checkJellyfishCollision();
        checkSharkCollision();
        updateInvincibility();
    }

    private void checkKeys()
    {
        if (Greenfoot.isKeyDown(leftKey)) turn(-turnSpeed);
        if (Greenfoot.isKeyDown(rightKey)) turn(turnSpeed);
        if (Greenfoot.isKeyDown(upKey)) move(speed);
        if (Greenfoot.isKeyDown(downKey)) move(-speed);
    }

    private void keepInBounds()
    {
        int x = getX();
        int y = getY();
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();

        if (x < 0) setLocation(0, y);
        if (x > worldWidth - 1) setLocation(worldWidth - 1, y);
        if (y < 0) setLocation(x, 0);
        if (y > worldHeight - 1) setLocation(x, worldHeight - 1);
    }

    private void checkCoinCollision()
    {
        if (isTouching(GoldCoin.class))
        {
            removeTouching(GoldCoin.class);
            Greenfoot.playSound("coin_collect.mp3");
            myScore++;
            OceanWorld world = (OceanWorld) getWorld();
            world.updateHud();
            world.respawnCoin();
        }
    }

    private void checkHeartCollision()
    {
        if (isTouching(HeartEmblem.class))
        {
            removeTouching(HeartEmblem.class);
            heal(10); // Adds 10 HP
            OceanWorld world = (OceanWorld) getWorld();
            world.updateHud();
            world.respawnHeart();
        }
    }

    private void heal(int amount)
    {
        myHealth = Math.min(100, myHealth + amount);
    }

    private void checkMineCollision()
    {
        if (!isInvincible && isTouching(SeaMine.class))
        {
            SeaMine mine = (SeaMine) getOneIntersectingObject(SeaMine.class);
            if (mine != null && !mine.isExploding())
            {
                mine.explode();
                takeDamage(20);
            }
        }
    }

    private void checkJellyfishCollision()
    {
        if (!isInvincible && isTouching(Jellyfish.class))
        {
            Greenfoot.playSound("jellyfish_sting.mp3");
            takeDamage(15);
        }
    }

    private void checkSharkCollision()
    {
        if (!isInvincible && isTouching(Shark.class))
        {
            Greenfoot.playSound("shark_bite.mp3");
            takeDamage(15);
        }
    }

    private void takeDamage(int amount)
    {
        myHealth -= amount;
        if (myHealth < 0) myHealth = 0;

        OceanWorld world = (OceanWorld) getWorld();
        world.updateHud();

        isInvincible = true;
        invincibilityTimer = 30;
        getImage().setTransparency(120);

        if (myHealth <= 0)
        {
            die();
        }
    }

    private void die()
    {
        isDead = true;
        respawnTimer = 180; // 3 seconds at 60 FPS
        getImage().setTransparency(0);
        setLocation(-200, -200);
    }

    private void handleRespawn()
    {
        respawnTimer--;
        OceanWorld world = (OceanWorld) getWorld();
        world.updateHud();

        if (respawnTimer <= 0)
        {
            isDead = false;
            myHealth = 100;
            getImage().setTransparency(255);
            setLocation(spawnX, spawnY);
            setRotation(0);

            isInvincible = true;
            invincibilityTimer = 60;

            world.updateHud();
        }
    }

    private void updateInvincibility()
    {
        if (isInvincible)
        {
            invincibilityTimer--;
            if (invincibilityTimer <= 0)
            {
                isInvincible = false;
                getImage().setTransparency(255);
            }
        }
    }

    public int getScore() { return myScore; }
    public int getHealth() { return myHealth; }
    public boolean isDead() { return isDead; }
    public int getRespawnSeconds() { return (respawnTimer / 60) + 1; }
}