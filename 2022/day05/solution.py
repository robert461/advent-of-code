import re, datetime, copy

start_time = datetime.datetime.now()

stack_information_lines, instruction_lines = open("day05/real_input.txt").read().split("\n\n")
stack_lines = stack_information_lines.split("\n")
stack_size = int(stack_lines.pop(-1).strip()[-1:])

stacks = [[] for _ in range(stack_size)]

for index, stack in enumerate(stack_lines):
    
    for i in range(stack_size):

        value = stack[i*4+1]

        if value.strip():

            stacks[index].append(value)
        
        else:

            stacks[index].append("EMPTY")


stacks.pop(-1)

# Transpose
stacks1 = list(zip(*stacks))
stacks = [list(sublist) for sublist in stacks1]


def get_solution(_stacks) -> str:
    
    return [_stacks[i][0] for i in range(len(_stacks))]
    

# Solution 1
# Take care of Python shallow copy
stacks_copy = copy.deepcopy(stacks)

for instruction_line in instruction_lines.split("\n"):

    if instruction_line:

        amount, stack_from, stack_to = map(int, re.findall("\d+", instruction_line))

        for i in range(amount):

            j = 0

            while stacks_copy[stack_from - 1][j] == "EMPTY":

                j += 1

            stacks_copy[stack_to - 1].insert(0, stacks_copy[stack_from - 1][j])

            del stacks_copy[stack_from - 1][j]


print("Solution 1: ", get_solution(stacks_copy))


# Solution 2
stacks_copy = copy.deepcopy(stacks)

for instruction_line in instruction_lines.split("\n"):

    if instruction_line:

        amount, stack_from, stack_to = map(int, re.findall("\d+", instruction_line))

        moving_crates = []

        for i in range(amount):

            j = 0

            while stacks_copy[stack_from - 1][j] == "EMPTY":

                j += 1

            moving_crates.append(stacks_copy[stack_from - 1].pop(j))
        
        for i in range(amount):

            stacks_copy[stack_to - 1].insert(0, moving_crates.pop(-1))


print("Solution 2: ", get_solution(stacks_copy))

print(f"Program executed in {(datetime.datetime.now() - start_time).total_seconds() * 1000}ms")
