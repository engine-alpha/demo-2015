public class PlayState extends State {
    private Level level;
    private Main main;

    public PlayState(Main main) {
        super(main);

        this.main = main;
        this.level = new Level(8, 16, main);
        this.add(this.level);

        this.main.tickerAnmelden(level, 50);
    }

    public void onKey(int key) {
        this.level.onKey(key);
    }
}
