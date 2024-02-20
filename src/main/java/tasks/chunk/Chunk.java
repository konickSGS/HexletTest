package tasks.chunk;

import java.util.Arrays;

public class Chunk {
    public static String[][] chunk(String[] words, int size) {
        int len = words.length;

        int chunkCount = (int) Math.ceil((double) len / size);

        String[][] chunkArray = new String[chunkCount][0];

        for (int i = 0; i < chunkCount; ++i) {
            int startIndex = i * size;
            int endIndex= Math.min(len, size * (i + 1));
            chunkArray[i] = Arrays.copyOfRange(words, startIndex, endIndex);
        }

        return chunkArray;
    }
}
