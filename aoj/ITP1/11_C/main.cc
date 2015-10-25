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
    int a[6], b[6];
    for (int i = 0; i < 6; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < 6; i++) {
        cin >> b[i];
    }
    Dice ad(a);
    Dice bd(b);


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
    while (ad.num[0] != bd.num[0]) {
        ad.move('W');
    }
    bool result = true;
    for (int i = 0; i < 6; i++) {
        result = result && (ad.num[i] == bd.num[i]);
    }
    if (result) cout << "Yes"; else cout << "No";
    cout << endl;
}

