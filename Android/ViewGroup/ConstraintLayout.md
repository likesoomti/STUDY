# ConstraintLayout

```
public class ConstraintLayout 
extends ViewGroup 

java.lang.Object
   ↳	android.view.View
 	   ↳	android.view.ViewGroup
 	 	   ↳	android.support.constraint.ConstraintLayout
```

유연한 방법으로 위젯의 위치를 지정하고 크기를 조정할 수있는 기능.



졸업 작품 할때만 해도 RelatvieLayout 이 default layout 이었는데 각종 이유로 변경이 된 것 같다. 

### Constraint 알아보기

Constraint)은 즉 위치와 크기(차원)로 UI를 설정한다.

#### 속성

- ConstraintLayout 뷰 위치 속성

  - layout_constraintTop_toTopOf
  - layout_constraintTop_toBottomOf
  - layout_constraintBottom_toTopOf
  - layout_constraintBottom_toBottomOf
  - layout_constraintLeft_toTopOf
  - layout_constraintLeft_toBottomOf
  - layout_constraintLeft_toLeftOf
  - layout_constraintLeft_toRightOf
  - layout_constraintRight_toTopOf
  - layout_constraintRight_toBottomOf
  - layout_constraintRight_toLeftOf 
  - layout_constraintRight_toRightOf 
  - left, right 정렬에 대해 start, end속성 지원

  4개만 존재했던 RelativeLayout에 비해 유연하게 지원합니다.

### Relatvie 차이

속성

```

```

### Constraint Layout이 Relative Layout보다 나을까요?

1. Relative Layout은 성능 이슈를 보안하였다. 

   내부적으로 자체 제약 조건을 풀어내는 데 측정 수와 레이아웃 수가 두배 필요한데, 다른 알고리즘과 관계를 표현하여 성능을 더욱 빠르게 하였다.

2. 희소 행렬을 사용해 메모리를 적게 사용한다.

3. 또한 ConstraintLayout이 번들되지 않는 라이브러리라는 점이 개발자와 라이브러리 제작자가 눈여겨봐야 할 점입니다. 개발자가 완전히 통제할 수 있다는 것이 장점이죠. 뭔가를 읽었다면 바로 이해하고 결정할 수 있습니다. 여러분에게 맞는 버전을 사용할 수도 있습니다. 저희가 다음 날 다른 버전을 내놓더라도 여러분에 코드에는 영향을 미치지 않죠.