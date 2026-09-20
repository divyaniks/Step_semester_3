package interfaces_and_abstraction.class_problems;

public class Podcast implements Playable {

    private String title;
    private int episodeNumber;

    public Podcast(String title, int episodeNumber) {
        this.title = title;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Playing podcast: " + title
                + " Episode " + episodeNumber;
    }

    @Override
    public String play(int fromSecond) {
        return "Playing podcast: " + title
                + " Episode " + episodeNumber
                + " from " + fromSecond + "s";
    }

    @Override
    public String pause() {
        return "Paused podcast: " + title
                + " Episode " + episodeNumber;
    }
}