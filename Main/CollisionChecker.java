package learnGame.Main;

import learnGame.Entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x +entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBotWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBotRow = entityBotWorldY/gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()){
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.getTileSize();
                tileNum1 = gp.tileManager.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[entityRightCol][entityTopRow];
                if(gp.tileManager.getTile()[tileNum1].isCollisionOn() == true || gp.tileManager.getTile()[tileNum2].isCollisionOn() == true){
                    entity.setCollision(true);
                }
                break;
            case "down":
                entityBotRow = (entityBotWorldY + entity.getSpeed())/gp.getTileSize();
                tileNum1 = gp.tileManager.getMapTileNum()[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileManager.getMapTileNum()[entityRightCol][entityBotRow];
                if(gp.tileManager.getTile()[tileNum1].isCollisionOn() == true || gp.tileManager.getTile()[tileNum2].isCollisionOn() == true){
                    entity.setCollision(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.getTileSize();
                tileNum1 = gp.tileManager.getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[entityLeftCol][entityBotRow];
                if(gp.tileManager.getTile()[tileNum1].isCollisionOn() == true || gp.tileManager.getTile()[tileNum2].isCollisionOn() == true){
                    entity.setCollision(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.getTileSize();
                tileNum1 = gp.tileManager.getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.getMapTileNum()[entityRightCol][entityBotRow];
                if(gp.tileManager.getTile()[tileNum1].isCollisionOn() == true || gp.tileManager.getTile()[tileNum2].isCollisionOn() == true){
                    entity.setCollision(true);
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //get entity solid area pos
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                //get the obj solid area pos
                gp.obj[i].getSolidArea().x = gp.obj[i].getWorldX() + gp.obj[i].getSolidArea().x;
                gp.obj[i].getSolidArea().y = gp.obj[i].getWorldY() + gp.obj[i].getSolidArea().y;

                switch (entity.getDirection()){
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){
                                entity.setCollision(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){
                                entity.setCollision(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){
                                entity.setCollision(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.obj[i].getSolidArea())){
                            if(gp.obj[i].isCollision()){
                                entity.setCollision(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.obj[i].getSolidArea().x = gp.obj[i].getSolidAreaDefaultX();
                gp.obj[i].getSolidArea().y = gp.obj[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }
    //check npc or monster
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                //get entity solid area pos
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                //get the obj solid area pos
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                switch (entity.getDirection()){
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if(entity.getSolidArea().intersects(target[i].getSolidArea())){
                            entity.setCollision(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity){
        //get entity solid area pos
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        //get the obj solid area pos
        gp.player.getSolidArea().x = gp.player.getWorldX() + gp.player.getSolidArea().x;
        gp.player.getSolidArea().y = gp.player.getWorldY() + gp.player.getSolidArea().y;

        switch (entity.getDirection()){
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.player.getSolidArea())){
                    entity.setCollision(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.player.getSolidArea())){
                    entity.setCollision(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.player.getSolidArea())){
                    entity.setCollision(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if(entity.getSolidArea().intersects(gp.player.getSolidArea())){
                    entity.setCollision(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gp.player.getSolidArea().x = gp.player.getSolidAreaDefaultX();
        gp.player.getSolidArea().y = gp.player.getSolidAreaDefaultY();
    }
}
