package learnGame.Main;

import learnGame.Entity.Entity;
import learnGame.Entity.Player;
import learnGame.Tile.TileManager;
import learnGame.object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //screen setting
    private int originalTileSize = 16;//16x16 tile semua aset karakter berukuran 16 px
    private int scale = 3;//memperbesar aset 3x
    private int tileSize = originalTileSize * scale;//48x48
    private int maxScreenCol = 16;//satu layar cuma bisa sebanyak 16 tile kolom
    private int maxScreenRow = 12;//satu layar cuma bisa sebayak 12 tile baris
    private int screenWidth = tileSize * maxScreenCol;//768 px
    private int screenHeight = tileSize * maxScreenRow;//576 px

    //world setting
    private int maxWorldCol = 50, maxWorldRow = 50;

    //fps
    private int fps = 60;

    //system
    TileManager tileManager = new TileManager(this);
    KeyHandler keyhandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    CollisionChecker cChecker = new CollisionChecker(this);
    AssetSetter aSetter = new AssetSetter(this);
    UI ui = new UI(this);
    Thread gameTherad;

    //entity and obj
    Player player = new Player(this,keyhandler);
    SuperObject obj[] = new SuperObject[10];
    Entity npc[] = new Entity[10];

    //game state
    private int gameState, playState = 1, pauseState = 2;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyhandler);
        this.setFocusable(true);
    }
    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        gameState = playState;
    }
    public void startGameThread(){
        gameTherad = new Thread(this);
        gameTherad.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;

        while (gameTherad != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                // update information seperti posisi karakter
                update();
                // draw screen
                repaint();
                delta--;
            }

        }
    }

    public void update(){
        if(gameState == playState){
            //player
            player.update();
            //npc
            for(int i = 0;i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
        }


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //tile
        tileManager.draw(g2);

        //obj
        for(int i = 0;i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //npc
        for(int i = 0; i < obj.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }

        //player
        player.draw(g2);
        //UI
        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getcChecker() {
        return cChecker;
    }

    public SuperObject[] getObj() {
        return obj;
    }

    public UI getUi() {
        return ui;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public Entity[] getNpc() {
        return npc;
    }
}
