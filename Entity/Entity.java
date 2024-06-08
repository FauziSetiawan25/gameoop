package learnGame.Entity;

import learnGame.Main.GamePanel;
import learnGame.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    private int worldX, worldY, speed;
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;
    private int spriteCounter = 0, spriteNum = 1;
    private Rectangle solidArea = new Rectangle(0,0,48,48);
    private int solidAreaDefaultX, solidAreaDefaultY;
    private boolean collision = false;
    private int actionLockCounter = 0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){

    }
    public void update(){
        setAction();
        setCollision(false);
        gp.getcChecker().checkTile(this);
        gp.getcChecker().checkObject(this, false);
        gp.getcChecker().checkPlayer(this);

        if(!isCollision()) {
            switch (getDirection()) {
                case "up":
                    setWorldY(getWorldY() - getSpeed());
                    break;
                case "down":
                    setWorldY(getWorldY() + getSpeed());
                    break;
                case "left":
                    setWorldX(getWorldX() - getSpeed());
                    break;
                case "right":
                    setWorldX(getWorldX() + getSpeed());
                    break;
            }
        }
        setSpriteCounter(getSpriteCounter() + getSpeed());
            if(getSpriteCounter() > 12){
                if(getSpriteNum() == 1){
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();


        if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
                && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()){
            switch (getDirection()){
                case ("up"):
                    if(getSpriteNum() == 1){
                        image = getUp1();
                    }else {
                        image = getUp2();
                    }
                    break;
                case ("down"):
                    if(getSpriteNum() == 1){
                        image = getDown1();
                    }else {
                        image = getDown2();
                    }
                    break;
                case ("left"):
                    if(getSpriteNum() == 1){
                        image = getLeft1();
                    }else {
                        image = getLeft2();
                    }
                    break;
                case ("right"):
                    if(getSpriteNum() == 1){
                        image = getRight1();
                    }else {
                        image = getRight2();
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY,gp.getTileSize(),gp.getTileSize(), null );
        }
    }
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage Image = null;

        try{
            Image = ImageIO.read(getClass().getResourceAsStream("/learnGame/Assets/"+imagePath+".png"));
            Image = uTool.scaleImage(Image, gp.getTileSize(), gp.getTileSize());
        }catch (IOException e){
            e.printStackTrace();
        }
        return Image;
    }
    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getActionLockCounter() {
        return actionLockCounter;
    }

    public void setActionLockCounter(int actionLockCounter) {
        this.actionLockCounter = actionLockCounter;
    }
}
