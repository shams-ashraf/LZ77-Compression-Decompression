# LZ77 Compression and Decompression

This project provides a simple Java implementation of the LZ77 compression algorithm. The program allows users to compress input strings using a sliding window and lookahead buffer, and then decompress them back using generated tags.

## Features

- Compresses text using the LZ77 algorithm
- Customizable window and buffer sizes
- Supports decompression from manual input of tags
- Command-line interaction
- Written in clean, easy-to-understand Java

## How to Use

### 1. Compile the code

javac LZ77.java
2. Run the program
java LZ77
3. Choose an option

Enter 1 to compress a string
Enter 2 to decompress tags

Example
Compression:

Input string:

abcabcabcabc
Window size: 6
Lookahead buffer size: 4

Resulting Tags:

<0, 0, a >
<0, 0, b >
<0, 0, c >
<3, 3, a >
<6, 3, null >
Decompression:

Input tags:

0 0 a
0 0 b
0 0 c
3 3 a
6 3 null

Output string:
abcabcabcabc
