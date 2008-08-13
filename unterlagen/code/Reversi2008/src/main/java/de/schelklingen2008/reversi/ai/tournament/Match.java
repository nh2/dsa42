package de.schelklingen2008.reversi.ai.tournament;

import java.util.ArrayList;
import java.util.List;

import de.schelklingen2008.reversi.ai.strategy.ReversiStrategy;
import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class Match
{

    public static final int     POINTS_DRAW = 1;
    public static final int     POINTS_WIN  = 2;
    private GameModel           model       = new GameModel();
    private ReversiStrategy     white;
    private ReversiStrategy     black;
    private Player              winner;
    private int                 countWhite;
    private int                 countBlack;
    private long                maxConsiderationTimeWhite;
    private long                maxConsiderationTimeBlack;
    private List<MatchObserver> observers   = new ArrayList<MatchObserver>();

    public Match(ReversiStrategy white, ReversiStrategy black)
    {
        this.white = white;
        this.black = black;
    }

    public void execute()
    {
        if (winner != null) throw new IllegalStateException("match already executed");

        while (!model.isFinished())
            move(model.getTurnHolder());

        winner = model.getWinner();
        countWhite = model.countPieces(Player.WHITE);
        countBlack = model.countPieces(Player.BLACK);
        model = null;
        notifyFinished();
    }

    public Player getWinner()
    {
        return winner;
    }

    public int getCountWhite()
    {
        return countWhite;
    }

    public int getCountBlack()
    {
        return countBlack;
    }

    public int getPointsWhite()
    {
        if (!isFinished()) return 0;
        if (winner == Player.WHITE) return POINTS_WIN;
        if (winner == null) return POINTS_DRAW;
        return 0;
    }

    public int getPointsBlack()
    {
        if (!isFinished()) return 0;
        if (winner == Player.BLACK) return POINTS_WIN;
        if (winner == null) return POINTS_DRAW;
        return 0;
    }

    private void move(Player player)
    {
        long started = System.currentTimeMillis();
        Piece move = getStrategy(player).move(new GameModel(model));
        long finished = System.currentTimeMillis();

        setMaxConsiderationTime(finished - started, player);
        model.placePiece(move.getX(), move.getY(), player);
    }

    private void setMaxConsiderationTime(long time, Player player)
    {
        if (Player.WHITE == player) maxConsiderationTimeWhite = Math.max(time, maxConsiderationTimeWhite);
        if (Player.BLACK == player) maxConsiderationTimeBlack = Math.max(time, maxConsiderationTimeBlack);
    }

    private ReversiStrategy getStrategy(Player player)
    {
        if (Player.WHITE == player) return white;
        if (Player.BLACK == player) return black;
        return null;
    }

    public long getMaxConsiderationTime(Player player)
    {
        if (Player.WHITE == player) return maxConsiderationTimeWhite;
        if (Player.BLACK == player) return maxConsiderationTimeBlack;
        return -1;
    }

    public boolean isFinished()
    {
        return model == null;
    }

    public void addObserver(MatchObserver observer)
    {
        observers.add(observer);
    }

    private void notifyFinished()
    {
        for (MatchObserver observer : observers)
            observer.matchFinished();
    }
}
