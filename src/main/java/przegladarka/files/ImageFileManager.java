package przegladarka.files;

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

    public void openFile(Path image) {
        imageFiles.clear();
        imageFiles.add(new ImageFile(image));
        currentFileIndex = imageFiles.size() - 1;
    }

    public void openDirectory() {
    }

    public List<ImageFile> getImageFiles() {
        return imageFiles;
    }

    public void setImageFiles(List<ImageFile> imageFiles) {
        this.imageFiles = imageFiles;
    }

    public int getCurrentFileIndex() {
        return currentFileIndex;
    }

    public void setCurrentFileIndex(int currentFileIndex) {
        this.currentFileIndex = currentFileIndex;
    }
}
