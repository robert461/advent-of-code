input_lines = open("day01/real_input.txt").read().strip().split("\n\n")

elf_calories = []

for line in input_lines:
    
    current_sum_of_calories = sum(map(int, line.split("\n")))

    elf_calories.append(current_sum_of_calories)

# Solution 1
print("Solution 1: ", max(elf_calories))

# Solution 2
print("Solution 2: ", sum(sorted(elf_calories, reverse=True)[:3]))
