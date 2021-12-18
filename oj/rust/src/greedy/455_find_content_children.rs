impl Solution {

    // 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
    // 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
    // 力扣（LeetCode）https://leetcode-cn.com/problems/assign-cookies
    pub fn find_content_children(g: Vec<i32>, s: Vec<i32>) -> i32 {
        let mut og: Vec<i32> = g;
        let mut os: Vec<i32> = s;
        og.sort();
        os.sort();
        
        let mut ig = 0;
        let mut is = 0;
        let mut n = 0;
        while ig < g.len() && is < s.len() {
            if *og.get(ig).unwrap() <= *os.get(is).unwrap() {
                n += 1;
                ig += 1;
            }
            is += 1;
        }
        return n;
    }
}