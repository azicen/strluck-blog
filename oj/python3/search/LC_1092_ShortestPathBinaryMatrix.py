import collections
from typing import List

class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        n = len(grid)
        target = n - 1
        if grid[target][target] == 1:
            return -1

        direction = (
            (0, 1), (0, -1), (1, 0), (-1, 0),
            (1, 1), (1, -1), (-1, 1), (-1, -1)
        )
        mark = [[0]*n for i in range(n)]
        
        queue = collections.deque()
        queue.append((0, 0, 1)) # 元组 0:x, 1:y, 2:level
        while len(queue) != 0:
            node = queue.popleft()
            if node[0] == target and node[1] == target:
                return node[2]
            if node[0] in (-1, n) or node[1] in (-1, n):
                continue
            if mark[node[0]][node[1]] == 1:
                continue
            if grid[node[0]][node[1]] == 1:
                continue
                
            for d in direction:
                queue.append((node[0] + d[0], node[1] + d[1], node[2] + 1))

            mark[node[0]][node[1]] = 1

        return -1

s = Solution()
str = s.shortestPathBinaryMatrix([
    [0,1,1,1,1,1,1,1],
    [0,1,1,0,0,0,0,0],
    [0,1,0,1,1,1,1,0],
    [0,1,0,1,1,1,1,0],
    [0,1,1,0,0,1,1,0],
    [0,1,1,1,1,0,1,0],
    [0,0,0,0,0,1,1,0],
    [1,1,1,1,1,1,1,0]
    ])
print(str)

str = s.shortestPathBinaryMatrix([
    [1,0,0],
    [1,1,0],
    [1,1,0]
    ])
print(str)