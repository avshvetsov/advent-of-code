package org.shvetsov.day8;

import lombok.Getter;

import java.util.Arrays;

public class UnionFind {

    private final int[] parent;
    @Getter
    private final int[] size;
    @Getter
    private int groups;

    public UnionFind(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
        }
        this.size = new int[size];
        Arrays.fill(this.size, 1);
        this.groups = size;
    }

    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP != rootQ) {
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            groups--;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
