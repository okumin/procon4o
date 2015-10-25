#include<iostream>
using namespace std;

class Dice {
    public:
        int *num;

        Dice(int *n) {
            num = n;
        }

        void move(char c) {
            int n[6];
            for (int i = 0; i < 6; i++) {
                n[i] = num[i];
            }
            if (c == 'N') {
                num[0] = n[1];
                num[1] = n[5];
                num[4] = n[0];
                num[5] = n[4];
            } else if (c == 'S') {
                num[0] = n[4];
                num[1] = n[0];
                num[4] = n[5];
                num[5] = n[1];
            } else if (c == 'W') {
                num[0] = n[2];
                num[2] = n[5];
                num[3] = n[0];
                num[5] = n[3];
            } else {
                num[0] = n[3];
                num[2] = n[0];
                num[3] = n[5];
                num[5] = n[2];
            }
        }
};

int main() {
    int n;
    cin >> n;
    int num[n][6];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 6; j++) {
            cin >> num[i][j];
        }
    }

    bool result = true;
    for (int a = 0; a < n - 1; a++) {
        for (int b = a + 1; b < n; b++) {
            Dice ad(num[a]), bd(num[b]);
            for (int j = 0; j < 4; j++) {
                if (ad.num[1] == bd.num[1]) break;
                ad.move('S');
            }
            if (ad.num[1] != bd.num[1]) {
                ad.move('W');
                for (int j = 0; j < 4; j++) {
                    if (ad.num[1] == bd.num[1]) break;
                    ad.move('S');
                }
            }
            for (int j = 0; j < 4; j++) {
                if (ad.num[0] == bd.num[0]) break;
                ad.move('W');
            }
            bool eq = true;
            for (int i = 0; i < 6; i++) {
                eq = eq && (ad.num[i] == bd.num[i]);
            }
            result = result && !eq;
        }
    }
    if (result) cout << "Yes"; else cout << "No";
    cout << endl;
}

