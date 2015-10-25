#include<iostream>
#include<cmath>
using namespace std;

int main () {
    int n;
    while (true) {
        cin >> n;
        if (n == 0) break;
        int s[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            cin >> s[i];
            sum += s[i];
        }
        double m = sum * 1.0 / n;
        double v = 0.0;
        for (int i = 0; i < n; i++) {
            v += pow(m - s[i], 2.0);
        }
        printf("%lf\n", sqrt(v / n));
    }
}

