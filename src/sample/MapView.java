package sample;

import javafx.embed.swing.SwingNode;
import org.mapeditor.core.Map;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.ObjectGroup;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;
import org.mapeditor.view.MapRenderer;
import org.mapeditor.view.OrthogonalRenderer;

import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
    Map map;
    MapRenderer renderer;

    public MapView() {
    }
    public MapView(Map map) {
        this.map = map;
        renderer = new OrthogonalRenderer(map);
        setPreferredSize(renderer.getMapSize());
    }

    void jPanel(final SwingNode swingNode) {
        Map map;
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap("src/resources/untitled.tmx");
        } catch (Exception e) {
            System.out.println("Error while reading the map:\n" + e.getMessage());
            return;
        }
        System.out.println(map.toString() + " loaded");
        swingNode.setContent(new MapView(map));
    }

    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();
        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            } else if (layer instanceof ObjectGroup) {
                renderer.paintObjectGroup(g2d, (ObjectGroup) layer);
            }
        }
    }
}
