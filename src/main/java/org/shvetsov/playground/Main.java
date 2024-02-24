package org.shvetsov.playground;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.shvetsov.playground.helpers.Owner;
import org.shvetsov.playground.helpers.Owners;
import org.shvetsov.playground.tasks.Task;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    private static String IMPLEMENTATIONS_PATH = "org.shvetsov.playground.implementations";

    @SneakyThrows
    public static void main(String[] args) {
        //
        Set<Class<? extends Task>> tasks = new Reflections(IMPLEMENTATIONS_PATH).getSubTypesOf(Task.class);
        Map<Owners, List<Class<? extends Task>>> collect = tasks.stream()
                .collect(groupingBy(clazz -> clazz.getAnnotation(Owner.class).value()));

        tasks.forEach(task -> {
            try {
                org.openjdk.jmh.Main.main(new String[]{"org.shvetsov.day19.Benchmarks.*", "input="});
            } catch (Exception ignored) {}
        });

        org.openjdk.jmh.Main.main(new String[]{"org.shvetsov.day19.Benchmarks.*"});
    }
}
