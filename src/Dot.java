import ea.Knoten;
import ea.Kreis;
import ea.Raum;
import ea.Rechteck;

import java.awt.Color;

public class Dot {
    public static final int RADIUS = 125;
    public static final int SIZE = 10;

    private Knoten symbol;
    private Kreis circle;
    private Rechteck line;
    private float cx;
    private float cy;
    private int alpha;
    private int step;

    public Dot(float cx, float cy, int alpha, int step) {
        this.cx = cx;
        this.cy = cy;
        this.alpha = alpha;
        this.step = step;

        this.symbol = new Knoten();
        this.circle = new Kreis(cx - SIZE / 2, cy - SIZE / 2, SIZE);
        this.line = new Rechteck(cx, cy, RADIUS, 1);
        this.symbol.add(circle, line);
        this.line.farbeSetzen(new Color(117, 114, 109, 200));
        this.circle.farbeSetzen(new Color(169, 30, 113));
    }

    public Dot(float cx, float cy, int alpha) {
        this(cx, cy, alpha, 0);
    }

    public void update(final int alpha) {
        float factor = 1;

        if (step < 10) {
            float progress = (float) step / 10;

            factor = 1 - .2f * (float) Math.sin(2 * progress * Math.PI) * (1 - progress / 1.3f);
            step++;
        }

        float radius = RADIUS * factor;

        this.alpha += alpha;
        this.alpha %= 360;

        float x = this.cx - SIZE / 2 + radius * (float) Math.cos(Math.toRadians(this.alpha));
        float y = this.cy - SIZE / 2 + radius * (float) Math.sin(Math.toRadians(this.alpha));

        this.circle.positionSetzen(x, y);

        x = this.cx + radius / 2 * (float) Math.cos(Math.toRadians(this.alpha)) - 1 - radius / 2;
        y = this.cy + radius / 2 * (float) Math.sin(Math.toRadians(this.alpha)) - 1;
        int size = (int) radius;

        this.line.positionSetzen(x, y);
        this.line.drehenAbsolut(this.alpha);
        this.line.breiteSetzen(size);

    }

    public Raum getSymbol() {
        return symbol;
    }
}
