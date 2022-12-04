import re

input_lines = open("day04/real_input.txt").read().strip().split("\n")


def get_fully_containing_of(a, b, c, d) -> int:

    return int((a >= c and b <= d) or (c >= a and d <= b))


def get_overlapping_of(a, b, c, d) -> int:

    return int((a >= c and a <= d) or (c >= a and c <= b))


# Fake Tail Recursion, replace GOTO with while True
def get_count_of_fully_containing(fully_containing: int = 0, overlap: int = 0, index: int = 0) -> int:

    while True:

        if index == len(input_lines):

            return [fully_containing, overlap]
        
        else:

            a, b, c, d = map(int, re.findall("\d+", input_lines[index]))

            fully_containing += get_fully_containing_of(a, b, c, d)
            overlap += get_overlapping_of(a, b, c, d)

            index += 1


solutions = get_count_of_fully_containing()

print("Solution 1: ", solutions[0])
print("Solution 2: ", solutions[1])
