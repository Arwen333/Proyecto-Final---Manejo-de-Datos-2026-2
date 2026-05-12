package domain.models;

import java.sql.Date;
import java.util.UUID;

public class Match {
    private final String _id;
    private Player _player1;
    private Player _player2;
    private float _score;
    private Date _date;

    public Match(Player player1, Player player2, Date date) {
        super();
        _id = UUID.randomUUID().toString();
        _player1 = player1;
        _player1 = player2;
        _score = 0;
        _date = date;
    }
}
