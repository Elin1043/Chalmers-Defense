package com.mygdx.chalmersdefense;

public class Vectors {
    public float x;
    public float y;

    public Vectors(float x, float y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object o){

        if (o != null){
            if (o instanceof Vectors){
                Vectors test = (Vectors)o;
                return test.x == this.x && test.y == this.y;
            }
        }
        return false;
    }


}
