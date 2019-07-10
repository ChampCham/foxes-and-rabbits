package io.muzoo.ooc.ecosystems.model;


import io.muzoo.ooc.ecosystems.LivingFactory;
import io.muzoo.ooc.ecosystems.Location;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class Animal extends Living{


    protected int foodLevel;


    public  void setRandomAge(boolean randomAge){
        if (randomAge) {//For only populated the first field
            foodLevel = rand.nextInt(getFoodValue());
        } else { //Predator new born
            // leave age at 0
            foodLevel = getFoodValue()/2;
        }
    }

    protected void giveBirth(List newLivings){

        int births = breed();
        for (int b = 0; b < births; b++) {
            Location loc = field.randomAdjacentLocation(location);
            Living living = (Living) field.getObjectAt(loc);
            if (living == null || living.getName().equals(getFoodName())) {
                if (living != null){ living.setDead(); }

                Living newLiving = LivingFactory.create(getName(), false, loc,field);

                newLivings.add(newLiving);//Add the new living to new living list

                field.place(newLiving, loc);
            }
        }

    }


    protected Location findFood(Location location) {
        Iterator adjacentLocations = field.adjacentLocations(location);
        while (adjacentLocations.hasNext()) {
            Location where = (Location) adjacentLocations.next();
            Living target = (Living) field.getObjectAt(where);
            Set<String> foodList = getFoodName();

            if (target != null && foodList.contains(target.getName())) {
                if (target.isActive()) {
                    target.setDead();
                    foodLevel = getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

    public void walk() {
            Location loc = findFood(location);
            super.walk(loc);
    }


    protected void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }


    @Override
    public void act( List<Actor> newActors) {
        incrementAge();
        incrementHunger();
        giveBirth(newActors);
        walk();
    }

    abstract protected int getFoodValue();

    abstract protected Set<String> getFoodName();
}
