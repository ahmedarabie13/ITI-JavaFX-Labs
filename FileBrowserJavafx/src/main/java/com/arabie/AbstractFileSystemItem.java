package com.arabie;

public abstract class AbstractFileSystemItem {
//    private AbstractFileSystemItem parent;
    private String name;

    public AbstractFileSystemItem(String name) {
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
