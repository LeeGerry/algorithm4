package twitter;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-genetic-mutation/discuss/91484/Java-Solution-using-BFS
 *
 */
public class MiniGeneticMutation {
    public int minMutation(String start, String end, String[] bank){
        if (start.equals(end))  return 0;

        char[] gens = {'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>();
        for (String item: bank) bankSet.add(item);
        int level = 0;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                String current = queue.poll();
                if (current.equals(end))    return level;

                char[] currentArray = current.toCharArray();
                for (int i =0; i<currentArray.length; i++){
                    char old = currentArray[i];

                    for (char c: gens){
                        currentArray[i] = c;
                        String next  = new String(currentArray);
                        if (!visited.contains(next) && bankSet.contains(next)){
                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    currentArray[i] = old;
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String start = "AAAAACCC", end = "AACCCCCC";
        String[] banks = {"AAAACCCC","AAACCCCC","AACCCCCC"};
        System.out.println(new MiniGeneticMutation().minMutation(start,end,banks));
    }
}
