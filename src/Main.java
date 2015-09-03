import ea.Game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main extends Game {
    public static void main(String[] args) {
        new Main();
    }

    private State state;

    public Main() {
        super(400, 600, "Engine Alpha – Demo 2015");
        this.setState(PlayState.class);
    }

    public void setState(Class<? extends State> stateClass) {
        State state;

        try {
            Constructor<? extends State> constructor = stateClass.getConstructor(Main.class);
            state = constructor.newInstance(this);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (this.state != null) {
            this.state.unload();
        }

        this.wurzel.leeren();
        this.wurzel.add(state);
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
