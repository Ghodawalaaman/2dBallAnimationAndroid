package com.example.a2dgraphicinandroid;

public class V2 {
    float x;
    float y;

    public V2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public V2 add(V2 that){
        return new V2(this.x + that.x,this.y + that.y);
    }

    public V2 sub(V2 that){
        return new V2(this.x - that.x,this.y - that.y);
    }

    public int length(){
        return (int) Math.sqrt(this.x * this.x + this.y + this.y);
    }

    public V2 normalize(){
        return new V2(this.x / length(),this.y / length());
    }

    public V2 scale(float s){
        return new V2(this.x * s,this.y * s);
    }

}
