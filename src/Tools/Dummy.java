package Tools;

import javax.swing.*;
import java.util.*;
import java.awt.*;

// Note that Dummy is a pretty BAD example of object-oriented
// programming. Instead of having separate classes for stationary and
// mobile dummies, they are distinguished using the flag "alive".  You
// probably do not want to base your solution on this class.


public class Dummy implements Entity {
    /** The icon of this entity. */
    private final ImageIcon image = new ImageIcon("unknown.gif"); 
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
    public Dummy(Pasture pasture) {
        this.pasture = pasture;
        moveDelay = 10;
    }

    /**
     * Creates a new instance of this class, with the given pasture as
     * its pasture, and position as its position.
     * @param pasture the pasture this entity should belong to.
     * @param position the position of this entity.
     */
    public Dummy(Pasture pasture, boolean alive) {
        this.pasture   = pasture;
        this.alive     = alive;
        moveDelay      = 10;
    }

    /**
     * Performs the relevant actions of this entity, depending on what
     * kind of entity it is.
     */
    public void tick() {
        if(alive)
            moveDelay--;
        
        if(moveDelay == 0) {
            Point neighbour = 
                (Point)getRandomMember
                (pasture.getFreeNeighbours(this));
            
            if(neighbour != null) 
                pasture.moveEntity(this, neighbour);

            moveDelay = 10;
        }
    }
    
    /** 
     * Returns the icon of this entity, to be displayed by the pasture
     * gui. 
     * @see PastureGUI
     */
    public ImageIcon getImage() { return image; }

    /**
     * Tests if this entity can be on the same position in the pasture
     * as the given one.
     */
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
