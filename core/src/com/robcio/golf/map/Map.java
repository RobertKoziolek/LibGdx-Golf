package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Force;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.utils.Textures;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

public class Map {

    private final Engine engine;

    public Map(final Engine engine){
        this.engine = engine;
        createBoundaries();
        createEntities();
    }


    private void createEntities() {
        for (int i = 0; i < 1; ++i) {
            engine.addEntity(new Ball(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(15)));
        }

//        engine.addEntity(new Hole(Position.of(50, 150), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(350, 350), Dimension.of(16)));
//        engine.addEntity(new Hole(Position.of(600, 450), Dimension.of(16)));

        addHole(Position.of(400, 500));
        addHole(Position.of(55, 500));
        addHole(Position.of(400, 100));
        addHole(Position.of(55, 100));
        engine.addEntity(new Bowl(Position.of(700, 300), Dimension.of(199), Textures.BOWL));

        final Force bumperForce =  Force.of(55);
        final Dimension bumperDimension = Dimension.of(20);

        engine.addEntity(new Bumper(Position.of(200, 500), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 400), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 300), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 200), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(200, 100), bumperDimension, bumperForce));

        engine.addEntity(new Bumper(Position.of(300, 150), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 250), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 350), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(300, 450), bumperDimension, bumperForce));

        engine.addEntity(new Bumper(Position.of(100, 150), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 250), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 350), bumperDimension, bumperForce));
        engine.addEntity(new Bumper(Position.of(100, 450), bumperDimension, bumperForce));

//        BodyFactory.createBox(Position.of(200, 200), Dimension.of(50, 99), false, false, 2, 3);
//        BodyFactory.createBox(Position.of(211, 400), Dimension.of(140, 49), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(773, 500), Dimension.of(50, 89), false, false, 2, 3);
//        BodyFactory.createCircular(Position.of(473, 500), Dimension.of(50, 50), false, false, 2, 3);
    }

    private void addHole(final Position position) {
        engine.addEntity(new Bowl(position, Dimension.of(25), Textures.HOLE));
        engine.addEntity(new Hole(position, Dimension.of(0.5f)));
    }

    private void createBoundaries() {
        //TODO popatrzyc na EdgeShape czy nie lepszy do tego
        new Wall(Position.of(WIDTH / 2, 0), Dimension.of(WIDTH, 9));
        new Wall(Position.of(WIDTH / 2, HEIGHT - 39), Dimension.of(WIDTH, 9));
        new Wall(Position.of(0, HEIGHT / 2), Dimension.of(9, HEIGHT));
        new Wall(Position.of(WIDTH - 0, HEIGHT / 2), Dimension.of(9, HEIGHT));

//        new Wall(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(9, HEIGHT - 299));
//        new Wall(Position.of(WIDTH / 2 - 299, HEIGHT / 2), Dimension.of(9, HEIGHT - 299));
//        new Wall(Position.of(WIDTH / 2 - 149, HEIGHT / 2 + 99), Dimension.of(299, 9));
    }
}
