"""
Soumil Verma
7/10/23
Sample Main file
"""

from PointGenerator import point_generator, from_list
from algo import get_m_closest


if __name__ == "__main__":
    num_points = int(input("Please enter the number of points you want to randomly generate (n): "))
    max_value = int(input("Please enter max value: "))
    m = int(input("Please enter the number of closest pairs you want to get (m): "))
    S = point_generator(num_points, 0, max_value)  # (n, lower, upper) n = number of points to generate
                        
    print(S)

    print(f'Get {m} closest pairs:')
    m_closest = get_m_closest(S, m)
    for dist, p1, p2 in get_m_closest(S, m):
        print(f'Distance={dist}, {p1}, {p2}')