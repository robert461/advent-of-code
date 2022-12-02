import functools as ft

input_lines = open("day02/real_input.txt").read().strip().replace(" ", "").split("\n")

# Solution 1
def score_of(character: chr) -> int:

    return -87 + ord(u"" + character + "")


def outcome_of(opponent_choice: chr, choice: chr) -> int:

    match (opponent_choice, choice):

        case ('A', 'Y') | ('B', 'Z') | ('C', 'X'): return 6

        case ('A', 'Z') | ('B', 'X') | ('C', 'Y'): return 0

        case _: return 3

print("Solution 1: ", sum(
        map(lambda a, _: a + _,
        [sum(map(score_of, line[1])) for line in input_lines],
        [sum(map(outcome_of, line[0], line[1])) for line in input_lines]
        )
    ))

# Solution 2
def ultra_top_secret_strategy_guide_for(opponent_choice: chr, choice: chr) -> chr:

    match (choice, opponent_choice):

        case ('X', 'A'): return 'Z'
        case ('X', 'B'): return 'X'
        case ('X', 'C'): return 'Y'

        case ('Y', 'A'): return 'X'
        case ('Y', 'B'): return 'Y'
        case ('Y', 'C'): return 'Z'

        case ('Z', 'A'): return 'Y'
        case ('Z', 'B'): return 'Z'
        case ('Z', 'C'): return 'X'

score = 0

for line in input_lines:

    correct_response = ultra_top_secret_strategy_guide_for(line[0], line[1])

    score += sum(map(score_of, correct_response))
    score += sum(map(outcome_of, line[0], correct_response))

print("Solution 2: ", score)
