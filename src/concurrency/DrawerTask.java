package concurrency;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import concurrency.controller.MainViewController;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class DrawerTask extends Task<Double> {
    private Canvas mainCanvas = (Canvas) Main.getMainScene().lookup("#mainCanvas");
    private int valueOf = 0;
    private double finalValueOf;
    @Override
    protected Double call() throws Exception {
        int amount = Integer.valueOf(MainViewController.getPointsLabel().getText());

        if(MainViewController.getPointsLabel().getText() != null){
            GraphicsContext gc = mainCanvas.getGraphicsContext2D();
            gc.clearRect(0,0,mainCanvas.getWidth(), mainCanvas.getHeight());
            BufferedImage bi= new BufferedImage((int)mainCanvas.getWidth(), (int)mainCanvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = bi.createGraphics();
            graphics.setPaint(Color.black);
            graphics.fillRect(0,0,bi.getWidth(),bi.getHeight());
            int min = -8;
            int max = 8;
            double x, y, x_1, y_1;
            Random random = new Random();
            for(int j=0;j<=amount;j++){
                x = min + (max - min) * random.nextDouble();
                y = min + (max - min) * random.nextDouble();
                if(Equation.calc(x, y)){
                    x_1 = ((mainCanvas.getHeight()-mainCanvas.getWidth()) * (x-min) / (max-min) + mainCanvas.getHeight()) + mainCanvas.getWidth()/4;
                    y_1 = ((mainCanvas.getHeight()-mainCanvas.getWidth()) * (y-min) / (max-min) + mainCanvas.getHeight());
                    bi.setRGB((int)x_1,(int)y_1,16776960);
                    valueOf++;
                }
                if(j % 3000 == 0){
                    gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0);
                    updateProgress(j,amount);
                }

                if(isCancelled()){
                    break;
                }
            }
            if(!isCancelled()){
                updateProgress(1,1);
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0);
            }
            finalValueOf = valueOf * 16 * 16 / amount;
        }
        else
            return null;

        return finalValueOf;
    }

}

