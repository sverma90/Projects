# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#   NAME: Soumil Verma                                                  #
#   DATE: August 21st, 2023                                             #
#   CLASS: EN 605.681.84                                                #
#   PROGRAM: is S consisted of x and y interveaving into each other?    #
#   COMPLEXITY: algo runs in Theta(N), N=len(s)                         #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
def is_interwoven(x,y,s):       # is s consisted of x and y interveaving into each other?
    comp_count = 0              # (C1) variable for number of comparison 
    is_in_x = [0] * len(s)      # (C2) create x array and set it to 0 * length of s
    found_x = False             # (C3) set found_x to false, used to determine interwoven
    x_full_pattern_count=0      # (C4) set x_full pattern to 0, used to return x repetition
    x_count = 0                 # (C5) keeps track of where im at in x
    indexes = []                # (C6) create indexes empty array
    for i,each in enumerate(s):     # (C7 * N) complexity: x (s/x) - linear in terms of s (looping each element in s)
        comp_count+=1               # (C8) increment number of comparison only when checking for x
        if each == x[x_count]:         # (C9) if each in x-ith
            x_count += 1                # (C10) increment x_count
            indexes.append(i)            # (C11) append to indexes array
        if len(indexes) == len(x):      # (C12) guarantees x count never goes over 1, if all matching indexes found that are in x....  
            for index in indexes:   # (C13 * N) linear in terms of (n/x) why? Because this can’t happen at every iteration. 
                is_in_x[index]=1       # (C14) ...I loop over all of them and set them to true.
                found_x = True      # (C15) set found_x to true
            x_full_pattern_count+=1     # (C16) if found all indexes, increment x pattern count
            x_count=0           # (C17) if found all indexes, reset x_count and indexes array
            indexes=[]          # (C18) reset indexes
    # # # # # # # # # # # # # # # # # # # # # # #
    # Check remaining chars for interwoven y    #
    # # # # # # # # # # # # # # # # # # # # # # #
    y_count = 0                     # (C19) set y_count to 0
    y_full_pattern_count = 0        # (C20) set y_full pattern to 0, used to return y repetition
    found_y = False                 # (C21) set found_y to false, used to determine interwoven
    is_always_valid = True          # (C22) set is_always_valid to true, used to determine interwoven
    is_in_y = [0] * len(s)          # (C23) create y array and set it to 0 * length of s
    indexes = []                    # (C24) create indexes empty array
    for i,each in enumerate(s): # (C25 * N) complexity: y (s/y)
        comp_count+=1           # (C26) increment number of comparison only when checking for y
        if is_in_x[i]:          # (C27) each time when we find something thats in x, we continue...
            continue            # (C28) because we are checking for y
        if y[y_count] == each:  # (C29) ...now when we try to find y, we increment and don’t have to recheck it
            y_count += 1        # (C30) increment y_count +1
            indexes.append(i)       # (C31) add to indexes array each time we find y
            if y_count == len(y):       # (C32) if we have found the complete instance of y
                for index in indexes:       # (C33 * N) for each index in indexes
                    is_in_y[index] = 1      # (C34) set is_in_y to 1 based on index
                y_full_pattern_count+=1     # (C35) increment y_full_pattern count +1
            indexes = []                    # (C36) reset indexes array after completion
        else:                           # (C37) if not interwoven...
            is_always_valid = False     # (C38) ...return false
        if len(y) == y_count:           # (C39) if the length of y = y_count
            y_count = 0                 # (C40) reset y_count and found_y
            found_y = True              # (C41) set found_y to true
    # # # # # # # # # # # # # # # # # # # # # # #
    # All return and/or print statements        #
    # # # # # # # # # # # # # # # # # # # # # # #
    print(f'Number of Comparisons made: {comp_count}')      # (C42) print number of comparisons made
    is_interwoven = (found_y and found_x and is_always_valid)   # (C43 * N) return is interwoven value (T or F)
    return is_interwoven, is_in_x, is_in_y, x*x_full_pattern_count, y*y_full_pattern_count  # (C44 * N) returns is interwoven, x and y position, x and y repetition