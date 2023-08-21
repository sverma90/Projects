# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#   NAME: Soumil Verma                                                  #
#   DATE: August 21st, 2023                                             #
#   CLASS: EN 605.681.84                                                #
#   PROGRAM: Sample Main for X, Y, S Interweaving Program               #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
import sys
from iw import *
if __name__ == "__main__":
    # reading in command line arguments
    if len(sys.argv) == 4:
        x = sys.argv[1]
        y = sys.argv[2]
        s = sys.argv[3]
    else:
        print('expected data in the form of:')
        print('python iw.py <x> <y> <s>')
        sys.exit(1)
    # check that x y and z only consist of 1's and 0's (error checking)
    is_valid = True
    for each in s:
        if each not in ['0','1']:
            is_valid = False
    for each in x:
        if each not in ['0','1']:
            is_valid = False
    for each in y:
        if each not in ['0','1']:
            is_valid = False
    if not is_valid:
        print('all args must consist of only 1 and 0')
        sys.exit(2)
    res,x_pos, y_pos, x_rep, y_rep = is_interwoven(x,y,s)
    print(f'x: {x}, y: {y}, s: {s}')
    print(f'is interwoven? {res}')
    print(f'x_pos: {x_pos}')
    print(f'y_pos: {y_pos}')
    print(f'x rep: {x_rep}')
    print(f'y rep: {y_rep}')