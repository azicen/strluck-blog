impl Solution {

    // 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
    // https://leetcode-cn.com/problems/non-overlapping-intervals/
    pub fn erase_overlap_intervals(intervals: Vec<Vec<i32>>) -> i32 {
        let mut oi = intervals;
        oi.sort_unstable_by_key(|i| i[1]);

        let mut end = oi[0][1];
        let mut del = 0;
        for i in 1..oi.len() {
            if oi[i][0] < end {
                del += 1;
            } else {
                end = oi[i][1];
            }
        }
        return del;
    }
}