package io.muzoo.ooc.ecosystems.model;


import io.muzoo.ooc.ecosystems.Field;
import io.muzoo.ooc.ecosystems.Location;
import java.util.Random;


public abstract class Living implements Actor{

    protected static final Random rand = new Random();

    protected int age = 0;

    protected boolean alive;

    protected Field field;

    protected Location location;


    public Living(){
        this.alive = true;
    }

    public Living(Builder builder){
        this.location = builder.location;
        this.field = builder.field;
        setRandomAge(builder.randomAge);
    }


    public void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    private boolean canBreed(){ return age >= getBreedingAge(); }

    protected int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }


    public boolean isActive() {
        return alive;
    }


    protected void setDead(){
        field.place(null,location);
        alive = false;
    }

    protected void setLocation(int row, int col){ this.location = new Location(row, col); }


    public void setLocation(Location location){ this.location = location; }


    public void setField(Field field) {
        this.field = field;
    }

    public Location getLocation() { return location; }


    public  void setRandomAge(boolean randomAge){
        if (randomAge) {//For only populated the first field
            age = rand.nextInt(getMaxAge());
        }
    }

    public void walk(Location loc) {
        if (alive) {
            if (loc == null) {
                loc = field.freeAdjacentLocation(location);
            } else {
                field.place(this, loc);
                field.place(null, location);
                setLocation(loc);
            }

            if (loc == null) {
                setDead();
            }
        }
    }


    abstract protected int getMaxAge();


    abstract protected double getBreedingProbability();


    abstract protected int getMaxLitterSize();

    abstract protected int getBreedingAge();

    abstract protected String getName();



    public static class Builder {

        protected Boolean randomAge;
        protected Field field;
        protected Location location;

        public Builder(int row, int col){
            this.location = new Location(row,col);
        }

        public Builder isRandomAge(boolean randomAge) {
            this.randomAge = randomAge;
            return this;
        }

        public Builder withField(Field field) {
            this.field = field;
            return this;
        }

    }

}
