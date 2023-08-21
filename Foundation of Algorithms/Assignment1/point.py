"""
Soumil Verma
7/10/23
Point Class
In this class, we are sorting points by x coordinate.
And if the x coordinate is the same, then we are sorting them by y coordinate.
"""


class Point:
    def __init__(self, x, y):           # constructor
        self.x = x
        self.y = y

    def dist(self, other):                          # distance from current to the other point
        return ((self.x - other.x) ** 2 + (self.y - other.y) ** 2) ** (1 / 2)       # used to calculate distance

    def __repr__(self):                     # allows you to print out points
        return f"Point(x={self.x}, y={self.y})"        # print for debugging???

    def __str__(self):                      # 2 string function in python
        return f"Point(x={self.x}, y={self.y})"

    def __lt__(self, other):                # allows us to use the less than operator and for overloading
        if self.x != other.x:               # We are sorting them by the x coordinate.
            return self.x - other.x         # And if the x coordinate is the same, then we are sorting
        else:                               # Them by y coordinate.
            return self.y - other.y
    def __eq__(self,other):                 # used for testing
        return self.x == other.x and self.y == other.y      # equality for points, comparing 2 logic
    def __hash__(self):                     # used for testing
        return hash((self.x,self.y))        # returns same value for object that are equal