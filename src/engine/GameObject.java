package engine;

import engine.components.Transform;
import engine.components.Component;
import java.util.ArrayList;
import java.util.List;
import engine.components.Camera;

public class GameObject {
    
    public Transform transform;    
    public List<Component> components;

    public GameObject() {
        transform = new Transform();
        components = new ArrayList<>();
    }
    
    public void attachComponent (Component newComponent) {
        components.add(newComponent);
        newComponent.setParent(this);
    }
    
    public Component getComponent (Class<?> targetComponentType) {
        for (Component comp : components) {
            if (targetComponentType.isInstance(comp)) {
                return comp;
            }
        }
        return null;
    }
    
}
