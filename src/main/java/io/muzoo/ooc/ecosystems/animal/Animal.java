package io.muzoo.ooc.ecosystems.animal;

import io.muzoo.ooc.ecosystems.Field;
import io.muzoo.ooc.ecosystems.Location;

import java.util.List;
import java.util.Random;

public abstract class Animal {
    // Characteristics shared by all animals (static fields).

    // A shared random number generator to control breeding.
    private static final Random rand = new Random();

    // Individual characteristics (instance fields).

    // The animal's age.
    protected int age;
    // Whether the animal is alive or not.
    protected boolean alive;
    // The animal's field.
    protected Field field;
    // The animal's position
    protected Location location;

    /**
     * Create a new animal at location in field.
     *
     *  @param randomAge If true, the animal will have random age.
     */
    public Animal(boolean randomAge, Location location)
    {
        age = 0;
        alive = true;
        this.location = location;
        if (randomAge) {//For only populated the first field
            age = rand.nextInt(getMaxAge());
        }
    }

    /**
     * Increase the age.
     * This could result in the animal's death.
     */

    public void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            alive = false;
        }
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }


    /**
     * A animal can breed if it has reached the breeding age.
     *
     * @return true if the animal can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= getBreedingAge();
    }


    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Tell the animal that it's dead now :(
     */
    protected void setDead() {
        alive = false;
    }


    /**
     * Set the animal's location.
     *
     * @param row The vertical coordinate of the location.
     * @param col The horizontal coordinate of the location.
     */
    protected void setLocation(int row, int col) {
        this.location = new Location(row, col);
    }

    /**
     * Set the animal's location.
     *
     * @param location The animal's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Make this animal act – that is, make it do whatever
     * it wants/needs to do.
     * @param newAnimals A list to return newly born animals.
     */
    abstract public void act(Field field,Field updatedField,List<Animal> newAnimals);
    /**
     * Return the max age of this animal.
     * @return The max age of this animal.
     */
    abstract protected int getMaxAge();


    /**
     * Return the breeding probability of this animal.
     * @return The breeding probability  of this animal.
     */
    abstract protected double getBreedingProbability();

    /**
     * Return the max litter size of this animal.
     * @return The  max litter size of this animal.
     */
    abstract protected int getMaxLitterSize();

    /**
     * Return the breeding age of this animal.
     * @return The breeding age of this animal.
     */
    abstract protected int getBreedingAge();

}
