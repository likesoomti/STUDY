# Designer PDF Viewer

## problem 

Sample Input 0
1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5
abc

Sample Output 0
9
Explanation 0

We are highlighting the word abc:
The tallest letter in abc is b, and . The selection area for this word is .
Note: Recall that the width of each character is .

** 가장 큰 알파벳 * 알파벳 길이 출력

## solve

```javascript 

function main() {
    h = readLine().split(' ');
    h = h.map(Number);
    var word = readLine();
    //알파벳,최대길이 저장 변수 선언 
    var alphabet= 'abcdefghijklmnopqrstuvwxyz';
    var max=0;
    //단어의 최대 길이 구하기
    for( i in word)
        max = h[alphabet.indexOf(word[i])] > max ? h[alphabet.indexOf(word[i])] : max;
    console.log(max *word.length)

}

```