package org.shvetsov.day19;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * <a href="https://adventofcode.com/2023/day/19">Day 19</a>
 */
public class Day19 {


    public static void main(String[] args) {

    }

    //Part 1
    public long partOneAnton(List<String> inputList) {
        Map<String, String> rulesMap = new HashMap<>();
        List<Part> parts = new ArrayList<>();
        boolean isRule = true;
        for (String s : inputList) {
            if (s.isBlank()) {
                isRule = false;
                continue;
            }
            if (isRule) {
                rulesMap.put(
                        StringUtils.substringBefore(s, "{"),
                        StringUtils.substringBetween(s, "{", "}")
                );
            } else {
                parts.add(Part.ofString(s));
            }
        }

        RuleTree ruleTree = new RuleTree(rulesMap);
        long sum = parts.stream()
                .filter(part -> ruleTree.testPart(part) == Result.ACCEPT)
                .mapToLong(Part::sum)
                .sum();
        return sum;
    }

    private static class RuleTree {
        private static final String INPUT_NODE_NAME = "in";
        private static final Integer INPUT_RULE_NUM = 0;
        private final RuleNode head;

        public RuleTree(Map<String, String> rulesMap) {
            this.head = new RuleNode(INPUT_NODE_NAME, INPUT_RULE_NUM, rulesMap);
        }

        public Result testPart(Part part) {
            return this.head.test(part);
        }

        private static class RuleNode {
            private static final BiFunction<Part, String, Integer> GET_PART_VAR_VALUE =
                    (part, varName) -> switch (varName) {
                        case "x" -> part.x();
                        case "m" -> part.m();
                        case "a" -> part.a();
                        case "s" -> part.s();
                        case null, default -> throw new IllegalArgumentException();
                    };
            private final String name;
            private final Integer num;
            @Getter
            private final Predicate<Part> predicate;
            private final RuleNode trueNode;
            private final RuleNode falseNode;
            @Getter
            private final Result result;

            public RuleNode(String name, Integer num, Map<String, String> rulesMap) {
                String rule = rulesMap.get(name);
                while (rule != null && !rule.split(",")[num].contains(":")) {
                    name = rule.split(",")[num];
                    num = 0;
                    rule = rulesMap.get(name);
                }

                this.name = name;
                this.num = num;
                if (rule == null) {
                    this.result = Result.ofSign(name);
                    this.predicate = null;
                    this.trueNode = null;
                    this.falseNode = null;
                } else {
                    this.result = null;
                    String subRule = rule.split(",")[num];
                    String condition = StringUtils.substringBefore(subRule, ":");
                    String variableName = condition.substring(0, 1);
                    Operand operand = Operand.ofSign(condition.substring(1, 2));
                    Integer value = Integer.valueOf(condition.substring(2));
                    this.predicate = part -> operand.getOperator().apply(GET_PART_VAR_VALUE.apply(part, variableName), value);
                    this.trueNode = new RuleNode(StringUtils.substringAfter(subRule, ":"), 0, rulesMap);
                    this.falseNode = new RuleNode(name, num + 1, rulesMap);
                }
            }

            public Result test(Part part) {
                RuleNode nextNode = this.predicate.test(part) ? this.trueNode : this.falseNode;
                return nextNode.getResult() != null ? nextNode.getResult() : nextNode.test(part);
            }
        }
    }


    private record Part(int x, int m, int a, int s) {

        private static Part ofString(String partString) {
            String[] split = partString.substring(1, partString.length() - 1).split(",");
            return new Part(
                    Integer.parseInt(StringUtils.substringAfter(split[0], "=")),
                    Integer.parseInt(StringUtils.substringAfter(split[1], "=")),
                    Integer.parseInt(StringUtils.substringAfter(split[2], "=")),
                    Integer.parseInt(StringUtils.substringAfter(split[3], "="))
            );
        }

        private long sum() {
            return x + m + a + s;
        }
    }


