# Project 3: Hash Tables

* Author: Aida Gomezbueno Berezo

* Class: CS321

* Semester: Fall 2022

## Overview

In this project, two different Hash Tables are implemented by performing both linear probing and double hashing techniques. 

## Compilation & Usage

Before anything, the code must be compiled as usual, typing javac *.java. After that, some parameters must be passed through the command line in the following form.

java HashTest \<input type\> \<load factor\> \[<debug level\>]
	
> \<input type\> = 1 - data generation based on random integers
	
> \<input type\> = 2 - date generation based on current date
  
> \<input type\> = 3 - words from file word-list
  
> 0 <= \<load factor\> <= 1 - how full the hash table is allowed to get while hashing
	
> \[<debug\> = 0 - print summary of experiment on the console]

> \[<debug\> = 1 - print summary and also the hash tables with number of duplicates and number of probes into two files]

 
## Results

As for the results, the following tables show the average number of probes vs. load factors (alpha) performing both probing techniques. One table for each different data source. 

Input source 1: random number

alpha	linear	double
-----------------------
0.5	01.51	1.381
0.6     01.77	1.522
0.7     02.17	1.717 
0.8     02.99	2.015
0.9     05.36	2.540
0.95    10.99	3.130
0.98    29.15	4.001
0.99    38.17	4.649

Input source 2: date

alpha	linear	double
-----------------------
0.5	1.0	1.0
0.6	1.0	1.0
0.7	1.0	1.0
0.8	1.0	1.0
0.9	1.0	1.0
0.95	1.0	1.0
0.98	1.0	1.0
0.99	1.0	1.0 

Input source 3: word-list

alpha	linear	double
-----------------------
0.5	1.59	1.390
0.6	2.15	1.534
0.7     3.60	1.721
0.8     6.71	2.016
0.9     19.82	2.569 
0.95    110.59	3.186 
0.98    324.21	4.020
0.99    471.67	4.696

