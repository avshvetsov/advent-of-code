package org.shvetsov.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class Button implements Module {

    private final String name;
    private final List<String> receiverNames;
    private final List<Module> receivers = new ArrayList<>();

    public Button(String name, List<String> receiverNames, Map<String, Module> modules) {
        this.name = name;
        this.receiverNames = receiverNames;
        populateReceivers(modules);
    }

    @Override
    public List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType ignored) {
        List<Triple<Module, Module, PulseType>> results = new ArrayList<>();
        for (Module receiver : receivers) {
            results.add(Triple.of(this, receiver, PulseType.LOW));
        }
        return results;
    }
}
