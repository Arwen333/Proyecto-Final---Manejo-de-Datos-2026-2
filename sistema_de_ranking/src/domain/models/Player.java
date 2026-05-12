package domain.models;

import java.util.UUID;

public class Player {
    private final String _id;
    private String _name;
    private int _level;
    private float _totalScore;

    public Player() {
        super();
        _id = UUID.randomUUID().toString();
    }

}
