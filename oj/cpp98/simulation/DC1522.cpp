// https://www.dotcpp.com/oj/problem.php?id=1522
#include <iostream>
#include <string>
#include <map>
using namespace std;

map<char, int*> dt;

void init() {
    dt['R'] = new int[2]{0, 1};
    dt['L'] = new int[2]{0, -1};
    dt['D'] = new int[2]{1, 0};
    dt['U'] = new int[2]{-1, 0};
}

void a() {
    int n;
    int sx, sy, tx, ty;
    cin >> n;
    string map[55];
    for (int i=0; i<n; i++) {
        cin >> map[i];
        for (int j=0; j<n; j++) {
            if(map[i].data()[j] == 'S') {
                sx = i;
                sy = j;
            }
            if(map[i].data()[j] == 'T') {
                tx = i;
                ty = j;
            }
        }
    }

    int cn;
    cin >> cn;
    string commend;
    int x, y;
    bool out, dizzy, there;
    for (int i=0; i<cn; i++) {
        x = sx;
        y = sy;
        out = dizzy = there = false;
        commend = "";
        cin >> commend;
        for (int j=0; j<commend.length(); j++) {
            x+=dt[commend.data()[j]][0];
            y+=dt[commend.data()[j]][1];
            if (x < 0 || x >=n || y < 0 || y>=n) {
                out = true;
                break;
            }
            if(map[x][y]=='#') {
                dizzy = true;
                break;
            }
            if(map[x][y]=='T') {
                there = true;
                break;
            }
        }
        if (out) {
            cout << "I am out!" << endl;
        } else if (dizzy) {
            cout << "I am dizzy!" << endl;
        } else if (there) {
            cout << "I get there!" << endl;
        } else {
            cout << "I have no idea!" << endl;
        }
    }
}

int main() {
    init();
    int n;
    cin >> n;
    while (n) {
        n--;
        a();
    }
    return 0;
}