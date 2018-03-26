package przegladarka;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import przegladarka.files.ImageFile;
import przegladarka.files.ImageFileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public void initialize() {
        imageFileManager = new ImageFileManager();
        openFileMenuItem();


    }

    private void openFileMenuItem() {
        openItem.setOnAction(event -> {
            //tworzę obiekt klasy FileChooser
            FileChooser fileChooser = new FileChooser();
            //Filtry do ładowania obrazków
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.bmp","*.jpeg"));
            //Pobieram wybrany przez użytkownika plik
            File imageFile = fileChooser.showOpenDialog(null);
            //Uruchamiam metodę openFile i przekazuje sciezke do pliku
            imageFileManager.openFile(imageFile.toPath());

            setImage(imageFileManager.getCurrentFileIndex());
        });
    }

    private void setImage(int currentIndex) {
        //pobieram imageFile na podstawie przekazanego indexu
        ImageFile imageFile = imageFileManager.getImageFiles().get(currentIndex);
        try {
            //ustawiam zdjęcie
            image.setImage(new Image(new FileInputStream(imageFile.getPath().toString())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
