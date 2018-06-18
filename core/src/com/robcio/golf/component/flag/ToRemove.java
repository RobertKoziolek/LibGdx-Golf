package com.robcio.golf.component.flag;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//TODO moznaby zrobic abstrakcyjne klasy dla encji i ToRemove moglby byc dodawany niejawnie
//TODO moze byc tibia jesli sie nalozy usuwanie componentu i calego obiektu bo component jest jeden na entity
@AllArgsConstructor
public class ToRemove implements Component {

    public final Class<? extends Component> component;

    public ToRemove() {
        this.component = null;
    }
}
