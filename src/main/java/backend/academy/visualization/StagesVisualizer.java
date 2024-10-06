package backend.academy.visualization;

public enum StagesVisualizer {

    Stage_0(0),
    Stage_1(1),
    Stage_2(2),
    Stage_3(3),
    Stage_4(4),
    Stage_5(5),
    Stage_6(6);

    private final int value;

    StagesVisualizer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
