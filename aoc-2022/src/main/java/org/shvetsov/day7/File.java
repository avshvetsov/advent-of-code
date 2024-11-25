package org.shvetsov.day7;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class File implements SystemItem {

    private String path;
    private long size;

    public File(String path, long size) {
        this.path = path;
        this.size = size;
    }

    @Override
    public void calculateSize() {

    }
}
