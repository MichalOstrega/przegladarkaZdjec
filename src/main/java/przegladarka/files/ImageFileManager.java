package przegladarka.files;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImageFileManager {
    private List<ImageFile> imageFiles;
    private int currentFileIndex;

    public ImageFileManager() {
        imageFiles = new ArrayList<>();
        currentFileIndex = -1;
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        //Filtry do ładowania obrazków
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.bmp", "*.jpeg"));
        //Pobieram wybrany przez użytkownika plik
        List<File> openFiles = fileChooser.showOpenMultipleDialog(null);
        //Uruchamiam metodę openFile i przekazuje sciezke do pliku
        if (openFiles != null) {
            imageFiles.clear();
            openFiles.forEach(openFile -> imageFiles.add(new ImageFile(openFile.toPath())));
        }
        currentFileIndex = 0;
    }

    public void openDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(null);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            imageFiles.clear();
            for (File openFile : files) {
                if (openFile.getName().contains(".jpg")
                        || openFile.getName().contains(".png")
                        || openFile.getName().contains(".bmp")
                        || openFile.getName().contains(".gif")) {
                    imageFiles.add(new ImageFile(openFile.toPath()));
                }
            }
        }
        currentFileIndex=0;
    }

    public List<ImageFile> getImageFiles() {
        return imageFiles;
    }

    public ImageFile getCurrentImage(){
        return imageFiles.get(getCurrentFileIndex());
    }

    public int getCurrentFileIndex() {
        return currentFileIndex;
    }

    public void setCurrentFileIndex(int currentFileIndex) {
        this.currentFileIndex = currentFileIndex;
    }
}
