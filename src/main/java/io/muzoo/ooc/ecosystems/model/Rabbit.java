package io.muzoo.ooc.ecosystems.model;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Rabbit extends Animal{

    private static final int BREEDING_AGE = 10;

    private static final int MAX_AGE = 40;

    private static final double BREEDING_PROBABILITY = 0.4;

    private static final int MAX_LITTER_SIZE = 5;

    private static final int PLANT_FOOD_VALUE = 5;

    private  final String NAME = "rabbit";

    private  final Set<String> FOOD_NAME = new HashSet<>(
            Arrays.asList("plant")
    );

    public Rabbit(){}

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
    protected int getFoodValue() { return PLANT_FOOD_VALUE;}

    @Override
    protected String getName() { return NAME; }

    @Override
    protected Set<String> getFoodName() { return FOOD_NAME; }

}
