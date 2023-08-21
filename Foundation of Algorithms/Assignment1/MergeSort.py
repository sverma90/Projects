"""
Soumil Verma
7/10/23
Merge sort function that merges 2 sorted list into one complete list
"""
import random


def merge(ls, l, m, r):  # ls = list, l = left, m = middle, r = right
    arr1 = []       # left bound
    arr2 = []       # right bound
    i1 = 0          # index 1 starts at left
    i2 = 0          # index 2 starts at middle
    i = l
    for each in ls[l:m]:        # sublist, start form left to middle (to l and 1 less than m)
        arr1.append(each)           # adding sublist to arr1
    for each in ls[m:r]:        # sublist, start form middle to right (to m and 1 less than r)
        arr2.append(each)           # adding sublist to arr2
    while i1 < len(arr1) and i2 < len(arr2):
        if arr1[i1] < arr2[i2]:             # moving data from arr1 to arr2, and vice versa
            ls[i] = arr1[i1]
            i1 += 1
        else:
            ls[i] = arr2[i2]
            i2 += 1
        i += 1
    while i1 < len(arr1):       # moving elements from arr1 to list
        ls[i] = arr1[i1]
        i += 1
        i1 += 1
    while i2 < len(arr2):        # moving elements from arr2 to list
        ls[i] = arr2[i2]
        i += 1
        i2 += 1


def mergeSort(ls, l, r):        # log(n) * n
    if l < r - 1:               # 1 Constant non-nested
        m = (l + r) // 2        # 1 Constant non-nested
        mergeSort(ls, l, m)     # O(log(n))  non-nested     breaks down all left side until sub-list size is 1
        mergeSort(ls, m, r)     # O(log(n))  non-nested     breaks down all right side until sub-list size is 1
        merge(ls, l, m, r)      # O(n)       nested         merges 2 sorted sub-lists into 1 sub-list


"""
asymptotic time complexity for mergeSort:

    1 + 1 + log(n) + log(n) * n
    log(n) + log(n) * n             get rid of lower order terms
    2log(n) * n                     simplified
    == log(n) * n                   remove 2 constant term
"""


# below is O(1) constant time complexity
# calls mergeSort function recursively, wrapper function, only for convenience
def merge_sort(ls):                 # intent was to hide certain parameters (like 0) in main
    mergeSort(ls, 0, len(ls))       # and to better beautify the program just pass the list into program