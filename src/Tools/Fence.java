/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Collection;
import java.util.Iterator;
import javax.swing.ImageIcon;

/**
 *
 * @author schia
 */
public class Fence implements Entity{
    /** The icon of this entity. */
    private final ImageIcon image = new ImageIcon("fence.gif"); 
    /** The position of this entity. */
    protected Pasture pasture;

    protected boolean alive = false;

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture.
     * @param pasture the pasture this entity should belong to.
     */
    public Fence(Pasture pasture) {
        this.pasture = pasture;
    }

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture, and position as its position.
     * @param pasture the pasture this entity should belong to.
     * @param position the position of this entity.
     */
    public Fence(Pasture pasture, boolean alive) {
        this.pasture   = pasture;
        this.alive     = alive;
    }

    @Override
    public void tick() {
    
    }

    @Override
    public ImageIcon getImage() {
        return image; 
    }

    @Override
    public boolean isCompatible(Entity otherEntity) { return false; }
    
    protected static <X> X getRandomMember(Collection<X> c) {
        if (c.size() == 0)
            return null;
        
        Iterator<X> it = c.iterator();
        int n = (int)(Math.random() * c.size());

        while (n-- > 0) {
            it.next();
        }

        return it.next();
    }
}
