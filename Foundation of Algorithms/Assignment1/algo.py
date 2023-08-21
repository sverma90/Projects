"""
Soumil Verma
7/10/23
get_m_closest function (main algorithm)
loops over all points, check all points,
from that point that's not the current point.
measure the distance after we have looped over all the points.
add it to all_dists list.
"""

from point import Point
from MergeSort import merge_sort
from PointGenerator import point_generator, from_list

"""
Theoretical complexity of the get_m_closest algorithm:
C1 + C2 + (C3 * N) + (C3 * N * N) +  (C4 * N * N) + (C5 * N * N)  + (C6 * N * N) + (C7 * N * N) + (C8 * N * N * log(N * N)) + (C9 * M)
C1 + C2 + (C3 * N) + (C3 * N^2) + (C4 * N^2) + (C5 * N^2)  + (C6 * N^2) + (C7 * N^2) + (C8 * N^2 * log(N^2)) + (C9 * M)
"""

def get_m_closest(S, m):  # s = set, m = number, O(n^2 * log(n^2))
    seen = set()  # O(1), C1        creating a set
    all_dists = []  # O(1), C2      declaring a list
    for point in S:  # O(n), C3 * N
        for other_point in S:  # O(n) * O(n), C3 * N * N
            if point is not other_point:  # O(1), C4 * N * N
                if (point, other_point) in seen or (other_point, point) in seen:  # O(n) * n, C5 * N * N
                    continue
                seen.add((point, other_point))  # O(1), (C6 * N * N)
                all_dists.append((point.dist(other_point), point, other_point))  # O(1) (C7 * N * N)
    merge_sort(all_dists)  # (C8 * N * N * log(N * N)) merge sort is O(nlog(n)), length of all_dists is n^2, so this merge sort is O(n^2 * log(n^2))
    return all_dists[0:m]  # O(n), return would be O(m), (C9 * M)