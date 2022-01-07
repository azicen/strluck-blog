class Solution:
    def simplifyPath(self, path: str) -> str:
        arr = []
        split = path.split("/")

        for s in split:
            if len(s) == 0:
                continue
            if s == ".":
                continue
            if s == "..":
                if len(arr) != 0:
                    del arr[-1]
                else:
                    continue
            else:
                arr.append(s)
        
        if len(arr) == 0:
            return "/"

        r = ""
        for s in arr:
            r += "/"
            r += s
        
        return r

s = Solution()
str = s.simplifyPath("/a/./b/../../c/")
print(str)