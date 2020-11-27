package com.zipcodewilmington.singlylinkedlist;

/**
 * Created by leon on 1/10/18.
 */
public class SinglyLinkedList<SomeType extends Comparable<SomeType>> {
    private Node<SomeType> tail;
    private Node<SomeType> head;
    private int length;

    public SinglyLinkedList() {
        this.tail = null;
        this.head = null;
        this.length = 0;
    }

    public SomeType get(int index){
        int tempIndex = head.getIndex();
        Node<SomeType> tempNode = head;
        while (tempIndex != index){
            tempIndex++;
            tempNode = tempNode.getNextValue();
        }
        return tempNode.getDataValue();
    }

    public void set(int index, SomeType obj){
        int tempIndex = head.getIndex();
        Node<SomeType> tempNode = head;
        while (tempIndex != index){
            tempIndex++;
            tempNode = tempNode.getNextValue();
        }
        tempNode.setDataValue(obj);
    }

    public void swap(SomeType dataValue1, SomeType dataValue2){
        Node<SomeType> tempNode = head;
        int indexDataValue1 = -1;
        int indexDataValue2 = -1;
        while (tempNode != null){
            if (tempNode.getDataValue() == dataValue1)
                indexDataValue1 = tempNode.getIndex();
            else if (tempNode.getDataValue() == dataValue2)
                indexDataValue2 = tempNode.getIndex();
            tempNode = tempNode.getNextValue();
        }
        this.set(indexDataValue2, dataValue1);
        this.set(indexDataValue1, dataValue2);
    }

    public void addFirstElement(SomeType dataValue){
        head = new Node<SomeType>(dataValue, null);
        head.setIndex(0);
    }

    public void addSecondElement(SomeType dataValue){
        tail = new Node<SomeType>(dataValue, null);
        head.setNextValue(tail);
        tail.setIndex(1);
    }


    public void addThirdElementOnwards(SomeType dataValue){
        int nextIndex = tail.getIndex() + 1;
        tail.setNextValue(new Node<SomeType>(dataValue, null));
        tail = tail.getNextValue();
        tail.setIndex(nextIndex);
    }

    public void add(SomeType dataValue){
        if (head == null)
            addFirstElement(dataValue);
        else if (tail == null)
            addSecondElement(dataValue);
        else
            addThirdElementOnwards(dataValue);
        length++;
    }

    public void shiftIndex(Node<SomeType> node, int currentIndex){
        while (node != null){
            node.setIndex(currentIndex);
            node = node.getNextValue();
            currentIndex++;
        }
    }

    public void removeFirstIndex(){
        Node<SomeType> newHead = head.getNextValue();
        head.setDataValue(null);
        head = newHead;
        head.setIndex(0);
        shiftIndex(tail.getNextValue(), 0);
    }

    public void removeSecondIndex(){
        Node<SomeType> tempNode = head.getNextValue();
        Node<SomeType> nextNode = tempNode.getNextValue();
        head.setNextValue(nextNode);
        nextNode.setIndex(1);
        tempNode.setNextValue(null);
        shiftIndex(head.getNextValue(), 1);
    }

    public void removeInTheMiddle(Node<SomeType> tempNode, Node<SomeType> toReplace, int index){
        Node<SomeType> toConnect = toReplace.getNextValue();
        tempNode.setNextValue(toConnect);
        toReplace.setNextValue(null);
        toConnect.setIndex(index + 1);
        shiftIndex(toConnect.getNextValue(), toConnect.getIndex());
    }

    public Node<SomeType> findNodeToReconnect(Node<SomeType> tempNode, int index) {
        while (tempNode.getIndex() != index - 1){
            tempNode = tempNode.getNextValue();
        }
        return tempNode;
    }

    public void removeOtherIndex(int index){
        Node<SomeType> tempNode = head.getNextValue();
        tempNode = findNodeToReconnect(tempNode, index);
        int tempIndex = tempNode.getIndex();
        Node<SomeType> toReplace = tempNode.getNextValue();
        if (toReplace.getNextValue() == null){
            tempNode.setNextValue(null);
        }
        else {
            removeInTheMiddle(tempNode, toReplace, tempIndex);
        }
    }

    public void remove(int index){
        if (index == 0 && this.size() == 1)
            removeLastElement();
        else if (index == 0)
            removeFirstIndex();
        else if (index == 1)
            removeSecondIndex();
        else
            removeOtherIndex(index);
        length--;
    }

    private void removeLastElement() {
        head = null;
    }

    public boolean contains(SomeType obj){
        Node<SomeType> tempNode = head;
        if (head == null)
            return false;
        else
            for (int i = 0; i < this.size(); i++) {
                if(tempNode.getDataValue() == obj)
                    return true;
                else
                    tempNode = tempNode.getNextValue();
            }
        return false;
    }

    public int size(){
        return length;
    }

    public int find(SomeType obj){
        Node<SomeType> tempNode = head;
        int nodeCount = 0;
        if (head == null)
            return -1;
        while (tempNode != null){
            if (tempNode.getDataValue() == obj)
                return nodeCount;
            nodeCount++;
            tempNode = tempNode.getNextValue();
        }
        return -1;
    }

    public SinglyLinkedList<SomeType> copySize(){
        SinglyLinkedList<SomeType> copy = new SinglyLinkedList<SomeType>();
        Node<SomeType> tempNode = head;
        while (tempNode != null){
            copy.add(tempNode.getDataValue());
            tempNode = tempNode.getNextValue();
        }
        return copy;
    }

    public SinglyLinkedList<SomeType> copy(){
        int copySize = this.size();
        if (copySize == 0)
            return new SinglyLinkedList<SomeType>();
        else
            return copySize();
    }

    public SinglyLinkedList<SomeType> slice(int indexStartInclusive, int indexEndExclusive){
        SinglyLinkedList<SomeType> copy = this.copy();
        for (int i = 0; i < copy.size(); i++) {
            if (i < indexStartInclusive || i >= indexEndExclusive){
                copy.remove(i);
            }
        }
        return copy;
    }

    public void reverse(){
        int size = this.size();
        for (int i = 0; i < size; i++) {
            this.add(this.get(size - 1 - i));
        }
        for (int i = 0; i < size; i++) {
            this.remove(0);
        }
    }

    public void bubbleSort(){
        for (int i = 0; i < length - 1; i++) {
            SomeType currentI = get(i);
            for (int j = i + 1; j < length; j++) {
                SomeType currentJ = get(j);
                if (currentI.compareTo(currentJ) > 0){
                    swap(currentI, currentJ);
                    currentI = currentJ;
                }
            }
        }
    }
}
