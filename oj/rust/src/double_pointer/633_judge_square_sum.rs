impl Solution {

    // 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
    // https://leetcode-cn.com/problems/sum-of-square-numbers/
    pub fn judge_square_sum(c: i32) -> bool {
        let target = c as i64;
        let mut min: i64 = 0;
        let mut max: i64 = (c as f64).sqrt() as i64; // 求更剪枝
        let mut n: i64;
        while max >= min {
            n = min * min + max * max;
            if n == target {
                return true;
            }
            if n > target {
                max -= 1;
            } else {
                min += 1;
            }
        }
        return false;
    }
}