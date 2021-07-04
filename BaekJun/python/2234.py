n, m = map(int, input().split())

cas_map = {}
group = frozenset()
dp = {}

for col in range(m):
    row_list = list(map(int,input().split()))
    for row in range(n):
        cas_map[(col, row)] = row_list[row]


def blocked_north(x):
    return (x | 2) == 2

def blocked_west(x):
    return (x | 1) == 1

def blocked_east(x):
    return (x | 4) == 4

def blocked_south(x):
    return (x | 8) == 8

def blocked_all(x):
    return x == (1 | 2 | 4 | 8)

def contains_intersected_elements():
    for x in group:
        for y in group:
            if not x.isdisjoint(y) and not x == y:
                return True
    return False

print("inserted map :", cas_map)

for row in range(n):
    for col in range(m):
        current = cas_map[(col, row)]
        if blocked_all(current):
            group = group | {frozenset() | {(col, row)}}

        else:
            if not blocked_north(current):
                group = group | {frozenset() | {(col, row), (col - 1, row)}}
            if not blocked_east(current):
                group = group | {frozenset() | {(col, row), (col, row + 1)}}
            if not blocked_south(current):
                group = group | {frozenset() | {(col, row), (col + 1, row)}}
            if not blocked_west(current):
                group = group | {frozenset() | {(col, row), (col, row - 1)}}


print("updated group :", group)

while contains_intersected_elements():
    for x in group:
        for y in group:
            if not x.isdisjoint(y) and not x == y:
                gruop = group - x
                group = group - y
                group = group | {x | y}
                break
print(group)
