package org.shvetsov.playground.tasks;

public interface Task<IN, OUT> {
    OUT run(IN inStructure);
    IN getTestInput();
}
