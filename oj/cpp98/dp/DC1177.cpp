// https://www.dotcpp.com/oj/problem.php?id=1177
#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

int problem_1177(vector<vector<int> > &list) {
    for (int i = list.size() - 1; i >= 0; --i) {
        for (int j = 0; j < list[i].size(); ++j) {
//            cout << i << "-" << j << " ";
            if (i == list.size() - 1) {
//                cout << list[i][j] << endl;
                continue;
            }
            // max{左下, 右下} + 自己
            list[i][j] = max(list[i + 1][j], list[i + 1][j + 1]) + list[i][j];
//            cout << list[i][j] << endl;
        }
    }
    return list[0][0];
}

int main() {
    int c, line, n, a;
    cin >> c; // 总样例数

    while (c) {
        c--;
        cin >> line;
        vector<vector<int> > list(line);
        for (int i = 0; i < line; ++i) {
            n = i + 1;
            list[i].reserve(n);
            for (int j = 0; j < n; ++j) {
                cin >> a;
                list[i].push_back(a);
            }
        }
        cout << problem_1177(list) << endl;
    }

//    for (int i = 0; i < list.size(); ++i) {
//        cout << list[i].size() << " - ";
//        for (int j = 0; j < list[i].size(); ++j) {
//            cout << list[i][j] << " ";
//        }
//        cout << endl;
//    }

    return 0;
}