import ea.Knoten;
import ea.Kreis;
import ea.Taste;
import ea.Ticker;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level extends Knoten implements Ticker {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;

    private final Main main;

    private final int anchorX;
    private final int anchorY;

    private final Kreis anchor;
    private final List<Dot> children;
    private final List<Kreis> buffer;

    public Level(final int initialCount, final int shots, final Main main) {
        this.main = main;

        this.anchorX = WIDTH / 2;
        this.anchorY = HEIGHT / 4;

        this.anchor = new Kreis(this.anchorX - 15, this.anchorY - 15, 30);
        this.anchor.farbeSetzen(new Color(100, 169, 43));
        this.anchor.zIndex(2);
        this.add(this.anchor);

        this.children = new ArrayList<>();

        for (int i = 0; i < initialCount; i++) {
            Dot circle = new Dot(this.anchorX, this.anchorY, 360 * i / initialCount);
            this.children.add(circle);
            this.add(circle.getSymbol());
        }

        this.buffer = new ArrayList<>();

        for (int i = 0; i < shots; i++) {
            Kreis circle = new Kreis(this.anchorX - Dot.SIZE / 2, HEIGHT - 150 + i * 50, Dot.SIZE);
            circle.farbeSetzen(new Color(100, 169, 43));

            this.buffer.add(circle);
            this.add(circle);
        }
    }

    @Override
    public void tick() {
        for (Dot d : this.children) {
            d.update(5);
        }
    }

    private void shoot() {
        // no more ammunition
        if (this.buffer.size() == 0) {
            return;
        }

        final Kreis circle = this.buffer.remove(0);

        this.main.manager.anmelden(new Ticker() {
            @Override
            public void tick() {
                circle.verschieben(0, -10);

                // only check for collisions if necessary
                if (circle.getY() < anchorY + Dot.RADIUS + 30) {
                    for (Dot d : children) {
                        if (d.getSymbol().schneidet(circle)) {
                            main.setState(new GameOverState(main));
                        }
                    }
                }

                if (circle.getY() < anchorY + Dot.RADIUS) {
                    entfernen(circle);

                    Dot dot = new Dot(anchorX, anchorY, 90);
                    children.add(dot);
                    add(dot.getSymbol());

                    main.manager.abmelden(this);
                }
            }
        }, 25);

        for (Kreis k : buffer) {
            k.positionSetzen(k.getX(), k.getY() - 50);
        }
    }

    public void onKey(int code) {
        if (code == Taste.LEERTASTE) {
            this.shoot();
        }
    }
}
