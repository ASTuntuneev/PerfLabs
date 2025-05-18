public class Task1 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Передайте два аргумента: n и m");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder path = new StringBuilder();
        int start = 1;
        do {
            int len = 0;
            path.append(start);
            while (len < m) {
                len++;
                start++;
                if (start > n) {
                    start = 1;
                }
            }
            start--;
            if (start == 0) {
                start = n;
            }
        } while (start != 1);

        System.out.println(path);
    }
}