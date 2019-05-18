package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.animal.Animal;
import io.muzoo.ooc.ecosystems.animal.Fox;
import io.muzoo.ooc.ecosystems.animal.Rabbit;
import io.muzoo.ooc.ecosystems.animal.Tiger;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {

    private static Map<String, Class<? extends Animal>> registeredAnimalClasses
            = new HashMap<String, Class<? extends Animal>>() {
        {
            put("rabbit", Rabbit.class);
            put("fox", Fox.class);
            put("tiger", Tiger.class);
        }
    };

    public static Animal create(String animalType,boolean randomAge, Location location){
        Class<? extends Animal> animalClass =registeredAnimalClasses.get(animalType);
        Animal animal = null;
        try {
            animal = animalClass.newInstance();
            animal.setRandomAge(randomAge);
            animal.setLocation(location);
        }catch (InstantiationException | IllegalAccessException ex){
        }catch (NullPointerException npe){
            System.out.println("Not found animalType: " + animalType);
        }
        return animal;
    }
}
