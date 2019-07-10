package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.model.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class LivingFactory {

    private static final double HUNTER_CREATION_PROBABILITY = 0.002;
    // The probability that a fox will be created in any given grid position.
    private static final double TIGER_CREATION_PROBABILITY = 0.02;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.03;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.1;

    private static final double PLANT_CREATION_PROBABILITY = 0.4;


    private static Map<Double, Class<? extends Living>> registeredLivingClassesByProb
            = new LinkedHashMap<Double, Class<? extends Living>>()
    {
        {   put(HUNTER_CREATION_PROBABILITY, Hunter.class);
            put(TIGER_CREATION_PROBABILITY, Tiger.class);
            put(FOX_CREATION_PROBABILITY, Fox.class);
            put(RABBIT_CREATION_PROBABILITY, Rabbit.class);
            put(PLANT_CREATION_PROBABILITY, Plant.class);

        }
    };

    private static Map<String, Class<? extends Living>> registeredLivingClassesByName
            = new LinkedHashMap<String, Class<? extends Living>>()
    {
        {   put("hunter", Hunter.class);
            put("tiger", Tiger.class);
            put("fox", Fox.class);
            put("rabbit", Rabbit.class);
            put("plant", Plant.class);

        }
    };

    public static Living create(String livingType, boolean randomAge, Location location, Field field){
        Class<? extends Living> livingClass = registeredLivingClassesByName.get(livingType);
        Living living = null;
        try {
            living = livingClass.newInstance();
            living.setRandomAge(randomAge);
            living.setLocation(location);
            field.place(living,location);
            living.setField(field);
        }catch (InstantiationException | IllegalAccessException ex){
        }catch (NullPointerException npe){
            System.out.println("Not found livingType: " + livingType);
        }
        return living;
    }

    public static Living create(Location location, Field field){
        Random rand = new Random();
        Double randDouble = rand.nextDouble();
        for (Map.Entry<Double, Class<? extends Living>> entry : registeredLivingClassesByProb.entrySet()) {
            Double prob = entry.getKey();
            Class<? extends Living>  livingClass = entry.getValue();
            if (randDouble < prob){
                try {
                    Living living = livingClass.newInstance();
                    living.setRandomAge(true);
                    living.setLocation(location);
                    field.place(living, location);
                    living.setField(field);
                    return living;
                }catch (InstantiationException | IllegalAccessException ex){
                    System.out.println("Not found livingType");
                }
            }else {
                randDouble -= prob;
            }
        }
        return null;
    }

}
