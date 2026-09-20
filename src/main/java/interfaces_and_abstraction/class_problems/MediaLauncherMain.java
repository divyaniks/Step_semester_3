package interfaces_and_abstraction.class_problems;

public class MediaLauncherMain {

    static void launchAll(Playable[] items) {

        for (Playable item : items) {
            System.out.println(item.play());
            System.out.println(item.pause());
        }
    }

    public static void main(String[] args) {

        AudioFile a = new AudioFile("Morning Jazz");

        Podcast p = new Podcast("Tech Talk", 12);

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.pause());

        System.out.println(p.play());
        System.out.println(p.play(45));
        System.out.println(p.pause());

        Playable ref = a;

        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}