package com.robcio.golf.world;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.utils.Maths;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BodyAssembler {

    private static World world;

    final private static ShapeFactory shapeFactory = new ShapeFactory();

    public static void setWorld(final World world) {
        if (BodyAssembler.world != null) throw new IllegalStateException("World cannot be set twice");
        BodyAssembler.world = world;
    }

    private final FixtureDef fixtureDef;
    private Object userData;
    private Position position;
    private boolean isStatic;
    private boolean isRotationFixed;
    private float angle;
    private float linearDamping;
    private float angularDamping;

    public BodyAssembler(final FixtureDef fixtureDef) {
        this.fixtureDef = fixtureDef;
    }

    public static BodyAssembler withShape(final Shape shape){
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        return new BodyAssembler(fixtureDef);
    }

    public static BodyAssembler box(final Dimension dimension) {
        final Shape box = shapeFactory.createBox(dimension);
        return withShape(box);
    }

    public static BodyAssembler circular(final Dimension dimension) {
        final Shape circular = shapeFactory.createCircular(dimension);
        return withShape(circular);
    }

    public BodyAssembler withUserData(final Object object) {
        this.userData = object;
        return this;
    }

    public BodyAssembler withPosition(final Position position) {
        this.position = position;
        return this;
    }

    public BodyAssembler withCategoryBits(final short categoryBits) {
        fixtureDef.filter.categoryBits = categoryBits;
        return this;
    }

    public BodyAssembler withSensor(final boolean isSensor) {
        fixtureDef.isSensor = isSensor;
        return this;
    }

    public BodyAssembler withMaskBits(final short maskBits) {
        fixtureDef.filter.maskBits = maskBits;
        return this;
    }

    public BodyAssembler withAngle(final float angle) {
        this.angle = angle;
        return this;
    }

    public BodyAssembler withRestitution(final float restitution) {
        fixtureDef.restitution = restitution;
        return this;
    }

    public BodyAssembler withDensity(final float density) {
        fixtureDef.density = density;
        return this;
    }

    public BodyAssembler withLinearDamping(final float linearDamping) {
        this.linearDamping = linearDamping;
        return this;
    }

    public BodyAssembler withAngularDamping(final float angularDamping) {
        this.angularDamping = angularDamping;
        return this;
    }

    public BodyAssembler withStatic(final boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    public BodyAssembler withFixedRotation(final boolean isRotationFixed) {
        this.isRotationFixed = isRotationFixed;
        return this;
    }

    public Body assemble() {
        //TODO rotacja sprawia ze zmienia sie pozycja, w zwiazku z tym mapa z Tiled nie jest taka jak w grze
        final BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = isRotationFixed;
        bodyDef.angle = Maths.degreesToRadians(angle);
        bodyDef.type = isStatic ? BodyType.StaticBody : BodyType.DynamicBody;

        if (position != null) {
            position = Position.toBox2D(position);
            bodyDef.position.set(position.x, position.y);
        }

        final Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setLinearDamping(linearDamping);
        body.setAngularDamping(angularDamping);
        body.setUserData(userData);
        fixtureDef.shape.dispose();
        return body;
    }
}
