package przegladarka.files;

import java.nio.file.Path;
import java.util.List;

public class ImageFileManager {
    private List<ImageFile> imageFiles;
    private int currentFileIndex;
    private OpenFile openFile;

    public ImageFileManager(List<ImageFile> imageFiles) {
        this.imageFiles = imageFiles;

    }

    public void openFile(){
        Path open = openFile.open();
        imageFiles.add(new ImageFile(open.getFileName().toString(), open));
        currentFileIndex = 0;

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
