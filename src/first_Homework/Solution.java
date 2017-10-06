package first_Homework;

import java.util.ArrayList;

interface Textable {
    void start();

    void createSentences();

    void createWords();

    void createPunctuation();

    void createLetters();
}

public class Solution {
    public static void main(String[] args) {
        String lineText = "Нравится ли тебе Java?Очень нравится?Тогда посвящай Java,как можно больше времени,и днем,и ночью,и во сне.";
        Text text = new Text(lineText);
        text.start();
    }
}

class Text implements Textable {
    private String text;

    private ArrayList<Sentence> arrayOfSentence;
    private ArrayList<Sentence.Word> arrayOfWords;
    private ArrayList<Sentence.Punctuation> arrayOfPunctuation = new ArrayList<>();

    public Text(String text) {
        this.text = text;
    }

    public void start() {
        createSentences();
        createWords();
        createPunctuation();
        createLetters();
        printInfoAboutCount();
    }

    @Override
    public void createSentences() {
//        for(String line : text.split("[.\\!\\?]")) {
//        arrayOfSentence.add(new Sentence(line));
//        }
        arrayOfSentence = new ArrayList<>();
        String line = "";
        char[] array = text.toCharArray();
        for (Character chr : array) {
            if (chr == '?' || chr == '!' || chr == '.') {
                line += chr;
                arrayOfSentence.add(new Sentence(line));
                line = "";
            } else {
                line += chr;
            }
        }
    }

    @Override
    public void createWords() {
        arrayOfWords = new ArrayList<>();
        for (Sentence snt : arrayOfSentence) {
            String[] massiveOfWords = snt.sentence.split("[ \\.\\!\\?\\,]");
            for (int i = 0; i < massiveOfWords.length; i++) {
                arrayOfWords.add(new Sentence.Word(massiveOfWords[i]));
            }
        }
    }

    @Override
    public void createPunctuation() {
        arrayOfPunctuation = new ArrayList<>();
        for (Sentence snt : arrayOfSentence) {
            char[] massive = snt.sentence.toCharArray();
            for (int i = 0; i < massive.length; i++) {
                if (massive[i] == ',' || massive[i] == '.' || massive[i] == '!' || massive[i] == '?' || massive[i] == ';' ||
                        massive[i] == ':' || massive[i] == '-') {
                    arrayOfPunctuation.add(new Sentence.Punctuation(massive[i] + ""));
                }
            }
        }
    }

    @Override
    public void createLetters() {
        for (Sentence.Word scn : arrayOfWords) {
            scn.createLetters();
        }
    }

    private void printInfoAboutCount() {
        int countOfSentence = arrayOfSentence.size();
        int countOfWords = arrayOfWords.size();
        int countOfLetters = countOfLetters();
        int countOfAskingSentence = countOfAskingSentence();
        int countWordWithP = countWordWithP();

        System.out.println("Количество предложений : " + countOfSentence + ", количество слов : " + countOfWords +
                ", количество букв : " + countOfLetters + ", количество вопросительных предложений : " + countOfAskingSentence);
        System.out.println(", количесвто слов на букву П/п : " + countWordWithP);

    }

    private int countOfLetters() {
        int count = 0;
        for (Sentence.Word snt : arrayOfWords) {
            count += snt.arrayOfLetter.size();
        }
        return count;
    }

    private int countOfAskingSentence() {
        int count = 0;
        for (int i = 0; i < arrayOfSentence.size(); i++) {
            if (arrayOfSentence.get(i).sentence.contains("?")) count++;
        }
        return count;
    }

    private int countWordWithP() {
        int count = 0;
        for (int i = 0; i < arrayOfWords.size(); i++) {
            if (arrayOfWords.get(i).word.startsWith("п") || arrayOfWords.get(i).word.startsWith("П")) count++;
        }
        return count;
    }

    static class Sentence {
        private String sentence;

        public Sentence(String sentence) {
            this.sentence = sentence;
        }

        static class Word {
            private String word;
            private ArrayList<Sentence.Word.Letters> arrayOfLetter;

            public Word(String word) {
                this.word = word;
            }

            private void createLetters() {
                arrayOfLetter = new ArrayList<>();
                char[] massive = word.toCharArray();
                for (int i = 0; i < massive.length; i++) {
                    arrayOfLetter.add(new Letters(massive[i]));
                }
            }

            static class Letters {
                private char letter;

                public Letters(char letter) {
                    this.letter = letter;
                }
            }


        }
        static class Punctuation {
            private String punctuation;

            public Punctuation(String punctuation) {
                this.punctuation = punctuation;
            }
        }
    }
}
