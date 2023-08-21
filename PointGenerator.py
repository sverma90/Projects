"""
Soumil Verma
7/10/23
random number generator that creates points for us
basically you're generating points and adding it to list
user provides lower and upper bounds
user provides n number of points to generate
function returns list of points
"""

import random
from point import Point



def point_generator(n, lower, upper):  # n = number of points to generate randomly
    ls = set()
    for each in range(n):                   # For each point until the range n 
        x = random.randint(lower, upper)    # Create x and y random points from lower and upper bound
        y = random.randint(lower, upper)    
        p = Point(x, y)                     # Store x and y into point p and add to list
        ls.add(p)
    return ls                               # Return list of randomized points from x and y


def from_list(ls):          # this is for testing only to generate non-random numbers.
    return {Point(x, y) for x,y in ls}