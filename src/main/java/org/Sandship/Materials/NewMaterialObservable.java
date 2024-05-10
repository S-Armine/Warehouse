package org.Sandship.Materials;

import org.Sandship.Player;

import java.util.ArrayList;
import java.util.List;

// a class that holds a list of observers that want to be notified about new material in a system
//this class also manages sending notifications
public class NewMaterialObservable {
    private List<Player> observers = new ArrayList<>();
    private static NewMaterialObservable instance;

    private NewMaterialObservable() {

    }

    public static NewMaterialObservable getInstance() {
        if (instance == null) {
            synchronized (NewMaterialObservable.class) {
                if(instance == null)
                {
                    instance = new NewMaterialObservable();
                }
            }
        }
        return instance;
    }

    public void addObserver(Player player) {
        if (!observers.contains(player)) {
            observers.add(player);
        }
    }

    public void removeObserver(Player player) {
        if (observers.contains(player)) {
            observers.add(player);
        }
    }

    //notifying observers is done via this method
    public void notify(String materialName) {
        for (var observer : observers) {
            observer.update(materialName);
        }
    }
}
