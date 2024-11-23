package org.shvetsov.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Broadcast implements Module {

    private final String name;
    private final List<String> receiverNames;
    private final List<Module> receivers = new ArrayList<>();

    public Broadcast(String name, List<String> receiverNames) {
        this.name = name;
        this.receiverNames = receiverNames;
    }

    @Override
    public List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType pulse) {
        List<Triple<Module, Module, PulseType>> results = new ArrayList<>();
        for (Module receiver : receivers) {
            results.add(Triple.of(this, receiver, pulse));
        }
        return results;
    }
}
