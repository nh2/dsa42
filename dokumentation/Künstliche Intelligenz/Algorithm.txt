Move minimax(Game game) {
    int best = -INF;
    Move bestMove = null;
    for (Move move: possibleMoves(game)) {
        Game copyGame = game.copy();
        value = minimaxValue(copyGame.move(move), maxDepth);
        if ( value > best ) {
            bestMove = move;
            best = value;
        }
    }
    return bestMove;
}

int minimaxValue(Game game, int depth) {
    if ( game.isFinished() || depth == 0 )
        return evaluate(game);
    
    int best;   
    if ( MAX is to move )
        best = -INF;
    else
        best = INF;
        
    for (Move move: game.possibleMoves()) {
        Game copyGame = game.copy();
        value = minimaxValue(copyGame.move(move), depth-1);
        if ( MAX is to move )
            if ( value > best ) best = value;
        else
            if ( value < best ) best = value;
    }
    return best;
}
