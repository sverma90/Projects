# Sort Selection

<p><b>Data Structure used:</b> Recursion</p>

<p><b>Algorithms used:</b> Quick, Merge, Heap, Insertion</p>

<p>Write a Quicksort and a Heap Sort. They should both be recursive or both be iterative, so that the overhead of recursion will not be a factor in your comparisons.</p>

<p></p>

<ul>
 <li>Select the first item of the partition as the pivot. Treat partitions of size one and two as stopping cases.</li>
 <li>Same pivot selection. For a partition of size 100 or less, use an insertion sort to finish.</li>
 <li>Same pivot selection. For a partition of size 50 or less, use an insertion sort to finish.</li>
 <li>Select the median-of-three as the pivot. Treat partitions of size one and two as stopping cases.</li>
</ul>

<p>Create input files of four sizes: 50, 1000, 2000, 5000 and 10000 integers.  For each size file make 3 versions.  On the first use a randomly ordered data set.  On the second use the integers in reverse order.  On the third use the integers in normal ascending order.  (You may use a random number generator to create the randomly ordered file, but it is important to limit the duplicates to <1%.</p> 