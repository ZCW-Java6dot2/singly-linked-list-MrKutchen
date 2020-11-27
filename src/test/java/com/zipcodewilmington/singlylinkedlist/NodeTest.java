package com.zipcodewilmington.singlylinkedlist;

import org.junit.Assert;
import org.junit.Test;

public class NodeTest {
    @Test
    public void constructorTest(){
        Integer test = 5;
        Node node = new Node(5, null);

        Assert.assertEquals(5, node.getDataValue());
        Assert.assertEquals(null, node.getNextValue());
    }

    @Test
    public void setterAndGetterIndexTest(){
        int index = 5;
        Node node = new Node(index, null);
        node.setIndex(index);

        Assert.assertEquals(index, node.getIndex());
    }

    @Test
    public void setDataValue(){
        Integer obj = 5;
        Node node = new Node(obj, null);
        Integer newObj = 10;
        node.setDataValue(newObj);

        Assert.assertEquals(newObj, node.getDataValue());
    }
}