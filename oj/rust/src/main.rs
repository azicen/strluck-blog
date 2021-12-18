fn main() {
    println!(
        "{:?}",
        // next_greatest_letter_1(vec!['e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n'], 'e')
        next_greatest_letter_1(vec!['c', 'f', 'j'], 'k')
    );
}

pub fn next_greatest_letter_1(letters: Vec<char>, target: char) -> char {
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
