package przegladarka.files;

public class ImageFileOpener {
    private ImageFileManager imageFileManager;
    private Runnable onChange;


    public ImageFileOpener(ImageFileManager imageFileManager) {
        this.imageFileManager = imageFileManager;
    }


    public void selectPreviousFile() {
        int currentFileIndex = imageFileManager.getCurrentFileIndex();
        if (currentFileIndex != -1) {
            int changedFileIndex = currentFileIndex == 0 ? imageFileManager.getImageFiles().size() - 1 : currentFileIndex - 1;
            imageFileManager.setCurrentFileIndex(changedFileIndex);
            onChange.run();
        }

    }

    public void selectNextFile() {
        int currentFileIndex = imageFileManager.getCurrentFileIndex();
        if (currentFileIndex != -1) {
            int changedFileIndex = currentFileIndex == imageFileManager.getImageFiles().size() - 1 ? 0 : currentFileIndex +1;
            imageFileManager.setCurrentFileIndex(changedFileIndex);
            onChange.run();
        }

    }

    public Runnable getOnChange() {
        return onChange;
    }

    public void setOnChange(Runnable onChange) {
        this.onChange = onChange;
    }
}
