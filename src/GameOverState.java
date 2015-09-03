import ea.Taste;
import ea.Text;

public class GameOverState extends State {
    private Text text;
    private Main main;

    public GameOverState(Main main) {
        super(main);

        this.main = main;
        this.text = new Text("Game Over", Level.WIDTH / 2, 100);
        this.text.setAnker(Text.Anker.MITTE);
        this.add(this.text);
    }

    public void onKey(int code) {
        if (code == Taste.LEERTASTE) {
            main.setState(PlayState.class);
        }
    }
}
