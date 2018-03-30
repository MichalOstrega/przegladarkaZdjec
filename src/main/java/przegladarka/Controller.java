package przegladarka;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import przegladarka.files.ImageFile;
import przegladarka.files.ImageFileManager;
import przegladarka.files.ImageFileOpener;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;


public class Controller {
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private ImageView image;
    @FXML
    private MenuItem openItem;
    @FXML
    private MenuItem openFolder;
    @FXML
    private MenuItem saveItem;
    @FXML
    private MenuItem quitItem;

    private Path imagePath;

    private ImageFileManager imageFileManager;

    private ImageFileOpener imageFileOpener;

    public void initialize() throws InterruptedException {
        imageFileManager = new ImageFileManager();
        imageFileOpener = new ImageFileOpener(imageFileManager);
        imageFileOpener.setOnChange(() -> {
            displayImage(imageFileManager.getCurrentImage());
        });
        firstImage();
        openFileMenuItem();
        openDirectoryMenuItem();
        saveFile();
        quitItem();
        buttonsPrevNext();

    }

    private void buttonsPrevNext() {
        prevButton.setOnAction(event -> {
            imageFileOpener.selectPreviousFile();
        });
        nextButton.setOnAction(event -> {
            imageFileOpener.selectNextFile();
        });
    }

    private void openFileMenuItem() {
        openItem.setOnAction(event -> {
            imageFileManager.openFile();
            imageFileOpener.getOnChange().run();
        });
    }

    private void openDirectoryMenuItem() {
        openFolder.setOnAction(event -> {
            imageFileManager.openDirectory();
            imageFileOpener.getOnChange().run();
        });
    }

    private void displayImage(ImageFile imageFile) {
        try {
            //ustawiam zdjęcie
            image.setImage(new Image(new FileInputStream(imageFile.getPath().toString())));
        } catch (FileNotFoundException e) {

        }
    }


    private void saveFile() {
        saveItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File saveFile = fileChooser.showSaveDialog(null);
            if (saveFile != null) {
                save(saveFile);
            }
        });
    }

    private void save(File saveFile) {
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image.getImage(), null), "jpg", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void firstImage() {
        image.setImage(new Image("http://files.tinypic.pl/i/00962/ot6kpgsqxm59.png"));
    }

    private void quitItem() {
        quitItem.setOnAction(event -> {
            AlertBox.display("Are you sure?","Czy aby na pewno chcesz opuścić nasz program?");
        });
    }

}