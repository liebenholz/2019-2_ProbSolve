import java.io.IOException;
import java.util.Scanner;

class Main {
	static int nVertice, nEdges;

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		nEdges = input.nextInt();
		nVertice = input.nextInt();

		int[][] graph = new int[nEdges+1][nEdges+1];
		int[][] weight = new int[nEdges+1][nEdges+1];

		graph[0][0] = 0;
		weight[0][0] = 0;

		for(int i=0; i<nVertice; i++) {
			int e1 = input.nextInt();
			int e2 = input.nextInt();

			graph[e1][0] = e1;
			graph[e2][0] = e2;
			graph[0][e1] = e1;
			graph[0][e2] = e2;

			graph[e1][e2] = 1;
			graph[e2][e1] = 1;
			int w = input.nextInt();
			weight[e1][e2] = w;
			weight[e2][e1] = w;
		}

		for(int i=1; i<=nEdges; i++) {
			System.out.print(i + ": ");
			for(int j=1; j<=nEdges; j++) {
				if(graph[i][j]==1) { System.out.print(" " + j);
				System.out.print("(" + weight[i][j] + ")");
				}
			}
			System.out.println();
		}

		input.close();
	}
}