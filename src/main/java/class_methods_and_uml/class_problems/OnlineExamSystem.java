package class_methods_and_uml.class_problems;
import java.util.*;

interface Question {
    int getId();
    boolean evaluate(String answer);
}

class MultipleChoiceQuestion implements Question {
    private final int id;
    private final String correctOption;

    public MultipleChoiceQuestion(int id, String correctOption) {
        this.id = id;
        this.correctOption = correctOption;
    }

    public int getId() { return id; }
    public boolean evaluate(String answer) { return correctOption.equalsIgnoreCase(answer); }
}

class TrueFalseQuestion implements Question {
    private final int id;
    private final boolean correctAnswer;

    public TrueFalseQuestion(int id, boolean correctAnswer) {
        this.id = id;
        this.correctAnswer = correctAnswer;
    }

    public int getId() { return id; }
    public boolean evaluate(String answer) { return Boolean.parseBoolean(answer) == correctAnswer; }
}

class Examination {
    private final String title;
    private final List<Question> questions = new ArrayList<>();

    public Examination(String title) { this.title = title; }
    public void addQuestion(Question q) { questions.add(q); }
    public String getTitle() { return title; }
    public List<Question> getQuestions() { return Collections.unmodifiableList(questions); }
}

class Attempt {
    private final Examination exam;
    private final Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    public Attempt(Examination exam) {
        this.exam = exam;
        System.out.printf("Examination '%s' started by Student.%n", exam.getTitle());
    }

    public void recordAnswer(int questionId, String answer) {
        if (submitted) {
            System.out.println("Cannot modify answers after submission.");
            return;
        }
        answers.put(questionId, answer);
        System.out.printf("Question %d answered with '%s'.%n", questionId, answer);
    }

    public void submit() {
        if (submitted) return;
        submitted = true;
        int score = 0;
        for (Question q : exam.getQuestions()) {
            if (q.evaluate(answers.getOrDefault(q.getId(), ""))) {
                score++;
            }
        }
        System.out.printf("Examination '%s' submitted successfully. Result for '%s' attempt: %d/%d correct.%n",
                exam.getTitle(), exam.getTitle(), score, exam.getQuestions().size());
    }
}

public class OnlineExamSystem {
    public static void main(String[] args) {
        Examination mathQuiz = new Examination("Math Quiz");
        mathQuiz.addQuestion(new MultipleChoiceQuestion(1, "A"));
        mathQuiz.addQuestion(new MultipleChoiceQuestion(2, "B"));

        Attempt attempt = new Attempt(mathQuiz);
        attempt.recordAnswer(1, "A");
        attempt.recordAnswer(2, "C");
        attempt.submit();
    }
}