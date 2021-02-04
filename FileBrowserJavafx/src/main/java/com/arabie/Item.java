package com.arabie;

public class Item {
    private String root;
    private String name;
    private boolean isFile;

    public String getRoot() {
        return root;
    }

    public Item(String root, String name, boolean isFile) {
        this.root = root;
        this.name = name;
        this.isFile = isFile;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setIsFile(boolean file) {
        isFile = file;
    }

    @Override
    public String toString() {
        return "Item{" +
                "root='" + root + '\'' +
                ", name='" + name + '\'' +
                ", isFile=" + isFile +
                '}';
    }
}
