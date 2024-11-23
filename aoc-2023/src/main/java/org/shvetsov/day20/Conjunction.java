package org.shvetsov.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Conjunction implements Module {

    private final String name;
    private final List<String> receiverNames;
    private final List<Module> receivers = new ArrayList<>();

    private final Map<String, PulseType> inputMap = new HashMap<>();

    public Conjunction(String name, List<String> receiverNames) {
        this.name = name;
        this.receiverNames = receiverNames;
    }


    @Override
    public List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType pulse) {
        List<Triple<Module, Module, PulseType>> results = new ArrayList<>();
        PulseType produce = evaluatePulseType(source, pulse);
        for (Module receiver : receivers) {
            results.add(Triple.of(this, receiver, produce));
        }
        return results;
    }

    private PulseType evaluatePulseType(Module source, PulseType pulse) {
        inputMap.put(source.getName(), pulse);
        if (pulse == PulseType.LOW) {
            return PulseType.HIGH;
        } else {
            return inputMap.values().stream()
                    .allMatch(pulseType -> pulseType == PulseType.HIGH) ? PulseType.LOW : PulseType.HIGH;
        }
    }

    public void addInput(Module module) {
        inputMap.put(module.getName(), PulseType.LOW);
    }
}
