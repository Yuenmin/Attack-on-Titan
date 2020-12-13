package com.attackontitan;

import javafx.embed.swing.SwingNode;
import org.mapeditor.core.*;
import org.mapeditor.io.TMXMapReader;
import org.mapeditor.view.MapRenderer;
import org.mapeditor.view.OrthogonalRenderer;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class MapView extends JPanel {
    Map map;
    MapRenderer renderer;

    public MapView(final SwingNode swingNode) {
        try {
            TMXMapReader mapReader = new TMXMapReader();
            map = mapReader.readMap("gamemap.tmx");
        } catch (Exception e) {
            System.out.println("Error while reading the map:\n" + e.getMessage());
            return;
        }
        System.out.println(map.toString() + " loaded");

        swingNode.setContent(new MapView(map));

    }
    public MapView(Map map) {
        this.map = map;
        renderer = new OrthogonalRenderer(map);
        setVisible(true);
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
            } else if (layer instanceof ObjectGroup) {
                    paintObjectGroup(g2d,(ObjectGroup) layer);
            }
        }
    }

    public void paintObjectGroup(Graphics2D g, ObjectGroup group) {
        Iterator var = group.iterator();

        while(var.hasNext()) {
            MapObject mo = (MapObject)var.next();
            double ox = mo.getX();
            double oy = mo.getY();
            Double objectWidth = mo.getWidth();
            Double objectHeight = mo.getHeight();
            Tile tile = mo.getTile();

            Image objectImage = tile.getImage();
            if(group.getName().equals("cannon")){
                oy-=235;
            }else if(group.getName().equals("soldier")){
                oy-=100;
            }
            g.drawImage(objectImage, (int) ox, (int) oy,objectWidth.intValue(),objectHeight.intValue(), null);
        }
    }

}
