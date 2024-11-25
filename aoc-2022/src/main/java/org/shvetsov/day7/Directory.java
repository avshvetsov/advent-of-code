package org.shvetsov.day7;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Directory implements SystemItem{

    private String path;
    private List<SystemItem> children;
    private long size;

    public Directory(String path) {
        this.path = path;
        this.children = new ArrayList<>();
    }

    @Override
    public void calculateSize() {
        for (SystemItem child : children) {
            child.calculateSize();
            this.size += child.getSize();
        }
    }
}
