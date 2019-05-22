package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.Field;
import java.util.List;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Rabbit extends Prey{
    // Characteristics shared by all rabbits (static fields).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 50;
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.15;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 5;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     */
    public Rabbit(){ super();}

    @Override
    protected int getMaxAge(){ return MAX_AGE; }

    @Override
    protected double getBreedingProbability(){ return BREEDING_PROBABILITY;
    }

    @Override
    protected int getMaxLitterSize(){ return MAX_LITTER_SIZE; }

    @Override
    protected int getBreedingAge(){ return BREEDING_AGE; }

    @Override
    protected String getClassName(){ return "rabbit"; }

    @Override
    public void act(Field field, Field updatedField, List<Actor> newActors) {  run(updatedField,newActors); }

    @Override
    public boolean isActive() {
        return false;
    }
}