impl Solution {
    // https://leetcode-cn.com/problems/two-sum/
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        struct Num {
            i: usize,
            n: i32,
        }
        impl Num {
            pub fn new(i: usize, n: i32) -> Self {
                Num { i, n }
            }
        }
        // 构建结构体Vec
        let mut vnums: Vec<Num> = vec![];
        for i in 0..nums.len() {
            vnums.push(Num::new(i, *nums.get(i).unwrap()));
        }
        vnums.sort_by(|a, b| a.n.cmp(&b.n)); // 小到大排序

        let mut min = 0;
        let mut max = nums.len() - 1;
        let mut n: i32;
        loop {
            n = vnums.get(min).unwrap().n + vnums.get(max).unwrap().n;
            if n == target {
                break;
            }
            if n > target {
                max -= 1;
            } else {
                min += 1;
            }
        }
        let i = vnums.get(min).unwrap().i as i32;
        let j = vnums.get(max).unwrap().i as i32;
        return vec![i, j];
    }
}
