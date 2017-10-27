
// 현재 실행중인 파일의 경로를 나타낸다.
console.log('filename:',__filename);
// 현재 실행중인 폴더의 경로를 나타낸다.
console.log('dirname:',__dirname);
console.log('output: %d', 273); // %d를 입력하면 그 뒤에 숫자를 찾아 그 안에 출력해준다.

// 순서대로 출력된다.
console.log('%d + %d = %d', 273, 52, 273+52);

// 매개변수의 갯수가 다른 경우 
console.log('%d + %d = %d', 273, 52, 273+52,52273);
console.log('%d + %d = %d & %d', 273, 52, 273+52);

// 다른 문자열
console.log('문자열: %s', 'Hello World..!','특수 기호와 상관 없음');
console.log('JSON: %j',{name:'RintIanTta'});

// 시간 측정을 시작한다.
console.time('alpha');
var output = 1;
for(var i = 1 ; i <= 10; i ++){
    output *= 1;
}
console.log('Result:',output);

//시간 측정을 종료한다.
console.timeEnd('alpha');