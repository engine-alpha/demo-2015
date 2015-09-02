import ea.Game;

public class Main extends Game {
    public static void main(String[] args) {
        new Main();
    }

    private GameState state;

    public Main() {
        super(400, 600, "Engine Alpha – Demo 2015");
        this.setState(new PlayState(this));
    }

    public void setState(GameState state) {
        if (this.state != null) {
            this.manager.abmelden(this.state);
        }

        this.wurzel.leeren();
        this.wurzel.add(state);
        this.manager.anmelden(state);
        this.state = state;
    }

    @Override
    public void tasteReagieren(int code) {
        if (this.state == null) {
            return;
        }

        this.state.onKey(code);
    }
}
