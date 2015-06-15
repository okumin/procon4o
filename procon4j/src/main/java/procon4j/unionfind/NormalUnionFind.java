package procon4j.unionfind;

class NormalUnionFind {
	static class UnionFind {
		int[] par;
		int[] rank;

		UnionFind(int n) {
			par = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				par[i] = i;
				rank[i] = 0;
			}
		}

		int root(int v) {
			if (par[v] == v) {
				return v;
			} else {
				return par[v] = root(par[v]);
			}
		}

		boolean same(int x, int y) {
			return root(x) == root(y);
		}

		void unite(int x, int y) {
			x = root(x);
			y = root(y);
			if (x == y) return;

			if (rank[x] < rank[y]) {
				par[x] = y;
			} else {
				par[y] = x;
				if (rank[x] == rank[y]) rank[x]++;
			}
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append('(');
			builder.append(par[0]);
			for (int i = 1; i < par.length; i++) {
				builder.append(", ");
				builder.append(par[1]);
			}
			builder.append(')');
			return builder.toString();
		}
	}
}
