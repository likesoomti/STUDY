# 드로어블

버튼을 커스텀하는 과정에서, 기억이 안나 다시 정리해봅니다.



## 드로어블 간단 정리

제가 이해한 드로어블을 간단히 말하면 `사용자 배경 커스텀`입니다.

예를들어 `버튼을 예쁘게 커스터마이징` 하고싶을 경우,

background 에 배경색을 지정하거나, `res/drawable` 에 이미지 파일을 설정하여 이미지를 보이게 할 수 있지만,

````
버튼이 클릭했을때 다른 컬러를 보여주고싶어!!

또는

버튼이 클릭되면 다른 사진을 보여주고싶어!!!
````

의 경우에는 일반 배경컬러 / 이미지는 정적이기 때문에 이 방법을 해결할 수 없습니다.

따라서 

####사용자가 이러한 그래픽을 커스텀을 하고싶을때, 그에 해당하는 그래픽을 xml 로 만들 수 있습니다.

이 xml 로 그래픽을 만드는 것을 **드로어블**이라고 합니다. 



가장 자주 사용하는 드로어블은

1. 상태 드로어블 
2. 쉐이프 드로어블

이있는데요 



## 드로어블 만들기

드로어블은 

```
res>drawable
```

에서 우클릭을 하면 두번째에 `drawable resoure file` 메뉴가 있습니다. 

그걸 클릭해서 만들 수 있습니다.

#### sample

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

</selector>
```

파일 이름이 리소스 ID로 사용됩니다.

제가 이 글을 쓰게된것은 쉐이프 드로어블 커스텀이 생각 안나서이기 때문에 쉐이프 드로어블 먼저 알아봅시다.



## Shape Drawable

`public class Shape Drawable extends Drawable`

- ShapeDrawable은 `Shape`객체를 가져 와 화면에서 그 존재를 관리합니다. 
- Shape의 기본 설정값은 `RectShape`됩니다.



#### shape

셰이프 드로어블을 사용하기 위해서는 메인 루트 태그가 `<shape>` 이어야 합니다. 따라서 디폴트 설정값인 `<selector>`를  `shape` 로 바꿔줍니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">

</shape>
```



#### shape 요소

`<shape>` 는 기본 요소로  `"rectange"`을 가집니다. 또한 여러 속성을 가지고 있습니다.

- rectangle : 사각형 
- oval : 타원형
- line : 가로선
- ring : 고리형 
  - 고리형에만 사용되는 속성
    - `android:innerRadius` 내부 반경
    - `android:innerRadiusRatio`
    - `android:thickness`
    - `android:thicknessRatio`
    - `android:innerRadius`로 재정의됩니다. 기본값은 3입니다.
    - `android:useLevel`
    - `LevelListDrawable`



#### Corner

셰이프가 사각형인경우 둥근 모서리를 생성할 수 있습니다.

#### gradient

셰이프에 그라데이션 색상을 지정합니다.

- `android:angle`

  그라데이션의 각도를 나타냅니다

  1. 0 

      왼쪽에서 오른쪽

  2. 90 

     하단에서 상단으로 진행됩니다

  3. 45의 배수여야 합니다.

  4. 기본값은 0입니다.

- `android:centerX` 

  그라데이션 중심에서 상대적인 X 의 위치를 나타냅니다.

- `android:centerY`

  그라데이션의 중심에 대한 상대적인 Y 위치를 나타냅니다.

- `android:centerColor`

  시작 색상과 끝 색상 사이에 오는 색상(선택 항목)으로, 16진수 값이나 [색상 리소스](https://developer.android.com/guide/topics/resources/more-resources.html#Color)로 지정됩니다.

- `android:endColor` 

  끝 색상으로, 16진수 값이나 [색상 리소스](https://developer.android.com/guide/topics/resources/more-resources.html#Color)로 지정됩니다.

- `android:gradientRadius`

  그라데이션의 반경입니다. `android:type="radial"`인 경우에만 적용됩니다.

- `android:startColor`

  시작 색상으로, 16진수 값이나 색상 리소스로 지정됩니다.

- `android:type`

  적용할 그라데이션 패턴의 유형입니다. 

  - "linear :선형 그라데이션. 이는 기본값입니다

  - "radial": 원형 그라데이션. 시작 색상은 가운데 색상입니다.

  - "sweep" :스위핑 라인 그라데이션.

    

- `<padding>`

  포함하는 뷰 요소에 적용할 패딩입니다.

  - left

  - right

  - bottom

  - top

    

- `<size>`

  - width
  - height

- `<solid>`

  셰이프를 채울 단색입니다.

  - color

- `<stroke>`

  셰이프에 대한 스트로크 선입니다.

  - width

  - color

    

## 샘플 코드

버튼을 커스텀하는 샘플 코드를 만들어 봅시다. 

res/drawable/gradient_box.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:startColor="#FFFF0000"
        android:endColor="#80FF00FF"
        android:angle="45"/>
    <padding android:left="7dp"
        android:top="7dp"
        android:right="7dp"
        android:bottom="7dp" />
    <corners android:radius="8dp" />
</shape>
```

그리고 뷰에 커스텀한 드로어블을 xml 적용하는 코드입니다.

```xml
<TextView
    android:background="@drawable/gradient_box"
    android:layout_height="wrap_content"
    android:layout_width="wrap_content" />
```

java 코드에서도 적용할 수 있습니다.

```
Resources res = getResources();
Drawable shape = res. getDrawable(R.drawable.gradient_box);

TextView tv = (TextView)findViewByID(R.id.textview);
tv.setBackground(shape);
```

참고 항목:

- `ShapeDrawable`

## 참조

```
https://developer.android.com/guide/topics/resources/drawable-resource
```

