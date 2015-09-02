import ea.Knoten;
import ea.Kreis;
import ea.Raum;
import ea.Rechteck;

import java.awt.Color;

public class Dot {
    public static final int RADIUS = 150;
    public static final int SIZE = 16;

    private Knoten symbol;
    private Kreis circle;
    private Rechteck line;
    private float cx;
    private float cy;
    private int alpha;

    public Dot(float cx, float cy, int alpha) {
        this.cx = cx;
        this.cy = cy;
        this.alpha = alpha;

        this.symbol = new Knoten();
        this.circle = new Kreis(cx - SIZE / 2, cy - SIZE / 2, SIZE);
        this.line = new Rechteck(cx, cy, RADIUS, 1);
        this.symbol.add(circle, line);

        this.line.farbeSetzen(new Color(100, 169, 43, 100));
        this.circle.farbeSetzen(new Color(100, 169, 43));
    }

    public void update(final int alpha) {
        this.alpha += alpha;
        this.alpha %= 360;

        float x = this.cx - SIZE / 2 + RADIUS * (float) Math.cos(Math.toRadians(this.alpha));
        float y = this.cy - SIZE / 2 + RADIUS * (float) Math.sin(Math.toRadians(this.alpha));

        this.circle.positionSetzen(x, y);

        x = this.cx + RADIUS / 2 * (float) Math.cos(Math.toRadians(this.alpha)) - 1 - RADIUS / 2;
        y = this.cy + RADIUS / 2 * (float) Math.sin(Math.toRadians(this.alpha)) - 1;

        this.line.positionSetzen(x, y);
        this.line.drehenAbsolut(this.alpha);
    }

    public Raum getSymbol() {
        return symbol;
    }
}
