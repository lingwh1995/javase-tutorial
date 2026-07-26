package headfirst.designpatterns.templatemethod.sort;

/**
 * 鸭子
 *
 * @author lingwh
 * @date 2023/12/7 10:16
 */
public class Duck implements Comparable<Duck> {

    String name;
    int weight;

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String toString() {
        return name + " weighs " + weight;
    }

    public int compareTo(Duck object) {

        Duck otherDuck = object;

        if (this.weight < otherDuck.weight) {
            return -1;
        } else if (this.weight == otherDuck.weight) {
            return 0;
        } else { // this.weight > otherDuck.weight
            return 1;
        }
    }
}
