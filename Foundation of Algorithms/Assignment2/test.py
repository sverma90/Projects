# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#   NAME: Soumil Verma                                                  #
#   DATE: August 21st, 2023                                             #
#   CLASS: EN 605.681.84                                                #
#   PROGRAM: Unit Testing for X, Y, S Interweaving Program              #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
import unittest
from iw import *
class test(unittest.TestCase):
    def testFirstReturnValue(self):
        is_iw = is_interwoven("101", "0", "1010101")[0]             # testing is_interwoven
        self.assertTrue(is_iw)
        is_iw = is_interwoven("101", "0", "110000")[0]      
        self.assertFalse(is_iw)
    def testSecondReturnValue(self):
        is_in_x = is_interwoven("101", "0", "1010101")[1]           # testing is_in_x
        self.assertEqual([1, 1, 1, 0, 1, 1, 1], is_in_x)
        is_in_x = is_interwoven("101", "0", "110000")[1]
        self.assertEqual([0, 0, 0, 0, 0, 0], is_in_x)
    def testThirdReturnValue(self):
        is_in_y = is_interwoven("101", "0", "1010101")[2]           # testing is_in_y
        self.assertEqual([0, 0, 0, 1, 0, 0, 0], is_in_y)
        is_in_y = is_interwoven("101", "0", "110000")[2]
        self.assertEqual([0, 0, 1, 1, 1, 1], is_in_y)
    def testFourthReturnValue(self):                                # testing x full pattern
        x_full_pattern = is_interwoven("101", "0", "1010101")[3]    
        self.assertEqual("101101", x_full_pattern)
        x_full_pattern = is_interwoven("101", "0", "110000")[3]
        self.assertEqual("", x_full_pattern) 
    def testFifthReturnValue(self):                                 # testing y full pattern
        y_full_pattern = is_interwoven("101", "0", "1010101")[4]
        self.assertEqual("0", y_full_pattern)
        y_full_pattern = is_interwoven("101", "0", "110000")[4]
        self.assertEqual("0000", y_full_pattern)
if __name__ == "__main__":
    unittest.main();