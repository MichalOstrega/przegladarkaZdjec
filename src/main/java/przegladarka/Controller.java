package przegladarka;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import przegladarka.files.ImageFile;
import przegladarka.files.ImageFileManager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


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

    private FileChooser fileChooser;

    public void initialize() throws InterruptedException {
        //tworzę obiekt klasy FileChooser
        fileChooser = new FileChooser();
        imageView();
        imageFileManager = new ImageFileManager();
        openFileMenuItem();

        saveFile();
        quitItem();
        buttonsPrevNext();

    }

    private void buttonsPrevNext() {
        prevButton.setOnAction(event -> {
            int currentFileIndex = imageFileManager.getCurrentFileIndex();
            if (currentFileIndex!=-1) {
                int changedFileIndex = currentFileIndex==0 ? imageFileManager.getImageFiles().size()-1 : currentFileIndex-1;
                imageFileManager.setCurrentFileIndex(changedFileIndex);
                setImage(imageFileManager.getCurrentFileIndex());
            }

        });
        nextButton.setOnAction(event -> {
            int currentFileIndex = imageFileManager.getCurrentFileIndex();
            if (currentFileIndex!=-1) {
                int changedFileIndex = currentFileIndex==imageFileManager.getImageFiles().size()-1 ? 0 : currentFileIndex+1;
                imageFileManager.setCurrentFileIndex(changedFileIndex);
                setImage(imageFileManager.getCurrentFileIndex());
            }
        });
    }

    private void openFileMenuItem() {
        openItem.setOnAction(event -> {
            //Filtry do ładowania obrazków
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.bmp", "*.jpeg"));
            //Pobieram wybrany przez użytkownika plik
            List<File> imageFiles = fileChooser.showOpenMultipleDialog(null);
            //Uruchamiam metodę openFile i przekazuje sciezke do pliku
            if (imageFiles != null) {
                imageFileManager.getImageFiles().clear();
                imageFiles.forEach(imageFile -> imageFileManager.openFile(imageFile.toPath()));
                setImage(0);
            }
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


    private void saveFile() {
        saveItem.setOnAction(event -> {
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

    private void imageView() {
        image.setImage(new Image("http://www.focus.pl/media/cache/default_view/uploads/media/default/0001/13/01c9cda76044ba3835d4564589c6a3d6ecaf91c3.jpeg"));
    }

    private void quitItem() {
        quitItem.setOnAction(event -> {
            AlertBox.display("Are you sure?","Czy aby na pewno chcesz opuścić nasz zajebisty program?");
        });
    }

}
