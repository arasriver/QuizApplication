import java.util.ArrayList;
import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter the name of the quiz
        System.out.println("Enter the name of the quiz:");
        String quizName = scanner.nextLine();

        // Ask user to enter the number of questions for the quiz
        System.out.println("Enter the number of questions for the quiz:");
        int numQuestions = scanner.nextInt();
        scanner.nextLine();

        // Create a new Quiz object with the given name and number of questions
        Quiz quiz = new Quiz(quizName, numQuestions);

        // Ask user to enter each question and its answer choices
        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question " + (i + 1) + ":");
            String questionText = scanner.nextLine();
            Question question = new Question(questionText);

            // Ask user to enter the number of answer choices for the question
            System.out.println("Enter the number of answer choices for this question:");
            int numChoices = scanner.nextInt();
            scanner.nextLine();

            // Ask user to enter each answer choice and add it to the question
            for (int j = 0; j < numChoices; j++) {
                System.out.println("Enter answer choice " + (j + 1) + ":");
                String answerChoice = scanner.nextLine();
                question.addAnswerChoice(answerChoice);
            }

            // Ask user to enter the correct answer for the question
            System.out.println("Enter the number of the correct answer for this question:");
            int correctAnswer = scanner.nextInt();
            scanner.nextLine();
            question.setCorrectAnswer(correctAnswer);

            // Add the question to the quiz
            quiz.addQuestion(question);
        }

        // Take the quiz and display the results
        quiz.takeQuiz();
        quiz.displayResults();
    }
}

class Quiz {
    private String name;
    private ArrayList<Question> questions;
    private int numCorrect;
    private int numIncorrect;

    public Quiz(String name, int numQuestions) {
        this.name = name;
        this.questions = new ArrayList<>(numQuestions);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.questions.size(); i++) {
            Question question = this.questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getText());

            for (int j = 0; j < question.getNumChoices(); j++) {
                System.out.println((j + 1) + ". " + question.getAnswerChoice(j));
            }

            System.out.println("Enter your answer choice:");
            int userAnswer = scanner.nextInt();
            scanner.nextLine();

            if (userAnswer == question.getCorrectAnswer()) {
                this.numCorrect++;
            } else {
                this.numIncorrect++;
            }
        }
    }

    public void displayResults() {
        int totalQuestions = this.questions.size();
        int score = (int) Math.round(100.0 * this.numCorrect / totalQuestions);

        System.out.println("Quiz results:");
        System.out.println("Number of questions: " + totalQuestions);
        System.out.println("Number of correct answers: " + this.numCorrect);
        System.out.println("Number of incorrect answers: " + this.numIncorrect);
        System.out.println("Score: " + score + "%");
    }
}

class Question {
    private String text;
    private ArrayList<String> answerChoices;
    private int correctAnswer;

    public Question(String text) {
        this.text = text;
        this.answerChoices = new ArrayList<>();
    }

    public void addAnswerChoice(String answerChoice) {
        this.answerChoices.add(answerChoice);
    }

    public String getText() {
        return this.text;
    }

    public int getNumChoices() {
        return this.answerChoices.size();
    }

    public String getAnswerChoice(int index) {
        return this.answerChoices.get(index);
    }

    public void setCorrectAnswer(int index) {
        this.correctAnswer = index;
    }

    public int getCorrectAnswer() {
        return this.correctAnswer;
    }
}
