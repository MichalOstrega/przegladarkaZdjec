package przegladarka.files;

import java.nio.file.Path;

public class ImageFile {
    private String fileName;
    private Path path;

    public ImageFile(String fileName, Path path) {
        this.fileName = fileName;
        this.path = path;
    }

    public ImageFile(Path path) {
        this(path.getFileName().toString(),path);
    }

    public String getFileName() {
        return fileName;
    }

    public Path getPath() {
        return path;
    }
}
