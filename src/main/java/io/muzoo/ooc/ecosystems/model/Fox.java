package io.muzoo.ooc.ecosystems.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Fox extends Animal{

    private static final int BREEDING_AGE = 10;

    private static final int MAX_AGE = 80;

    private static final double BREEDING_PROBABILITY = 0.2;

    private static final int MAX_LITTER_SIZE = 3;

    private static final int RABBIT_FOOD_VALUE = 12;

    private  final String NAME = "fox";

    private  final Set<String> FOOD_NAME = new HashSet<>(
            Arrays.asList("rabbit")
    );

    public Fox(){ }

    @Override
    protected int getMaxAge(){ return MAX_AGE; }

    @Override
    protected double getBreedingProbability(){ return BREEDING_PROBABILITY; }

    @Override
    protected int getMaxLitterSize(){ return MAX_LITTER_SIZE; }

    @Override
    protected int getBreedingAge(){ return BREEDING_AGE; }

    @Override
    protected int getFoodValue(){ return RABBIT_FOOD_VALUE; }

    @Override
    protected String getName() {
        return NAME;
    }

    @Override
    protected Set<String> getFoodName() {
        return FOOD_NAME;
    }
}
