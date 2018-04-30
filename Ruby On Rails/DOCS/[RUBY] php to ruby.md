\# PHP TO Rails

php 파일을 루비로 바꾸면서 사용했던 메서드 바꾼거 정리 

\#### $REMOTE_ADDR

ip 주소를 체크하는 메서드

\#####  rails 에서는

\```ruby

request.remote_ip

\```

이렇게 변환하면 된다.

\#### microtime()

마이크로 초 단위의 현재 Unix 타임 스탬프 (epoch)를 반환

\```php

echo microtime();

// => 0.63928794 1242744141

\```

\##### rails 에서는

\```ruby

epoch_mirco = Time.now.to_f;

epoch_full = Time.now.to_i;

epoch_fraction = epoch_mirco - epoch_full;

puts epoch_fraction.to_s + ' ' + epoch_full.to_s;

\# => 0.639287948608398 1242744141

\```

\#### preg_replace()

\```

$result = preg_replace("some(regex)here/", "replace_str", $str);

\```

\##### ruby 에서는 

\```

result = str.gsub(/some(regex)here/, 'replace_str')

\```