impl Solution {
    // 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
    // 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
    // https://leetcode-cn.com/problems/sqrtx/
    pub fn my_sqrt(x: i32) -> i32 {
        if x == 0 {
            return 0;
        }
        if x < 4 {
            return 1;
        }

        let mut half;
        let mut go = 1;
        let mut end = x;
        let mut sqrt;
        while go < end - 1 {
            half = (end - go) / 2 + go;
            print!("go:{:?} h:{:?} end:{:?}", go, half, end);
            sqrt = x / half;
            println!(" s:{:?}", sqrt);
            if sqrt == half {
                return half;
            }
            if sqrt > half {
                go = half;
            } else {
                end = half;
            }
        }
        go
    }
}
