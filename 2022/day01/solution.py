import datetime

start_time = datetime.datetime.now()

input_lines = open("day01/real_input.txt").read().strip().split("\n\n")

elf_calories = []

for line in input_lines:
    
    current_sum_of_calories = sum(map(int, line.split("\n")))

    elf_calories.append(current_sum_of_calories)

print("Solution 1: ", max(elf_calories))

print("Solution 2: ", sum(sorted(elf_calories, reverse=True)[:3]))

print(f"Program executed in {(datetime.datetime.now() - start_time).total_seconds() * 1000}ms")
