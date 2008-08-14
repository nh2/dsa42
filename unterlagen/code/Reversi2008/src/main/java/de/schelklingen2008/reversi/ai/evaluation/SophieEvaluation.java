package de.schelklingen2008.reversi.ai.evaluation;

import java.util.Set;

import de.schelklingen2008.reversi.model.GameModel;
import de.schelklingen2008.reversi.model.Piece;
import de.schelklingen2008.reversi.model.Player;

public class SophieEvaluation implements EvaluationFunction
{

    public int evaluatePosition(GameModel gameModel, Player player)
    {
        int diff = gameModel.countPieces(player) - gameModel.countPieces(player.other());
        int safePieces = countSafePieces(gameModel, player);
        Set<Piece> legalMoves = gameModel.getLegalMovesSet(player.other());
        if (legalMoves.isEmpty()) diff += 10;
        diff += safePieces;

        return diff;
    }

    private int countSafePieces(GameModel gamelModel, Player player)
    {
        int safePieceCounter = 0;

        boolean[][] safePieces = getAllSafePieces(gamelModel, player);

        for (int x = 0; x < safePieces.length; x++)
        {
            for (int y = 0; y < safePieces.length; y++)
            {
                if (safePieces[x][y]) safePieceCounter++;
            }
        }

        return safePieceCounter;
    }

    private boolean[][] getAllSafePieces(GameModel gameModel, Player player)
    {
        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];
        initializeBooleanArray(safePieces);
        boolean[][] safePiecesUpLeft = getSafePiecesUpLeft(gameModel, player);
        boolean[][] safePiecesUpRight = getSafePiecesUpRight(gameModel, player);
        boolean[][] safePiecesDownLeft = getSafePiecesDownLeft(gameModel, player);
        boolean[][] safePiecesDownRight = getSafePiecesDownRight(gameModel, player);

        for (int x = 0; x < safePieces.length; x++)
        {
            for (int y = 0; y < safePieces.length; y++)
            {
                if (safePiecesDownLeft[x][y]
                    || safePiecesDownRight[x][y]
                    || safePiecesUpLeft[x][y]
                    || safePiecesUpRight[x][y]) safePieces[x][y] = true;
            }
        }

        return safePieces;
    }

    private boolean[][] getSafePiecesUpLeft(GameModel model, Player player)
    {

        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];
        initializeBooleanArray(safePieces);
        Player[][] board = model.getBoard();

        int y = 0;
        int x = 0;
        int lastRowPiece = 0;

        while (board[x][y] == player)
        {

            while (board[x][y] == player)
            {
                if (x > lastRowPiece) break;
                safePieces[x][y] = true;
                x++;
                if (x >= board.length) break;
            }
            lastRowPiece = x;
            x = 0;
            y++;
            if (y >= board.length) break;
        }

        return safePieces;

    }

    private boolean[][] getSafePiecesDownLeft(GameModel model, Player player)
    {

        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];
        initializeBooleanArray(safePieces);
        Player[][] board = model.getBoard();

        int y = GameModel.SIZE - 1;
        int x = 0;
        int lastRowPiece = 0;

        while (board[x][y] == player)
        {

            while (board[x][y] == player)
            {
                if (x > lastRowPiece) break;
                safePieces[x][y] = true;

                x++;
                if (x >= board.length) break;
            }
            lastRowPiece = x;
            x = 0;
            y--;
            if (y <= 0) break;
        }

        return safePieces;

    }

    private boolean[][] getSafePiecesDownRight(GameModel model, Player player)
    {

        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];
        initializeBooleanArray(safePieces);
        Player[][] board = model.getBoard();

        int y = GameModel.SIZE - 1;
        int x = GameModel.SIZE - 1;
        int lastRowPiece = 0;

        while (board[x][y] == player && y >= 0)
        {

            while (board[x][y] == player && x > 0)
            {
                if (x < lastRowPiece) break;
                safePieces[x][y] = true;
                x--;
                if (x <= 0) break;
            }
            lastRowPiece = x;
            x = 6;
            y--;
            if (y <= 0) break;
        }

        return safePieces;

    }

    private boolean[][] getSafePiecesUpRight(GameModel model, Player player)
    {

        boolean[][] safePieces = new boolean[GameModel.SIZE][GameModel.SIZE];
        initializeBooleanArray(safePieces);
        Player[][] board = model.getBoard();

        int y = 0;
        int x = GameModel.SIZE - 1;
        int lastRowPiece = 0;

        while (board[x][y] == player && y < GameModel.SIZE)
        {

            while (board[x][y] == player && x > 0)
            {
                if (x < lastRowPiece) break;
                safePieces[x][y] = true;
                x--;
                if (x <= 0) break;
            }
            lastRowPiece = x;
            x = 6;
            y++;
            if (y >= board.length) break;
        }

        return safePieces;

    }

    private void initializeBooleanArray(boolean[][] pArray)
    {

        for (int x = 0; x < pArray.length; x++)
        {
            for (int y = 0; y < pArray.length; y++)
            {
                pArray[x][y] = false;
            }
        }
    }
}
