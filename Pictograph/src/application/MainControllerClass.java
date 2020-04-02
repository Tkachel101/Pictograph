package application;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainControllerClass implements Initializable {
	@FXML
	private ImageView image;
	@FXML
	private ScrollPane scrollBar;
	@FXML
	private Menu recent;
	ArrayList<BufferedImage> selectedImages = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void newProject() {

	}

	public void openProject() {

	}

	public void save() {

	}

	public void saveAs() {

	}

	public void loadImage() {
		File recordsDir = new File(System.getProperty("user.home"), "/Pictures");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(recordsDir);
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
		if (selectedFiles != null) {
			for (int i = 0; i < selectedFiles.size(); ++i) {
				try {
					selectedImages.add(ImageIO.read(selectedFiles.get(i)));
					ImageView smallImage = new ImageView();
					
					smallImage.setImage(SwingFXUtils.toFXImage(resize(selectedImages.get(selectedImages.size() - 1), 150, 150), null));
					((AnchorPane) scrollBar.getContent()).getChildren().add(smallImage);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		 
	}
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 

	public BufferedImage blkWhite(BufferedImage loadedImage) {
		BufferedImage storedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < loadedImage.getWidth(); ++i) {
			for (int j = 0; j < loadedImage.getHeight(); ++j) {
				Color c = new Color(loadedImage.getRGB(i, j));
				int grey = (c.getBlue() + c.getGreen() + c.getRed()) / 3;
				storedImage.setRGB(i, j, new Color(grey, grey, grey).getRGB());
			}
		}
		return storedImage;
	}

	public void removeImages() {

	}

	public void close() {
		Platform.exit();
	}

	public void copy() {

	}

	public void paste() {

	}

	public void delete() {

	}

	public void undo() {

	}

	public void redo() {

	}

	public void about() {

	}

}
