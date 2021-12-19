// https://www.dotcpp.com/oj/problem.php?id=1465
#include <iostream>
using namespace std;

int nx, ny;
int dt[5][2] = {{0,0},{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
int d = 1;
bool r[205][205];
int list[205][205];

bool is(int x, int y) {
    if (x < 1 || x > nx || y < 1 || y > ny) return false;
    if (r[x][y]) return false;
    return true;
}

int main() {
	cin >> nx;
	cin >> ny;
	for(int i=1; i <= nx; i++) {
		for(int j=1; j <= ny; j++) {
			cin >> list[i][j];
		}
	}

	int x = 1, y = 1;
	r[1][1] = true;
    cout << list[1][1];
    bool loop = true;
	while(true) {
		if (is(x+dt[d][0], y+dt[d][1])) {
			x = x+dt[d][0];
			y = y+dt[d][1];
			r[x][y] = true;
			loop = true;
			cout << " " << list[x][y];
		} else {
			if(!loop) break;
			loop = false;
			d = (d+1 > 4) ? 1 : d+1;
		}
	}
	cout << endl;

	return 0;
}
