package Tools;
import Main.Engine;

import GUI.PastureGUI;
import Main.Engine;
import Tools.Dummy;
import Tools.Entity;
import Tools.Fence;
import java.util.*;
import java.awt.Point;


/**
 * A pasture contains sheep, wolves, fences, plants, and possibly
 * other entities. These entities move around in the pasture and try
 * to find food, other entities of the same kind and run away from
 * possible enemies. 
 */
public class Pasture {

    private int         width = 20;
    private int         height = 20;

    private int         dummys = 20;
    private int         wolves;
    private int         sheep;
    private int         plants;
    private int         fences;

    private Set<Entity> world = 
        new HashSet<Entity>();
    private Map<Point, List<Entity>> grid = 
        new HashMap<Point, List<Entity>>();
    private Map<Entity, Point> point 
        = new HashMap<Entity, Point>();

    private PastureGUI gui;

    /** 
     * Creates a new instance of this class and places the entities in
     * it on random positions.
     */
    public Pasture() {

        Engine engine = new Engine(this);
        gui = new PastureGUI(width, height, engine);

        /* The pasture is surrounded by a fence. Replace Dummy for
         * Fence when you have created that class */
        for (int i = 0; i < width; i++) {
            gui.addEntity(new Fence(this, false), new Point(i,0));
            gui.addEntity(new Fence(this, false), new Point(i, height - 1));
        }
        for (int i = 1; i < height-1; i++) {
            gui.addEntity(new Fence(this, false), new Point(0,i));
            gui.addEntity(new Fence(this, false), new Point(width - 1,i));
        }

        /* 
         * Now insert the right number of different entities in the
         * pasture.
         */
        for (int i = 0; i < dummys; i++) {
            Entity dummy = new Dummy(this, true);
            addEntity(dummy, getFreePosition(dummy));
        }

        gui.update();
    }

    public void refresh() {
        gui.update();
    }

    /**
     * Returns a random free position in the pasture if there exists
     * one.
     * 
     * If the first random position turns out to be occupied, the rest
     * of the board is searched to find a free position. 
     */
    private Point getFreePosition(Entity toPlace) 
        throws MissingResourceException {
        Point position = new Point((int)(Math.random() * width),
                                   (int)(Math.random() * height)); 
        int startX = (int)position.getX();
        int startY = (int)position.getY();

        int p = startX+startY*width;
        int m = height * width;
        int q = 97; //any large prime will do

        for (int i = 0; i<m; i++) {
            int j = (p+i*q) % m;
            int x = j % width;
            int y = j / width;

            position = new Point(x,y);
            boolean free = true;

            Collection <Entity> c = getEntitiesAt(position);
            if (c != null) {
                for (Entity thisThing : c) {
                    if(!toPlace.isCompatible(thisThing)) { 
                        free = false; break; 
                    }
                }
            }
            if (free) return position;
        }
        throw new MissingResourceException(
                  "There is no free space"+" left in the pasture",
                  "Pasture", "");
    }
    
            
    public Point getPosition (Entity e) {
        return point.get(e);
    }

    /**
     * Add a new entity to the pasture.
     */
    public void addEntity(Entity entity, Point pos) {

        world.add(entity);

        List<Entity> l = grid.get(pos);
        if (l == null) {
            l = new  ArrayList<Entity>();
            grid.put(pos, l);
        }
        l.add(entity);

        point.put(entity,pos);

        gui.addEntity(entity, pos);
    }
    
    public void moveEntity(Entity e, Point newPos) {
        
        Point oldPos = point.get(e);
        List<Entity> l = grid.get(oldPos);
        if (!l.remove(e)) 
            throw new IllegalStateException("Inconsistent stat in Pasture");
        /* We expect the entity to be at its old position, before we
           move, right? */
                
        l = grid.get(newPos);
        if (l == null) {
            l = new  ArrayList<Entity>();
            grid.put(newPos, l);
        }
        l.add(e);

        point.put(e, newPos);

        gui.moveEntity(e, oldPos, newPos);
    }

    /**
     * Remove the specified entity from this pasture.
     */
    public void removeEntity(Entity entity) { 

        Point p = point.get(entity);
        world.remove(entity); 
        grid.get(p).remove(entity);
        point.remove(entity);
        gui.removeEntity(entity, p);

    }

    /**
     * Various methods for getting information about the pasture
     */

    public List<Entity> getEntities() {
        return new ArrayList<Entity>(world);
    }
        
    public Collection<Entity> getEntitiesAt(Point lookAt) {

        Collection<Entity> l = grid.get(lookAt);
        
        if (l==null) {
            return null;
        }
        else {
            return new ArrayList<Entity>(l);
        }
    }


    public Collection<Point> getFreeNeighbours(Entity entity) {
        Set<Point> free = new HashSet<Point>();
        Point p;

        int entityX = (int)getEntityPosition(entity).getX();
        int entityY = (int)getEntityPosition(entity).getY();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
            p = new Point(entityX + x,
                          entityY + y);
            if (freeSpace(p, entity))
                free.add(p);
            }
        }
        return free;
    }

    private boolean freeSpace(Point p, Entity e) {
                              
        List <Entity> l = grid.get(p);
        if ( l == null  ) return true;
        for (Entity old : l ) 
            if (! old.isCompatible(e)) return false;
        return true;
    }

    public Point getEntityPosition(Entity entity) {
        return point.get(entity);
    }


}


