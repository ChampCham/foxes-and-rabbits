package io.muzoo.ooc.ecosystems.animal;

import io.muzoo.ooc.ecosystems.AnimalFactory;
import io.muzoo.ooc.ecosystems.Field;
import io.muzoo.ooc.ecosystems.Location;

import java.util.List;

public abstract class Prey extends Animal{
    public Prey(){ super(); }

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

            giveBirth(updatedField, updatedField, newRabbits);
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

}
