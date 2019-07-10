package io.muzoo.ooc.ecosystems;

import io.muzoo.ooc.ecosystems.model.*;
import io.muzoo.ooc.ecosystems.view.SimulatorView;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a field containing
 * rabbits and foxes.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2002.10.28
 */
public class Simulator {
    // The private static final variables represent 
    // configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 50;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 50;

    // The list of actors in the field
    private List<Actor> actors;
    // The list of actors just born
    private List<Actor> newActors;
    // The current state of the field.
    public  Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        actors = new ArrayList<>();
        newActors = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Fox.class, Color.blue);
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Tiger.class, Color.red);
        view.setColor(Plant.class, Color.green);
        view.setColor(Hunter.class, Color.BLACK);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * e.g. 500 steps.
     */
    public void runLongSimulation() {
        simulate(500);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
        }
    }

    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep() {
        step++;
        newActors.clear();

        // let all actors act
        for (Iterator<Actor> iter = actors.iterator(); iter.hasNext(); ) {
            Actor actor = iter.next();
            if (!actor.isActive()){
                iter.remove();
            }
            else {
                actor.act(newActors);

            }
        }
        System.out.println(actors.size());
        // add new born actors to the list of actors
        actors.addAll(newActors);
        // display the new field on screen
        view.showStatus(step, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        actors.clear();
        field.clear(); //Null grid
        populate(field);
        // Show the starting state in the view.
        view.showStatus(step, field);
    }

    /**
     * Populate a field with foxes and rabbits.
     *
     * @param field The field to be populated.
     */
    private void populate(Field field) {
        Random rand = new Random();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                Actor actor = LivingFactory.create(location, field);
                if (actor != null) {
                    actors.add(actor);
                    field.place(actor, row, col);
                }
            }
        }
        Collections.shuffle(actors);
    }
}
