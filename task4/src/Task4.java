import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java MinMovesToEqualElements <input_file>");
            return;
        }

        List<String> inputNums = Files.readAllLines(Paths.get(args[0]));
        List<Integer> nums = new ArrayList<>();
        for (String line : inputNums) {
            if (!line.trim().isEmpty()) {
                for (String part : line.trim().split("\\s+")) {
                    nums.add(Integer.parseInt(part));
                }
            }
        }

        if (nums.isEmpty()) {
            System.out.println(0);
            return;
        }

        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int totalMoves = 0;
        for (int num : nums) {
            totalMoves += Math.abs(num - median);
        }

        System.out.println(totalMoves);
    }
}