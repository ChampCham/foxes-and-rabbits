package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.LivingFactory;
import io.muzoo.ooc.ecosystems.Location;

import java.util.List;



public class Plant  extends Living {

    private static final int BREEDING_AGE = 5;

    private static final int MAX_AGE = 300;

    private static final double BREEDING_PROBABILITY = 0.7;

    private static final int MAX_LITTER_SIZE = 10;

    private  final String NAME = "plant";


    @Override
    protected int getMaxAge() { return MAX_AGE; }

    @Override
    protected double getBreedingProbability() { return BREEDING_PROBABILITY; }

    @Override
    protected int getMaxLitterSize() { return MAX_LITTER_SIZE; }

    @Override
    protected int getBreedingAge() { return BREEDING_AGE; }

    @Override
    protected String getName() { return NAME; }


    protected void giveBirth( List newLivings){
        // New livings are born into adjacent locations.
        int births = breed(); //the number of new born livings
        for (int b = 0; b < births; b++) {
            Location loc = field.randomAdjacentLocation(location,100);
            Living living = (Living) field.getObjectAt(loc);
            if (living == null) {
                Living newLiving = LivingFactory.create(getName(), false, loc,field);
                newLivings.add(newLiving);  //Add the new living to new living list
                field.place(newLiving, loc);
            }
        }

    }

    @Override
    public void act(List<Actor> newActors) {
        incrementAge();
        giveBirth(newActors);
    }
}
