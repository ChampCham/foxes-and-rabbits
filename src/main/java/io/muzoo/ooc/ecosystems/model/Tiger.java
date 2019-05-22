package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.Field;

import java.util.List;

public class Tiger extends Predator{
    // Characteristics shared by all tigeres (static fields).

    // The age at which a tiger can start to breed.
    private static final int BREEDING_AGE = 10;
    // The age to which a tiger can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a tiger breeding.
    private static final double BREEDING_PROBABILITY = 0.09;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a tiger can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 4;

    /**
     * Create a tiger. A tiger can be created as a new born (age zero
     * and not hungry) or with random age.
     */
    public Tiger(){ super(); }

    @Override
    protected int getMaxAge(){ return MAX_AGE; }

    @Override
    protected double getBreedingProbability(){ return BREEDING_PROBABILITY; }

    @Override
    protected int getMaxLitterSize(){ return MAX_LITTER_SIZE; }

    @Override
    protected int getBreedingAge(){ return BREEDING_AGE; }

    @Override
    protected int getRabbitFoodValue(){ return RABBIT_FOOD_VALUE; }

    @Override
    protected String getClassName(){ return "tiger"; }

    @Override
    public void act(Field field, Field updatedField, List<Actor> newActors) {  hunt(field,updatedField,newActors); }

    @Override
    public boolean isActive() {
        return false;
    }
}
