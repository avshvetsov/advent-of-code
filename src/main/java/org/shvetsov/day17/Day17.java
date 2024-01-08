package org.shvetsov.day17;

import com.google.common.primitives.Chars;
import org.shvetsov.utils.Direction;
import org.shvetsov.utils.Grid;
import org.shvetsov.utils.Point;

import java.util.*;

/**
 * <a href="https://adventofcode.com/2023/day/17">Day 17</a>
 */
public class Day17 {

    public static void main(String[] args) {

    }

    public int partOneAnton(List<String> inputList) {
        TrafficMap trafficMap = TrafficMap.ofCityBlock(inputList);
        Point start = Point.of(0, 0);
        Point end = Point.of(trafficMap.sizeRow() - 1, trafficMap.sizeColumn() - 1);
        trafficMap.dijkstra(start, end);
        return trafficMap.getValue(end).getMinHeatLossFromStart();
    }

    public int partTwoAnton(List<String> inputList) {

        return 0;
    }

    public static class TrafficMap extends Grid<CityBlock> {


        private TrafficMap(List<List<CityBlock>> lists) {
            super(lists);
        }

        public static TrafficMap ofCityBlock(List<String> lines) {
            List<List<CityBlock>> nodeList = new ArrayList<>();
            for (String line : lines) {
                List<CityBlock> list = new ArrayList<>();
                List<Character> characters = Chars.asList(line.toCharArray());
                for (Character character : characters) {
                    Integer distance = Character.getNumericValue(character);
                    CityBlock cityBlock = new CityBlock(distance);
                    list.add(cityBlock);
                }
                nodeList.add(list);
            }
            return new TrafficMap(nodeList);
        }

        public void updateCityBlock(Node node) {
            CityBlock cityBlock = getValue(node.getKey().point());
            if (cityBlock.getMinHeatLossFromStart() == null) {
                cityBlock.setMinHeatLossFromStart(node.getMinHeatLossFromStart());
                //Добавить алгоритм заполнения маршрута, на основании prev нод
            }
        }

        public void dijkstra(Point start, Point end) {
            //Ноды состоят из Key, minHeatLossFromStart, prevNode
            //Key состоит из Point, Direction, к-ва шагов в одном направлении
            //Генерируем первые 2 Ноды и добавляем их в очередь
            //Читаем очерель пока в ней есть Ноды
            //Проверяем читали ли мы такую ноду ранее (проверка по точке, направлению и к-во шагов в одном направлении)
                //если да, читаем след ноду
                //если нет, записываем ноду в прочтенные
            //По Point проверяем, записано ли minHeatLossFromStart в CityBlock с тем же Point
                //если нет, записываем minHeatLossFromStart в CityBlock
            //По Point проверяем, является ли Point концом
                //если да, return
            //Генерируем допустимых соседей Ноды и добавляем их в очередь
            //возвращаемся к строке 55 "Читаем очередь пока в ней есть Ноды"

            Queue<Node> queue = new PriorityQueue<>();
            HashSet<Node> visited = new HashSet<>();
            //Заполняем стартовую ноду
            Node startNode = new Node(new Key(start, null, 0), 0, null);
            updateCityBlock(startNode);

            //Формируем следующие ноды и добавляем их в очередь
            Node initNode1 = startNode.buildNewNode(this, Direction.SOUTH);
            Node initNode2 = startNode.buildNewNode(this, Direction.EAST);
            queue.add(initNode1);
            queue.add(initNode2);

            //Цикл, пока ноды не закончатся
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                if (visited.contains(current)) {
                    continue;
                } else visited.add(current);

                updateCityBlock(current);

                Point currentPoint = current.getKey().point();
                if (currentPoint.equals(end)) {
                    return;
                }

                getNeighbourDirections(currentPoint).stream()
                        .filter(direction -> filterPart1(current, direction))
                        .forEach(direction -> queue.add(current.buildNewNode(this, direction)));
            }
        }

        private boolean filterPart1(Node current, Direction direction) {
            Direction currentDirection = current.getKey().direction();
            if (direction.equals(currentDirection.opposite())) {
                return false;
            }
            if (direction.equals(currentDirection) && current.getKey().line() >= 3) {
                return false;
            }
            return true;
        }


    }

}