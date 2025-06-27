package xXxDungeon_EscapexXx.items;

import xXxDungeon_EscapexXx.model.Entity;

public interface Equipable {
    String getName();
    void equip(Entity cible);
    void unequip(Entity cible);
}
