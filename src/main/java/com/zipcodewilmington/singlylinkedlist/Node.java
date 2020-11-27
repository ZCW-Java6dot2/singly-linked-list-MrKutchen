package com.zipcodewilmington.singlylinkedlist;

public class Node<SomeType> {

    private SomeType dataValue;
    private Node nextValue;
    private int index = 0;

    public Node(SomeType dataValue, Node nextValue) {
        this.dataValue = dataValue;
        this.nextValue = nextValue;
    }

    public SomeType getDataValue() {
        return dataValue;
    }

    public void setDataValue(SomeType dataValue) {
        this.dataValue = dataValue;
    }

    public Node getNextValue() {
        return nextValue;
    }

    public void setNextValue(Node nextValue) {
        this.nextValue = nextValue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
