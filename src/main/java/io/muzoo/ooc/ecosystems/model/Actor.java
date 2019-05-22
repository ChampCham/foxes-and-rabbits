package io.muzoo.ooc.ecosystems.model;

import io.muzoo.ooc.ecosystems.Field;

import java.util.List;

public interface Actor {

    /**
     * Make this actor act – that is, make it do whatever
     * it wants/needs to do.
     * @param newActors A list to return newly born actors.
     */
    abstract public void act(Field field, Field updatedField, List<Actor> newActors);

    abstract public boolean isActive();
}
