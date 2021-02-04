package com.arabie;

import java.util.ArrayList;

public class FolderItem extends AbstractFileSystemItem {
    private ArrayList<AbstractFileSystemItem> children =new ArrayList<>();

    public FolderItem(String name) {
        super(name);
    }

    public ArrayList<AbstractFileSystemItem> getChildren() {
        ArrayList<AbstractFileSystemItem> childsLst= new ArrayList<>();
        for(AbstractFileSystemItem FSI:children){
            childsLst.add(FSI);
        }
        return childsLst;
    }

    public void addChild(AbstractFileSystemItem child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
