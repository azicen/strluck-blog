impl Solution {

    // https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
    pub fn next_greatest_letter(letters: Vec<char>, target: char) -> char {
        if target >= letters[letters.len() - 1] {
            return letters[0];
        }

        let mut min = 0;
        let mut mid;
        let mut max = letters.len() - 1;
        while min < max {
            mid = (max - min) / 2 + min;
            println!(
                "minc:{:?} midc:{:?} maxc:{:?} min:{:?} mid:{:?} max:{:?}",
                letters[min], letters[mid], letters[max], min, mid, max
            );
            if letters[mid] > target {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        println!("min:{:?} max:{:?}", min, max);
        letters[min]
    }
}