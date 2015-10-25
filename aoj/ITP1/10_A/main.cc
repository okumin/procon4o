#include<iostream>
#include<cmath>
using namespace std;

int main() {
    double x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    double xd = x1 - x2;
    double yd = y1 - y2;
    printf("%lf\n", sqrt(xd * xd + yd * yd));
}

