
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
        List<Pair> result = solve(rains);
        for (Pair p : result) {
            System.out.println(String.format("%04d-%04d", p.s, p.e));
        }
    }

    public static List<Pair> solve(Pair[] rains) {
        rains = normalize(rains);
        ArrayList<Pair> result = new ArrayList<Pair>();
        result.add(rains[0]);
        for (int i = 1; i < rains.length; i++) {
            Pair rain = rains[i];
            int index = result.size() - 1;
            Pair top = result.get(index);
            if (rain.s <= top.e) {
                result.set(index, new Pair(top.s, Math.max(top.e, rain.e)));
            } else {
                result.add(rain);
            }
        }
        return result;
    }

    public static Pair[] normalize(Pair[] rains) {
        for (int i = 0; i < rains.length; i++) {
            Pair rain = rains[i];
            int s = rain.s;
            int e = rain.e;
            int start = s - s % 5;
            int end = e + (5 - e % 5) % 5;
            if ((end - 60) % 100 == 0) {
                end = end - 60 + 100;
            }
            rains[i] = new Pair(start, end);
        }
        Arrays.sort(rains, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.s - b.s;
            }
        });
        return rains;
    }
}

