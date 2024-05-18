package src;

import java.util.ArrayList;
import java.util.Collections;

public class RepeatedString {

    public static long repeatedString(String word, long letterNumber) {
        ArrayList<Integer> occurrencesOfA = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a') {
                occurrencesOfA.add(i);
            }
        }

        int numberOccurrenceOfAInWord = occurrencesOfA.size();
        long frequencyOfA = (letterNumber / word.length()) * numberOccurrenceOfAInWord;

        int remainingLetters = (int) (letterNumber % word.length());
        int positionOfLastRemainingLetter = remainingLetters - 1;
        int remainingA = Collections.binarySearch(occurrencesOfA, positionOfLastRemainingLetter) +1;
        if(remainingA < 0)
            remainingA *= -1;

        return frequencyOfA + remainingA;
    }
}
