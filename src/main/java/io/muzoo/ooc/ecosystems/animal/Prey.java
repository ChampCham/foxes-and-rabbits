package io.muzoo.ooc.ecosystems.animal;

import io.muzoo.ooc.ecosystems.Field;
import io.muzoo.ooc.ecosystems.Location;

import java.util.List;

public abstract class Prey extends Animal{
    public Prey(boolean randomAge, Location location) { super(randomAge, location); }

    /**
     * This is what the rabbit does most of the time - it runs
     * around. Sometimes it will breed or die of old age.
     *
     * @param updatedField The field to transfer to.
     * @param newRabbits   A list to add newly born rabbits to.
     */
    public void run(Field updatedField, List newRabbits) {
        incrementAge();
        if (isAlive()) {
            int births = breed();
            for (int b = 0; b < births; b++) {
                Location loc = updatedField.randomAdjacentLocation(location);
                Animal newRabbit;
                if (getClassName().equals("Rabbit")){
                  newRabbit = new Rabbit(false, loc);
                    newRabbits.add(newRabbit);
                    updatedField.place(newRabbit, loc);
                }
            }
            Location newLocation = updatedField.freeAdjacentLocation(location);

            // Only transfer to the updated field if there was a free location
            if (newLocation != null) {
                setLocation(newLocation);
                updatedField.place(this, newLocation);
            } else {
                // can neither move nor stay - overcrowding - all locations taken
                setDead();
            }
        }
    }

    abstract protected String getClassName();
}
