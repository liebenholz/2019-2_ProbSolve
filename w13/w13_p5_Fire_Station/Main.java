import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
 
public class Main {
    static int inf = 1 << 25;
 
    static class Edge {
        int t, w;
        public Edge(int tt, int ww) {
            t = tt;
            w = ww;
        }
    }
    static class Node implements Comparable<Node> {
        int n, d;
        public Node(int nn, int ww) {
            n = nn;
            d = ww;
        }
        public int compareTo(Node o) {
            return d - o.d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(read.readLine());
        read.readLine();
        while (cases-- > 0) {
            int f, n;
            f = 0;	n = 0;
            String inp = read.readLine();
            int i;
            for (i = 0; inp.charAt(i) != ' '; i++)
                f = f * 10 + inp.charAt(i) - '0';
            i++;
            for (; i < inp.length(); i++)
                n = n * 10 + inp.charAt(i) - '0';
            int[] dist = new int[n + 1];
            for (i = 1; i <= n; i++)
                dist[i] = inf;
            while (f-- > 0) {
                int c = Integer.parseInt(read.readLine());
                dist[c] = 0;
            }
            LinkedList<Edge>[] G = new LinkedList[n + 1];
            while (read.ready()) {
                inp = read.readLine();
                if (inp.equals(""))
                    break;
                int a = 0, b = 0, c = 0;
                for (i = 0; inp.charAt(i) != ' '; i++)
                    a = a * 10 + inp.charAt(i) - '0';
                i++;
                for (; inp.charAt(i) != ' '; i++)
                    b = b * 10 + inp.charAt(i) - '0';
                i++;
                for (; i < inp.length() && inp.charAt(i) != ' '; i++)
                    c = c * 10 + inp.charAt(i) - '0';
                if (G[a] == null)
                    G[a] = new LinkedList<Edge>();
                if (G[b] == null)
                    G[b] = new LinkedList<Edge>();
                G[a].add(new Edge(b, c));
                G[b].add(new Edge(a, c));
            }
 
            int min = -1;
            int minimax = -1;
            for (i = 1; i < dist.length; i++) {
                PriorityQueue<Node> Q = new PriorityQueue<Node>();
                for (int j = 1; j < dist.length; j++)
                    if (dist[j] == 0)
                        Q.add(new Node(j, 0));
                Q.add(new Node(i, 0));
                boolean[] V = new boolean[n + 1];
                int[] cdist = dist.clone();
                cdist[i] = 0;
                while (!Q.isEmpty()) {
                    Node current = Q.poll();
                    if (!V[current.n]) {
                        V[current.n] = true;
                        if (G[current.n] != null) {
                            for (Edge e : G[current.n]) {
                                if (current.d + e.w < cdist[e.t]) {
                                    cdist[e.t] = current.d + e.w;
                                    Q.add(new Node(e.t, cdist[e.t]));
                                }
                            }
                        }
                    }
                }
                int max = cdist[1];
                for (int k = 2; k < cdist.length; k++)
                    if (cdist[k] > max)
                        max = cdist[k];
                if (min == -1 || max < minimax) {
                    minimax = max;
                    min = i;
                }
            }
            System.out.println(min);
            if (cases != 0) {
                System.out.println();
            }
        }
    }
}