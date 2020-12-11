package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        final SwingNode swingNode = new SwingNode();
        MapView mapView = new MapView();
        mapView.jPanel(swingNode);

        Group root = new Group();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Pane pane = new Pane();
        pane.getChildren().add(swingNode);
        root.getChildren().add(pane);

        Scene scene=new Scene(root, 1000, 800);
        stage.setTitle("Attack On Titan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
        TileLayer layer;
        TilePane tilePane = new TilePane();
        Tile tile;
        Image tileImage;
        int tid;
        HashMap<Integer, Image> tileHash = new HashMap<>();
        int width = map.getWidth();
        int height = map.getHeight();
        for(int i=0;i<map.getLayerCount();i++) {
            layer = (TileLayer) map.getLayer(i);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    tile = layer.getTileAt(x, y);
                    tid = tile.getId();
                    map.getLayer(i);
                    if (tileHash.containsKey(tid)) {
                        tileImage = tileHash.get(tid);
                    } else {
                        tileImage = createImage(tile.getImage());
                        tileHash.put(tid, tileImage);
                    }
                    ImageView imageView = new ImageView();
                    //imageView.setX(imageView.getX());
                    // imageView.setY(imageView.getY());
                    imageView.setPreserveRatio(true);
                    imageView.setFitHeight(32);
                    imageView.setFitWidth(32);
                    imageView.setImage(tileImage);
                    tilePane.getChildren().addAll(imageView);
                }
            }
        }
        System.out.println("tile image hash has " + tileHash.size() + " items");
        // dump all the tmx stuff
        tileHash = null;
        map = null;
        layer = null;
    }

    private Image createImage(BufferedImage bimg) {
        WritableImage wr=null;
        if (bimg != null) {
            wr = new WritableImage(bimg.getWidth(), bimg.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bimg.getWidth(); x++) {
                for (int y = 0; y < bimg.getHeight(); y++) {
                    pw.setArgb(x, y, bimg.getRGB(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }
    */


