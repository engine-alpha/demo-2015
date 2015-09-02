public class PlayState extends GameState {
    private Level level;
    private Main main;

    public PlayState(Main main) {
        this.main = main;
        this.level = new Level(8, 16, main);
        this.add(this.level);
        this.main.manager.anmelden(level, 50);
    }

    public void onKey(int key) {
        this.level.onKey(key);
    }
}
