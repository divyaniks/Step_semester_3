package class_methods_and_uml.assignment_problems;
import java.util.*;

enum HackathonState {
    OPEN, JUDGING, PUBLISHED
}

// 1. Abstraction for Track Scoring Rules (Open/Closed Principle)
interface ScoringRule {
    double calculateFinalScore(Score score);
}

class InnovationScoringRule implements ScoringRule {
    @Override
    public double calculateFinalScore(Score score) {
        return (score.getIdea() * 0.50) + (score.getExecution() * 0.30) + (score.getPresentation() * 0.20);
    }
}

class OpenScoringRule implements ScoringRule {
    @Override
    public double calculateFinalScore(Score score) {
        return (score.getIdea() + score.getExecution() + score.getPresentation()) / 3.0;
    }
}

// 2. Domain Entities
class Track {
    private final String name;
    private final ScoringRule scoringRule;

    public Track(String name, ScoringRule scoringRule) {
        this.name = name;
        this.scoringRule = scoringRule;
    }

    public String getName() { return name; }
    public double evaluate(Score score) { return scoringRule.calculateFinalScore(score); }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name); }
}

class Score {
    private final double idea;
    private final double execution;
    private final double presentation;

    public Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    public double getIdea() { return idea; }
    public double getExecution() { return execution; }
    public double getPresentation() { return presentation; }
}

class Project {
    private final String title;
    private final Team team;
    private Score score;

    public Project(String title, Team team) {
        this.title = title;
        this.team = team;
    }

    public String getTitle() { return title; }
    public Team getTeam() { return team; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }
}

class Team {
    private final String name;
    private final List<Student> members;
    private final Track track;
    private Project project;

    public Team(String name, List<Student> members, Track track) {
        this.name = name;
        this.members = new ArrayList<>(members);
        this.track = track;
    }

    public String getName() { return name; }
    public List<Student> getMembers() { return Collections.unmodifiableList(members); }
    public Track getTrack() { return track; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
}

// 3. Hackathon Manager (Enforces Invariants & State Transitions)
class Hackathon {
    private HackathonState state = HackathonState.OPEN;
    private final Map<String, Team> teams = new HashMap<>();
    private final Set<Student> registeredStudents = new HashSet<>();
    private final Map<String, Project> projects = new HashMap<>();

    public void registerTeam(String teamName, List<Student> members, Track track) {
        if (state != HackathonState.OPEN) {
            System.out.println("Registration failed: Hackathon registration is closed.");
            return;
        }
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }
        for (Student s : members) {
            if (registeredStudents.contains(s)) {
                System.out.println("Registration failed: Student " + s.getName() + " is already in a team.");
                return;
            }
        }

        Team team = new Team(teamName, members, track);
        teams.put(teamName, team);
        registeredStudents.addAll(members);
        System.out.printf("Team %s registered (%d members, %s track).%n",
                teamName, members.size(), track.getName());
    }

    public void submitProject(String teamName, String projectTitle) {
        Team team = teams.get(teamName);
        if (team == null) {
            System.out.println("Submission failed: Team not found.");
            return;
        }
        if (team.getProject() != null) {
            System.out.println("Submission failed: Team has already submitted a project.");
            return;
        }

        Project project = new Project(projectTitle, team);
        team.setProject(project);
        projects.put(projectTitle, project);
        if (state == HackathonState.OPEN) {
            state = HackathonState.JUDGING;
        }
        System.out.printf("Project '%s' submitted by %s.%n", projectTitle, teamName);
    }

    public void scoreProject(String projectTitle, double idea, double execution, double presentation) {
        if (state == HackathonState.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }
        Project project = projects.get(projectTitle);
        if (project == null) {
            System.out.println("Scoring failed: Project not found.");
            return;
        }

        Score score = new Score(idea, execution, presentation);
        project.setScore(score);
        double finalScore = project.getTeam().getTrack().evaluate(score);
        System.out.printf("Score recorded for '%s'. Final score: %.2f.%n", projectTitle, finalScore);
    }

    public void publishResults() {
        state = HackathonState.PUBLISHED;
        System.out.println("Results published.");
    }
}

// 4. Driver Class Matching Sample Input
public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon();
        Track innovation = new Track("Innovation", new InnovationScoringRule());
        Track open = new Track("Open", new OpenScoringRule());

        // 1. Register ByteBusters
        hackathon.registerTeam("ByteBusters",
                Arrays.asList(new Student("Asha"), new Student("Ravi"), new Student("Neha")),
                innovation);

        // 2. Register SoloCoder (fails size rule)
        hackathon.registerTeam("SoloCoder",
                Collections.singletonList(new Student("Kiran")),
                open);

        // 3. Submit SmartAttend
        hackathon.submitProject("ByteBusters", "SmartAttend");

        // 4. Score SmartAttend
        hackathon.scoreProject("SmartAttend", 8, 7, 9);

        // 5. Publish results
        hackathon.publishResults();

        // 6. Attempt rescore after publishing
        hackathon.scoreProject("SmartAttend", 10, 7, 9);
    }
}