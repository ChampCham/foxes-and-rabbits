package io.muzoo.ooc.ecosystems.model;

import java.util.List;

public interface Actor {

    void act(List<Actor> newActors);

    boolean isActive();

}
