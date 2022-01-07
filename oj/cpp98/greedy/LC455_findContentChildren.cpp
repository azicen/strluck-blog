// https://leetcode-cn.com/problems/assign-cookies/
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

// 小根堆
priority_queue< int, vector<int>, greater<int> > G;
priority_queue< int, vector<int>, greater<int> > S;

// 输入数据
void init_cin() {
	int n, a;
	cin >> n;
	while(n > 0) {
		n--;
		cin >> a;
		G.push(a);
	}
	cin >> n;
	while(n > 0) {
		n--;
		cin >> a;
		S.push(a);
	}
}

void greedy() {
	int n = 0;
	// 双队列都不为空时执行
	while(G.empty()==false && S.empty()==false) {
		// 胃口符合
		if(G.top() <= S.top()) {
			n++;
			G.pop(); // 小孩吃饱
		}
		S.pop();
	}
	cout << n << endl;
}

int main() {
	init_cin();
	greedy();
	return 0;
}