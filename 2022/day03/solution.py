input_lines = open("day03/real_input.txt").read().strip().split("\n")

sum1, sum2 = 0, 0

# Solution 1
def get_priority_of(character) -> int:

    unicode_subtract_value = -38

    if character.islower():

        unicode_subtract_value = -96

    return unicode_subtract_value + ord(u"" + character + "")


def split_string_half(string: str):
    
    length = len(string)

    half = round(length / 2)

    return [string[0:half], string[half:length]]


for line in input_lines:

    sub_strings = split_string_half(line)

    for character in sub_strings[1]:

        if character in sub_strings[0]:

            sum1 += get_priority_of(character)

            break

print("Solution 1: ", sum1)

# Solution 2
while len(input_lines) > 2:

    rucksacks = [input_lines.pop(), input_lines.pop(), input_lines.pop()]

    for character in set(rucksacks[0]) & set(rucksacks[1]) & set(rucksacks[2]):

        sum2 += get_priority_of(character)

print("Solution 2: ", sum2)
