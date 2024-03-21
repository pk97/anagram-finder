# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 21

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

**ASSUMPTIONS**
 
1. duplicates are allowed in both input and output.
2. Updated Spring and Gradle version to work with Java 21.
3. The input file is sorted by word length & check line contains 1 word.

**Description**

Anagram finder reads data from an input txt file based on word length. It reads data line by line and store it in
a map to group all the anagrams. When words of greater size are read, the map is cleared to prevent memory overflow.

**Time & Space Complexity**
Time complexity depends on the input txt file, i.e O(N) where N is size of file.
Space complexity is maximum size that a particular set of words of same length are present in the file.
In worst case, it is O(N).


