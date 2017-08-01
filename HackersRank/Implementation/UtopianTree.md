# Utopian Tree

## problem

The Utopian Tree goes through 2 cycles of growth every year. Each spring, it doubles in height. Each summer, its height increases by 1 meter.

Laura plants a Utopian Tree sapling with a height of 1 meter at the onset of spring. How tall will her tree be after growth cycles?

Input Format

The first line contains an integer, , the number of test cases. 
 subsequent lines each contain an integer, , denoting the number of cycles for that test case.

Constraints 
 

Output Format

For each test case, print the height of the Utopian Tree after  cycles. Each height must be printed on a new line.

Sample Input

3
0
1
4
Sample Output

1
2
7
Explanation


봄에는 *2 증가 
여름에는 +1 증가
봄,여름 시즌을 번갈아서 가는 경우 

## solve

```javascript


function main() {
    var t = parseInt(readLine());
    for(var a0 = 0; a0 < t; a0++){
        var n = parseInt(readLine());
    
    var result = 1;
    //홀수일경우 *2 짝수일경우 +1
    for (var i = 1 ; i <= n ; i ++)
        (i%2 == 1) ? result*=2 : result+=1;
    console.log(result);
    
    }
}


```