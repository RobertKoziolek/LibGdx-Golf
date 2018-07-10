package com.robcio.golf.component.util;

import com.badlogic.ashley.core.Component;
import lombok.AllArgsConstructor;

//TODO moze byc tibia jesli sie nalozy usuwanie componentu i calego obiektu bo component jest jeden na recipe
//TODO moze dodac ToRemove2, dzialajacy w systemie na OR i tyle
@AllArgsConstructor( )
public class ToRemove implements Component {

    public final Class<? extends Component> component;

    private ToRemove() {
        this.component = null;
    }

    public static ToRemove of(final Class<? extends Component> component) {
        return new ToRemove(component);
    }

    public static ToRemove self() {
        return new ToRemove();
    }
}
