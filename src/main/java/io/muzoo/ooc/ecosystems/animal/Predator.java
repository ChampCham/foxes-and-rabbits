package io.muzoo.ooc.ecosystems.animal;

import io.muzoo.ooc.ecosystems.Field;
import io.muzoo.ooc.ecosystems.Location;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal{
    // A shared random number generator to control breeding.
    private static final Random rand = new Random();

    protected int foodLevel;

    public Predator(boolean randomAge, Location location) {
        super(randomAge, location);
        if (randomAge) {//For only populated the first field
            foodLevel = rand.nextInt(getRabbitFoodValue());
        } else { //Predator new born
            // leave age at 0
            foodLevel = getRabbitFoodValue();
        }
    }

    /**
     * This is what the predator does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     *
     * @param currentField The field currently occupied.
     * @param updatedField The field to transfer to.
     * @param newPredatores     A list to add newly born predatores to.
     */
    public void hunt(Field currentField, Field updatedField, List newPredatores) {
        incrementAge();
        incrementHunger();
        if (alive) {
            // New predators are born into adjacent locations.
            int births = breed(); //the number of new born predators
            for (int b = 0; b < births; b++) {
                //Random location for the new born predator near their parent
                Location loc = updatedField.randomAdjacentLocation(location);

                Animal newPredator;
                if (getClassName().equals("Fox")){// New predators are born
                    newPredator = new Fox(false, loc);
                }else {
                    newPredator = new Tiger(false, loc);
                }

                newPredatores.add(newPredator);//Add the new predator to new Predators list

                updatedField.place(newPredator, loc);
            }

            // Move towards the source of food if found.
            Location newLocation = findFood(currentField, location);
            if (newLocation == null) {  // no food found - move randomly
                newLocation = updatedField.freeAdjacentLocation(location);
            }
            if (newLocation != null) {
                setLocation(newLocation);
                updatedField.place(this, newLocation);//Replace to rabbit/free location
            } else {
                // can neither move nor stay - overcrowding - all locations taken
                setDead();
            }
        }
    }

    /**
     * Tell the predator to look for rabbits adjacent to its current location.
     *
     * @param field    The field in which it must look.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Field field, Location location) {
        Iterator adjacentLocations = field.adjacentLocations(location);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Object animal = field.getObjectAt(where);

            /*Found Rabbit*/
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = getRabbitFoodValue();
                    return where; //Stay on eaten rabbit location
                }
            }
        }
        return null; //Can't find nearby rabbit
    }

    /**
     * Make this tiger more hungry. This could result in the tiger's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    abstract protected int getRabbitFoodValue();

    abstract protected String getClassName();

}
