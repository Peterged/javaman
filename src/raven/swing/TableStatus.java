package raven.swing;

import raven.model.StatusType;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class TableStatus extends JLabel {

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    private StatusType type;

    public void setType(StatusType type) {
        this.type = type;
        setText(type.toString());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            g = switch (type) {
                case PENDING -> new GradientPaint(0, 0, new Color(244, 205, 67), 0, getHeight(), new Color(244, 205, 67));
                case SELESAI -> new GradientPaint(0, 0, new Color(33, 235, 16), 0, getHeight(), new Color(33, 235, 16));
                case GAGAL, DIBATALKAN -> new GradientPaint(0, 0, new Color(244, 67, 67), 0, getHeight(), new Color(244, 67, 67));
                default -> new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
            };
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(grphcs);
    }
}