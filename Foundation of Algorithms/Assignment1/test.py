"""
Soumil Verma
7/10/23
unit testing part of program
"""

import unittest
from algo import get_m_closest
from point import Point

class test(unittest.TestCase):
    def test_list_of_four_points(self):
        points = [Point(1,1), Point(1,2), Point(1,3,), Point(50,50)]
        exp =[ (1.0, Point(1,1), Point(1,2)),
              (1.0, Point(1,2), Point(1,3)),
              (2.0, Point(1,1,), Point(1,3))
        ]
        # same elements, dont care about order
        for r,e in zip(get_m_closest(points, 3),exp):
            self.assertEqual(r,e)
    def test_m_equals_zero_returns_empty_list(self):
        points = [Point(1,1), Point(1,2), Point(1,3,), Point(50,50)]
        self.assertFalse(get_m_closest(points, 0))

if __name__ == "__main__":
    unittest.main();