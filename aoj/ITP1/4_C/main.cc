#include<iostream>
using namespace std;

int main() {
    int a, b;
    char op;
    while (true) {
        cin >> a >> op >> b;
        if (op == '+') cout << a + b;
        else if (op == '-') cout << a - b;
        else if (op == '*') cout << a * b;
        else if (op == '/') cout << a / b;
        else break;
        cout << endl;
    }
}

