#include<iostream>
#include<cmath>
using namespace std;

int main() {
    double a, b, C;
    cin >> a >> b >> C;
    double PI = acos(-1);
    double rad = C * PI / 180;

    double S = a * b * sin(rad) / 2;
    double c = sqrt(a * a + b * b - 2 * a * b * cos(rad));
    double L = a + b + c;
    double h = 2 * S / a;
    printf("%lf %lf %lf\n", S, L, h);
}

