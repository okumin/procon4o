
import java.util.*;

class Main {
    static class Pair {
        public int s;
        public int e;

        Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Pair[] rains = new Pair[n];
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            String[] se = line.split("-");
            Pair p = new Pair(Integer.parseInt(se[0]), Integer.parseInt(se[1]));
            rains[i] = p;
        }
        solve(rains);
    }

    public static void solve(Pair[] rains) {
        int[] result = new int[normalize(2400) + 1];
        for (Pair p : rains) {
            int ns = normalize(p.s);
            int s = ns - ns % 5;
            result[s] = result[s] + 1;

            int ne = normalize(p.e);
            int e = ne + (5 - ne % 5) % 5;
            result[e] = result[e] - 1;
        }
        int sum = 0;
        boolean on = false;
        for (int i = 0; i < result.length; i++) {
            sum += result[i];
            if (on && sum == 0) {
                System.out.println(String.format("%04d", denormalize(i)));
                on = false;
            } else if (!on && sum > 0) {
                System.out.print(String.format("%04d-", denormalize(i)));
                on = true;
            }
        }
    }

    public static int normalize(int x) {
        int hour = x / 100;
        int minutes = x % 100;
        return hour * 60 + minutes;
    }

    public static int denormalize(int x) {
        int minutes = x % 60;
        int hour = (x - minutes) / 60;
        return hour * 100 + minutes;
    }
}

