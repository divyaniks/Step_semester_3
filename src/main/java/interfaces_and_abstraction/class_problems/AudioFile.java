package interfaces_and_abstraction.class_problems;

public class AudioFile extends MediaFile implements Playable {

    public AudioFile(String fileId) {
        super(fileId);
    }

    @Override
    public String play() {
        return "Playing audio: " + getFileId();
    }

    @Override
    public String play(int fromSecond) {
        return "Playing audio: " + getFileId()
                + " from " + fromSecond + "s";
    }

    @Override
    public String pause() {
        return "Paused audio: " + getFileId();
    }
}