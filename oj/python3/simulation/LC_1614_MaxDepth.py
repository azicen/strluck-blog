class Solution:
    def maxDepth(self, s: str) -> int:
        i = 0
        max_i = 0
        for c in s:
            if c == "(":
                i += 1
                if i > max_i:
                    max_i += 1
            elif c== ")":
                i -= 1
        return max_i