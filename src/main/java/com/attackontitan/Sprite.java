/*package com.attackontitan;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.Animation;
import javafx.scene.image.ImageView;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

    AnimatedImage titan = new AnimatedImage();
    Image[] imageArray = new Image[4];

    public void initImage(){
        imageArray[0]= new Image("src/resources/Textures/Titan/walk1.png");
        imageArray[1]= new Image("src/resources/Textures/Titan/walk2.png");
        imageArray[2]= new Image("src/resources/Textures/Titan/walk3.png");
        imageArray[3]= new Image("src/resources/Textures/Titan/attack.png");
        for(int i=0;i<4;i++) {
            titan.frames = imageArray;
            titan.duration = 0.100;
        }
        ImageView walk=new ImageView(imageArray[1]);
    }

    public class AnimatedImage {
        // assumes animation loops,
        //  each image displays for equal time
        public Image[] frames;
        public double duration;

        public Image getFrame(double time) {
            int index = (int)((time % (frames.length * duration)) / duration);
            return frames[index];
        }
    }

    private void startWalking()
    {
        walk = TranslateTransitionBuilder.create()
                .node(dog)
                .fromX(-200)
                .toX(800)
                .duration(Duration.seconds(5))
                .onFinished(new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent t) {
                        startWalking();
                    }
                }).build();
        walk.play();
    }

    public void update(double time)
    {
        positionX += 10;
        positionY += 10;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    new AnimationTimer() {
        public void handle(long currentNanoTime){
            double t = (currentNanoTime - startNanoTime) / 1000000000.0;

            double x = 232 + 128 * Math.cos(t);
            double y = 232 + 128 * Math.sin(t);

            gc.drawImage( ufo.getFrame(t), 450, 25 );
        }
    }.start();
}*/
