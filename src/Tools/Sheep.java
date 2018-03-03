/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Main.Pasture;
import javax.swing.ImageIcon;

/**
 *
 * @author schia
 */
public class Sheep implements Entity{
    /** The icon of this entity. */
    private final ImageIcon image = new ImageIcon("sheep.gif"); 
    /** The position of this entity. */
    protected Pasture pasture;
    /** The number of ticks this entity should get before moving. */
    protected int moveDelay;

    protected boolean alive = true;
    
    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Sheep(Pasture pasture) {
        this.pasture = pasture;
        moveDelay = 10;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageIcon getImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCompatible(Entity otherEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
