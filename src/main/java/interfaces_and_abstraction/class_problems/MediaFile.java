package interfaces_and_abstraction.class_problems;

public abstract class MediaFile {

    private final String fileId;

    public MediaFile(String fileId) {
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }
}