package com.attackontitan;

import javafx.embed.swing.SwingNode;
import org.mapeditor.core.*;
import org.mapeditor.io.TMXMapReader;
import org.mapeditor.view.MapRenderer;
import org.mapeditor.view.OrthogonalRenderer;
import javax.swing.*;
import java.awt.*;

public class MapView extends JPanel {
    Map map;
    MapRenderer renderer;

    public MapView(final SwingNode swingNode) {
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap("src/main/resources/com/attackontitan/gamemap.tmx");
        } catch (Exception e) {
            System.out.println("Error while reading the map:\n" + e.getMessage());
            return;
        }
        System.out.println(map.toString() + " loaded");

        swingNode.setContent(new MapView(map));
        swingNode.setScaleX(0.59);
        swingNode.setScaleY(0.55);
        swingNode.setTranslateX(-550);
        swingNode.setTranslateY(-340);

    }

    public MapView(Map map) {
        this.map = map;
        renderer = new OrthogonalRenderer(map);
        setPreferredSize(renderer.getMapSize());
    }

    public void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g);
        MapObject object;
        for (MapLayer layer : map.getLayers()) {
            if (layer instanceof TileLayer) {
                renderer.paintTileLayer(g2d, (TileLayer) layer);
            } else if(layer.getName().equals("wallNum")){
                for(int i=0;i<9;i++) {
                    object = ((ObjectGroup) layer).getObjects().get(i);
                    int x = (int) object.getX();
                    int y = (int) object.getY();
                    g2d.setFont(new Font("Arial", Font.BOLD, 80));
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(Integer.toString(i + 1), x, y+55);
                }
            }
        }
    }
}
