package org.shvetsov.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FlipFlop implements Module {

    private final String name;
    private final List<String> receiverNames;
    private final List<Module> receivers = new ArrayList<>();

    private boolean state;

    public FlipFlop(String name, List<String> receiverNames) {
        this.name = name;
        this.receiverNames = receiverNames;
        this.state = false;
    }

    @Override
    public List<Triple<Module, Module, PulseType>> pulse(Module source, PulseType pulse) {
        List<Triple<Module, Module, PulseType>> results = new ArrayList<>();
        switch (pulse) {
            case LOW -> {
                PulseType produce = state ? PulseType.LOW : PulseType.HIGH;
                for (Module receiver : receivers) {
                    results.add(Triple.of(this, receiver, produce));
                }
                state ^= true;
            }
            case HIGH -> {
                //ignored
            }
            default -> throw new IllegalArgumentException("Invalid pulse type: " + pulse);
        }
        return results;
    }
}
