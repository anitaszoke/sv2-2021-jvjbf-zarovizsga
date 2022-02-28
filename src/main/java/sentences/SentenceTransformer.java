package sentences;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        if (checkStringStart(sentence)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (checkStringEnd(sentence)) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }


        String result = sentence;
        String[] words = sentence.split("\\s+");
        int countedWords = words.length-1;

        if (words.length > 4) {

            result = words[0] + " ... " + words[countedWords];
        }
        return result;


    }


    private boolean checkStringEnd(String sentence) {
        int lastIndex = sentence.length() - 1;
        String last = String.valueOf(sentence.charAt(lastIndex));
        String ends = ".!?";
        return !ends.contains(last);


    }

    private boolean checkStringStart(String sentence) {
        char c = sentence.charAt(0);
        return (!Character.isUpperCase(c));
    }
}
