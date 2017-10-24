# Cats and a Mouse

## problem

고양이 둘이 쥐를 잡는다

같은 거리에 있을경우 = 마우스 도망감

한 고양이가 가까운경우, 그친구가 잡는다 . 

누가 잡는지 출력


## solve
```javascript

function main() {
    var q = parseInt(readLine());
    for(var a0 = 0; a0 < q; a0++){
        var x_temp = readLine().split(' ');
        var x = parseInt(x_temp[0]);
        var y = parseInt(x_temp[1]);
        var z = parseInt(x_temp[2]);
        
        if(Math.abs(x-z) == Math.abs(y-z))
            console.log("Mouse C");
        else if(Math.abs(x-z) > Math.abs(y-z))
            console.log("Cat B");
        else 
            console.log("Cat A");
    }
}


```