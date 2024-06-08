package learnGame.Tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collisionOn;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }
}
