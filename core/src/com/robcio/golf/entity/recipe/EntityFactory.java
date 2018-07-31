package com.robcio.golf.entity.recipe;

import com.badlogic.ashley.core.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class EntityFactory {

    private static final String ILLEGAL_STATE_MESSAGE = "How the hell did the recipe even get a non-recipe build class";

    public static Entity createFrom(final Recipe recipe) {
        try {
            final Class<? extends Entity> entityClass = recipe.getEntityClass();
            final Constructor<? extends Entity> constructor = entityClass.getConstructor(Recipe.class);
            return constructor.newInstance(recipe);

        } catch (final NoSuchMethodException e) {
            throw new IllegalStateException(ILLEGAL_STATE_MESSAGE + " 0");
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(ILLEGAL_STATE_MESSAGE + " 1");
        } catch (final InstantiationException e) {
            throw new IllegalStateException(ILLEGAL_STATE_MESSAGE + " 2");
        } catch (final InvocationTargetException e) {
            throw new IllegalStateException(ILLEGAL_STATE_MESSAGE + " 3");
        }
    }

}
