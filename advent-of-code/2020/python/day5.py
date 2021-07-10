import math
from functools import reduce, partial

def search_row(rng, ch):
    min, max = rng
    
    gap = math.ceil((max - min)/2)
    if ch == 'F':
        return (min, max - gap)
    elif ch == 'B':
        return (min + gap, max)
    
def select_row_num(rng, ch):
    min, max = rng
    return {'F':min,
            'B':max}[ch]

def search_row_num(chars):
    #result = reduce(search_row, chars, (0, 127))
    rng = (0, 127)
    for ch in chars:
        rng = search_row(rng, ch)
    return select_row_num(rng, chars[len(chars)-1])

def search_col(rng, ch):
    min, max = rng
    
    gap = math.ceil((max - min)/2)
    if ch == 'L':
        return (min, max - gap)
    elif ch == 'R':
        return (min + gap, max)

def select_col_num(rng, ch):
    min, max = rng
    return {'L':min,
            'R':max}[ch]

def search_col_num(chars):
    #result = reduce(search_col, chars, (0, 7))
    rng = (0, 7)
    for ch in chars:
        rng = search_row(rng, ch)
    return select_col_num(rng, chars[len(chars)-1])

def row_part(chars):
    return chars[0:7]

def col_part(chars):
    return chars[7:] 

def find_seat_id(chars):
    COL_COUNT = 8
    return search_row_num(row_part(chars)) * COL_COUNT \
            + search_col_num(col_part(chars))

def solve_day1():
    file = open("day5.txt", 'r')
    input = file.read()
    inputs = input.splitlines()
    #ids = map(find_seat_id, inputs)
    ids = tuple(find_seat_id(x) for x in inputs)
    return max(ids)

def id_list():
    file = open("day5.txt", 'r')
    input = file.read()
    inputs = input.splitlines()
    return tuple(find_seat_id(chars) for chars in inputs)

def exists(seat_id, id_list):
    try:
        id_list.index(seat_id)
        #print(seat_id)
        return True
    except:
        return False

def upper_not_exists(seat_id, id_list):
    try:
        id_list.index(seat_id+1)
        return False
    except:
        #print(seat_id)
        return True
    
def upper_next_exists(seat_id, id_list):
    try:
        id_list.index(seat_id+2)
        #print(seat_id)
        return True
    except:
        return False

def is_proper(id_list):
    def fn(seat_id):
        return exists(seat_id, id_list) \
            and upper_not_exists(seat_id, id_list) \
            and upper_next_exists(seat_id, id_list)
    return fn
    
def find_my_seat(id_list):
    result = tuple(id for id in id_list if is_proper(id_list)(id))
    return result[0] + 1

id_lst = id_list()


def solve_day2():
    return find_my_seat(id_lst)

