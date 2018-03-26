package przegladarka.files;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImageFileManager {
    private List<ImageFile> imageFiles;
    private int currentFileIndex;

    public void setOpenFile(OpenFile openFile) {
        this.openFile = openFile;
    }

    private OpenFile openFile;

    public ImageFileManager() {
        imageFiles = new ArrayList<>();
        currentFileIndex=-1;

    }

    public void openFile(){
        Path image = openFile.open();
        imageFiles.clear();
        imageFiles.add(new ImageFile(image));
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
