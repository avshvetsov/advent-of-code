package org.shvetsov.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Output implements Module {

    private final String name;
    private final List<String> receiverNames = new ArrayList<>();
    private final List<Module> receivers = new ArrayList<>();
    private final List<Module> inputs = new ArrayList<>();

    public Output(String name) {
        this.name = name;
    }

    @Override
    public List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType pulse) {
        return Collections.emptyList();
    }

    public void addInput(Module module) {
        inputs.add(module);
    }
}
