package przegladarka.files;

import java.util.List;

public class ImageFileManager {
    private List<ImageFile> imageFiles;
    private int currentFileIndex;

    public ImageFileManager(List<ImageFile> imageFiles) {
        this.imageFiles = imageFiles;

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
