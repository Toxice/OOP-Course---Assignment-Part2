package Game;

import Game.GameEntities.EnvironmentUtilities.Game;

public class Main {
    static void main() {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
