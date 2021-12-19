// https://www.dotcpp.com/oj/problem.php?id=1463
#include <iostream>
using namespace std;

void cout_an(int n) {
	cout << "sin(1";
	for(int i=2; i<=n; i++) {
		cout << ((i%2) ? "+" : "-");
		cout << "sin(" << i;
	}
	for(int i=1; i<=n; i++) {
		cout << ")";
	}
}

void cout_sn(int n) {
	for(int i=1; i<=n-1; i++) {
		cout << "(";
	}
	cout_an(1);
	cout << "+" << n;
	for(int i=2; i<=n; i++) {
		cout << ")";
		cout_an(i);
		cout << "+" << n+1-i;
	}
}

int main() {
	int num;
	cin >> num;
	cout_sn(num);
	cout << endl;
	return 0;
}