    // Part 2
    // Работает для частного случая рассчета комбинаций, которые не пересекаются друг с другом.
    // Не будет работать, если результирующие правила могут пересекаться между собой.
    // Закомментированный код метода может рассчитывать пересечения, которые нужно вычитать из общей суммы комбинаций.
    // Закомментированный код не учитывает множественное пересечение правил. Если правило 3 пересекается с местом пересечения правил 1 и 2,
    // тогда мы вычтем место пересечения дважды. Нужно обрабатывать этот случай путем сохранения вместе с обработанными правилами их пересечений.
    // И тогда можно снова прибавить пересечения, которые были вычтены.
    public long partTwoAnton(List<String> inputList) {
        Map<String, String> rulesMap = new HashMap<>();
        for (String s : inputList) {
            if (s.isBlank()) {
                break;
            }
            rulesMap.put(
                    StringUtils.substringBefore(s, "{"),
                    StringUtils.substringBetween(s, "{", "}")
            );
        }

        RulesHolder rulesHolder = new RulesHolder(rulesMap);
        rulesHolder.populate(new Rule(), "in", 0);

        List<Rule> acceptedRules = rulesHolder.getRules().stream()
                .filter(rule -> rule.getResult() == Result.ACCEPT)
                .toList();

        long res = 0;

//        List<Rule> processed = new ArrayList<>();
        for (Rule acceptedRule : acceptedRules) {
            long combinations = acceptedRule.combinations();
//            long crossedCombinations = 0;
//            for (Rule processedRule : processed) {
//                Rule crossed = Rule.crossedOf(acceptedRule, processedRule);
//                crossedCombinations += crossed.combinations();
//            }
//            processed.add(acceptedRule);
            res += combinations;
        }
        return res;
    }


    private static class RulesHolder {
        @Getter
        private final List<Rule> rules = new ArrayList<>();
        private final Map<String, String> rulesMap;

        public RulesHolder(Map<String, String> rulesMap) {
            this.rulesMap = rulesMap;
        }

        public void populate(Rule rule, String name, Integer num) {
            String ruleString = rulesMap.get(name);
            // Если правило найдено и в sub-правиле по номеру нет символа ":", то переходим к следующему правилу по его имени
            if (ruleString != null && !ruleString.split(",")[num].contains(":")) {
                populate(rule, ruleString.split(",")[num], 0);
                return;
            }

            // Тут любое правило:
            // - или не найено в rulesMap, и тогда его name - это результат A/R. Задаем значение в result и сохраняем в rules;
            // - или содержит ":" в sub-правиле. К существующему правилу добавляем новое для true и для false -> рекурсивно вызываем populate();
            if (ruleString == null) {
                rule.setResult(Result.ofSign(name));
                rules.add(rule);
            } else {
                String subRule = ruleString.split(",")[num];
                String condition = StringUtils.substringBefore(subRule, ":");
                String variableName = condition.substring(0, 1);
                Operand operandTrue = Operand.ofSign(condition.substring(1, 2));
                Operand operandFalse = Operand.ofSign(Operand.opposite(condition.substring(1, 2)));
                Integer value = Integer.valueOf(condition.substring(2));

                Rule trueRule = rule.addRule(variableName, operandTrue, value);
                Rule falseRule = rule.addRule(variableName, operandFalse, value);

                populate(trueRule, StringUtils.substringAfter(subRule, ":"), 0);
                populate(falseRule, name, num + 1);
            }
        }
    }

    @Getter
    private static class Rule {
        private static final BiFunction<Rule, String, BitSet> GET_RULE_BITSET_BY_NAME =
                (rule, varName) -> switch (varName) {
                    case "x" -> rule.getX();
                    case "m" -> rule.getM();
                    case "a" -> rule.getA();
                    case "s" -> rule.getS();
                    case null, default -> throw new IllegalArgumentException();
                };

        private final BitSet x;
        private final BitSet m;
        private final BitSet a;
        private final BitSet s;
        @Setter
        private Result result;

        public Rule() {
            BitSet x = new BitSet(Constants.MAX_PART_VAL);
            x.set(0, Constants.MAX_PART_VAL);
            this.x = x;
            BitSet m = new BitSet(Constants.MAX_PART_VAL);
            m.set(0, Constants.MAX_PART_VAL);
            this.m = m;
            BitSet a = new BitSet(Constants.MAX_PART_VAL);
            a.set(0, Constants.MAX_PART_VAL);
            this.a = a;
            BitSet s = new BitSet(Constants.MAX_PART_VAL);
            s.set(0, Constants.MAX_PART_VAL);
            this.s = s;
            this.result = null;
        }

        public Rule(Rule rule) {
            this.x = (BitSet) rule.getX().clone();
            this.m = (BitSet) rule.getM().clone();
            this.a = (BitSet) rule.getA().clone();
            this.s = (BitSet) rule.getS().clone();
            this.result = rule.getResult();
        }

        public static Rule crossedOf(Rule r1, Rule r2) {
            Rule rule1 = new Rule(r1);
            rule1.getX().and(r2.getX());
            rule1.getM().and(r2.getM());
            rule1.getA().and(r2.getA());
            rule1.getS().and(r2.getS());
            return new Rule(rule1);
        }

        public Rule addRule(String varName, Operand operand, Integer value) {
            Rule newRule = new Rule(this);
            operand.getBitSetTransformator().apply(GET_RULE_BITSET_BY_NAME.apply(newRule, varName), value);
            return newRule;
        }

        public long combinations() {
            return (long) x.cardinality() * m.cardinality() * a.cardinality() * s.cardinality();
        }

    }
}