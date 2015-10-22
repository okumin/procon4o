#include<iostream>
using namespace std;

int main() {
    int W, H, x, y, r;
    cin >> W >> H >> x >> y >> r;
    if (x - r >= 0 && y - r >= 0 && x + r <= W && y + r <= H) cout << "Yes";
    else cout << "No";
    cout << endl;
    return 0;
}

