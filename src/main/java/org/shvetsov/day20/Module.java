package org.shvetsov.day20;

import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Map;

public interface Module {

    String getName();

    List<String> getReceiverNames();

    List<Module> getReceivers();

    List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType pulse);

    default void populateReceivers(Map<String, Module> modules) {
        for (String name : getReceiverNames()) {
            Module receiver = modules.getOrDefault(name, new Output(name));
            getReceivers().add(receiver);
            if (receiver instanceof Conjunction conjunction) {
                conjunction.addInput(this);
            }
            if (receiver instanceof Output output) {
                output.addInput(this);
            }
        }
    }
}
