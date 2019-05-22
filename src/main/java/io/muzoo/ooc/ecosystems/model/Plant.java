package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.Field;

import java.util.List;

public class Plant implements Actor{

    @Override
    public void act(Field field, Field updatedField, List<Actor> newActors) {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
