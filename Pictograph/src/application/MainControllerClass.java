package application;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainControllerClass implements Initializable {
	@FXML
	private ImageView image;
	@FXML
	private ScrollPane scrollBar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void close() {
		Platform.exit();
	}

	public void loadImage() {
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		 File selectedFile = fileChooser.showOpenDialog(null);
		 if (selectedFile != null) {
			 BufferedImage loadedImage = null;
			 try {
				loadedImage = ImageIO.read(selectedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < loadedImage.getWidth(); ++i) {
				for (int j = 0; j < loadedImage.getHeight(); ++j) {
					Color c = new Color(loadedImage.getRGB(i, j));
					int grey = (c.getBlue() + c.getGreen() + c.getRed()) / 3;
					loadedImage.setRGB(i, j, new Color(grey, grey, grey).getRGB());
				}
			}
			image.setImage(SwingFXUtils.toFXImage(loadedImage, null));
			  
		 }
	}
}
