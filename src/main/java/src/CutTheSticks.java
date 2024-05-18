package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CutTheSticks {
    public static List<Integer> cutTheSticks(List<Integer> sticks) {
        TreeMap<Integer, Integer> sticksFrequency = new TreeMap<>();
        for (Integer stick : sticks)
            sticksFrequency.put(stick, sticksFrequency.getOrDefault(stick, 0) + 1);

        ArrayList<Integer> result = new ArrayList<>();

        while (!sticksFrequency.isEmpty()) {
            result.add(countSticks(sticksFrequency));
            int minStick = sticksFrequency.firstKey();
            sticksFrequency = cutSticks(sticksFrequency, minStick);
        }
        return result;
    }

    private static TreeMap<Integer, Integer> cutSticks(TreeMap<Integer, Integer> sticksFrequency, int cutLength) {
        TreeMap<Integer, Integer> cutSticksFrequency = new TreeMap<>();

        for (Map.Entry<Integer, Integer> oldStickFrequency : sticksFrequency.entrySet()) {
            Integer stickLength = oldStickFrequency.getKey() - cutLength;
            if (stickLength <= 0)
                continue;

            cutSticksFrequency.put(stickLength, oldStickFrequency.getValue());
        }
        return cutSticksFrequency;
    }

    private static Integer countSticks(Map<Integer, Integer> sticksFrequency) {
        int quantity = 0;
        for (Map.Entry<Integer, Integer> entry: sticksFrequency.entrySet()) {
            quantity += entry.getValue();
        }
        return quantity;
    }
}
