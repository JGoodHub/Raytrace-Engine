package engine.components;

import engine.GameObject;

public class Component {

    public GameObject parent;
    
    public void setParent (GameObject parentObject) {
        parent = parentObject;
    }


}