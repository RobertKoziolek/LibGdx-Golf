package com.robcio.golf.entity.recipe;

import com.badlogic.ashley.core.Entity;
import com.robcio.golf.component.particle.Particle;
import com.robcio.golf.component.structure.Dimension;
import com.robcio.golf.component.structure.Position;
import com.robcio.golf.enumeration.BallType;
import com.robcio.golf.enumeration.EntityFlags;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Getter
    private final Class<? extends Entity> entityClass;
    private final Position position;
    private final Dimension dimension;
    @Getter
    private final BallType ballType;
    @Getter
    private final EntityFlags entityFlags;
    private final Particle particle;

    public Position getPosition(){
        return position.clone();
    }

    public Dimension getDimension(){
        return dimension.clone();
    }

    public Particle getParticle(){
        //TODO clone particla
        return particle;
    }

    public static Assembler of(@NonNull final Class<? extends Entity> clazz) {
        return new Assembler(clazz);
    }

    public static class Assembler {

        private final Class<? extends Entity> clazz;
        private Position position;
        private Dimension dimension;
        private BallType ballType;
        private EntityFlags entityFlags;
        private Particle particle;

        private Assembler(final Class<? extends Entity> clazz) {
            this.clazz = clazz;
            assertRecipeConstructor();
        }

        public Assembler withPosition(final Position position) {
            this.position = position;
            return this;
        }

        public Assembler withDimension(final Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        public Assembler withBallType(final BallType ballType) {
            this.ballType = ballType;
            return this;
        }

        public Assembler withEntityFlags(final EntityFlags entityFlags) {
            this.entityFlags = entityFlags;
            return this;
        }

        public Assembler withParticle(final Particle particle) {
            this.particle = particle;
            //TODO czy tu jakiegos klona nie chce miec?
            return this;
        }

        public Recipe assemble() {
            return new Recipe(clazz, position, dimension, ballType, entityFlags, particle);
        }

        private void assertRecipeConstructor() {
            try {
                clazz.getConstructor(Recipe.class);
            } catch (final NoSuchMethodException e) {
                throw new IllegalArgumentException("Class " + clazz.getSimpleName() + " cannot be built from a recipe");
            }
        }
    }
}
