package domain.models;

import java.util.UUID;

public class Match {
    private final String _id;
    private Player _player1;
    private Player _player2;

    public Match(Player player1, Player player2) {
        super();
        _id = UUID.randomUUID().toString();
        _player1 = player1;
        _player1 = player2;
    }
}
