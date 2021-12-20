// https://www.dotcpp.com/oj/problem.php?id=1499
#include <iostream>
#include <algorithm>

using namespace std;

int list[1005];
int r[1005];

int main() {
	int size, max_num = 0;
	cin >> size;
	for(int i = 1; i <= size; i++) {
		cin >> list[i];
	}
	for(int i = 1; i <= size; i++) {
		r[i] = r[list[i]] + 1;
		max_num = max(max_num, r[i]);
	}
	cout << max_num << endl;

	return 0;
}