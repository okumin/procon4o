#include<iostream>
#include<cmath>
using namespace std;

double min(double p, int n, int *x, int *y) {
    double d = 0.0;
    for (int i = 0; i < n; i++) {
        d += pow(abs(x[i] - y[i]), p);
    }
    return pow(d, 1 / p);
    return d;
}

int main() {
    int n, x[100], y[100];
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> x[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> y[i];
    }
    for (int i = 1; i <= 3; i++) {
        printf("%lf\n", min(i, n, x, y));
    }
    double max = 0;
    for (int i = 0; i < n; i++) {
        int diff = abs(x[i] - y[i]);
        max = diff > max ? diff : max;
    }
    printf("%lf\n", max);
}

