package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.Location;

import java.util.*;

public class Hunter extends Living{


    private static final int BREEDING_AGE = 10;

    private static final int MAX_AGE = 500;

    private static final double BREEDING_PROBABILITY = 0;

    private static final int MAX_LITTER_SIZE = 0;

    private  final String NAME = "hunter";

    private Location findTarget()
    {
        Iterator adjacentLocations = field.adjacentLocations(location,5);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Living target = (Living) field.getObjectAt(where);
            if(target instanceof Living) {
                Living living = (Living) target;
                living.setDead();
            }
        }
        return field.freeAdjacentLocation(location);
    }


    public void walk() {
        Location loc = findTarget();
        super.walk(loc);
    }


    @Override
    public void act(List<Actor> newActors) {
        walk();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    protected int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected double getBreedingProbability() {
        return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize() {
        return MAX_LITTER_SIZE;
    }

    @Override
    protected int getBreedingAge() {
        return BREEDING_AGE;
    }

    @Override
    protected String getName() {
        return NAME;
    }

}
