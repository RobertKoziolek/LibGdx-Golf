package com.robcio.golf.map;

import com.badlogic.ashley.core.Engine;
import com.robcio.golf.component.Dimension;
import com.robcio.golf.component.Force;
import com.robcio.golf.component.Position;
import com.robcio.golf.entity.*;
import com.robcio.golf.utils.Textures;

import static com.robcio.golf.MainClass.HEIGHT;
import static com.robcio.golf.MainClass.WIDTH;

//IDEA sloneczna mapa moze miec poruszajacy sie w bok cien chmury
public class TestMap {

    private final Engine engine;

    public TestMap(final Engine engine){
        this.engine = engine;
        createBoundaries();
        createHoles();
        createBalls();
        createBowls();
        createBoxes();
        createBumpers();
    }

    private void createBoxes() {
        engine.addEntity(new Box(Position.of(700,50), Dimension.of(40,40)));
        engine.addEntity(new Box(Position.of(700,50), Dimension.of(50,50)));
        engine.addEntity(new Box(Position.of(700,50), Dimension.of(30,30)));
        engine.addEntity(new Box(Position.of(700,50), Dimension.of(30,30)));
        engine.addEntity(new Box(Position.of(700,50), Dimension.of(30,30)));
    }

    private void createBowls() {
        engine.addEntity(new Bowl(Position.of(700, 300), Dimension.of(299), Textures.BOWL));
    }

    private void createHoles() {
        addHole(Position.of(400, 500));
        addHole(Position.of(55, 500));
        addHole(Position.of(400, 50));
        addHole(Position.of(55, 50));
    }

    private void createBalls() {
        for (int i = 0; i < 1; ++i) {
            engine.addEntity(new Ball(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(30)));
        }
    }

    private void createBumpers() {
        addBumper(200, 450);
        addBumper(200, 350);
        addBumper(200, 250);
        addBumper(200, 150);
        addBumper(200, 50);

        addBumper(300, 100);
        addBumper(300, 200);
        addBumper(300, 300);
        addBumper(300, 400);

        addBumper(100, 100);
        addBumper(100, 200);
        addBumper(100, 300);
        addBumper(100, 400);
    }

    private final Dimension bumperDimension = Dimension.of(40);
    private void addBumper(final float x, final float y) {
        engine.addEntity(new Bumper(Position.of(x, y), bumperDimension));
    }

    private void addHole(final Position position) {
        engine.addEntity(new Bowl(position, Dimension.of(50), Textures.HOLE));
        engine.addEntity(new Hole(position, Dimension.of(1f)));
    }

    private void createBoundaries() {
        //TODO popatrzyc na EdgeShape czy nie lepszy do tego
        engine.addEntity(new Wall(Position.of(WIDTH / 2, 0), Dimension.of(WIDTH, 9)));
        engine.addEntity(new Wall(Position.of(WIDTH / 2, HEIGHT - 39), Dimension.of(WIDTH, 9)));
        engine.addEntity(new Wall(Position.of(0, HEIGHT / 2), Dimension.of(9, HEIGHT)));
        engine.addEntity(new Wall(Position.of(WIDTH - 0, HEIGHT / 2), Dimension.of(9, HEIGHT)));

//        engine.addEntity(new Wall(Position.of(WIDTH / 2, HEIGHT / 2), Dimension.of(9, HEIGHT - 299)));
//        engine.addEntity(new Wall(Position.of(WIDTH / 2 - 299, HEIGHT / 2), Dimension.of(9, HEIGHT - 299)));
//        engine.addEntity(new Wall(Position.of(WIDTH / 2 - 149, HEIGHT / 2 + 99), Dimension.of(299, 9)));

//        engine.addEntity(new Wall(Position.of(WIDTH / 2 - 149, HEIGHT / 2 + 99), Dimension.of(299, 229)));
    }
}
