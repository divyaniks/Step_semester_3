import java.util.*;

public class LabScheduler {
    static class Job {
        final String name;
        int remaining;
        Job(String name, int remaining) {
            this.name = name;
            this.remaining = remaining;
        }
    }

    public static void schedule(int quantum, List<Job> initialJobs) {
        Queue<Job> queue = new ArrayDeque<>(initialJobs);
        int currentTime = 0;
        List<String> slices = new ArrayList<>();
        List<String> finishes = new ArrayList<>();

        while (!queue.isEmpty()) {
            Job job = queue.poll();
            int runTime = Math.min(quantum, job.remaining);
            int start = currentTime;
            currentTime += runTime;
            job.remaining -= runTime;
            slices.add(String.format("%s(%d-%d)", job.name, start, currentTime));

            if (job.remaining > 0) {
                queue.offer(job);
            } else {
                finishes.add(job.name + " " + currentTime);
            }
        }

        System.out.println(String.join(" ", slices));
        System.out.println("finish " + String.join(", ", finishes));
    }

    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
            new Job("P1", 5),
            new Job("P2", 3),
            new Job("P3", 7)
        );
        schedule(3, jobs);
    }
}