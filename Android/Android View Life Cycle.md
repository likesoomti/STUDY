# Android View LifeCycle



## 궁금했던 점은  

에듀위드 강의를 들으면서, 버튼 커스텀하는 과정에서 뷰 라이프 사이클에 대한 공부를 하고 클릭되었을때, 이미지가 변화하는 커스텀 버튼을 공부했다. 뷰가 붙히고 나면 재갱신을 할 때 `invalidate()` 가 그래픽을 재갱신하는 함수라고 설명이 되었는데, `invalidate()` 를 호출하지 않음에도 갱신이 되는게 궁금하여 여러 저러 찾아본결과 밑의 그림을 발견하였다.

### 결론먼저





![Alt text](https://codentrick.com/content/images/2015/07/android_view_lifecycle.png)







어떤 방식으로 돌아가는지 알아보기 위해 로그를 찍어보았다. **왜인지 모르겠는데 mesure 함수는 오버라이드에 없었다.(1)**

```java
package com.soomti.edwith;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import static android.content.ContentValues.TAG;

public class BitmapButton extends AppCompatButton {

    @Override
    protected void onAttachedToWindow() {
        Log.e(TAG, "onAttachedToWindow: " );
        super.onAttachedToWindow();

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure: " );
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.e(TAG, "layout: " );
        super.layout(l, t, r, b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.e(TAG, "onLayout: " );
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.e(TAG, "dispatchDraw: " );
        super.dispatchDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG, "draw: draw" );
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(TAG, "onDraw: ");
        super.onDraw(canvas);
    }

    @Override
    public void invalidate() {
        Log.e(TAG, "invalidate: " );
        super.invalidate();
    }

    public BitmapButton(Context context) {
        super(context);
        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        setBackgroundResource(R.drawable.heart);
        setTextSize(100);
        float textsize = getResources().getDimension(R.dimen.textSize);
        setTextSize(textsize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int buttonstatus = event.getAction();

        if(buttonstatus == MotionEvent.ACTION_DOWN) {
            setBackgroundResource(R.drawable.heart2);

        }else if (buttonstatus == MotionEvent.ACTION_UP){

            setBackgroundResource(R.drawable.heart);

        }
        // 이미지를 다시 그려주어야 하기 때문에 invalidate() 를 호출
       // invalidate();
        return true;
    }
}
```

실행한 로그 

```verilog
// invalidate 재갱신 호출..? 왜두번..? (2)
05-03 14:42:32.353 9131-9131/com.soomti.edwith E/ContentValues: invalidate: 
05-03 14:42:32.354 9131-9131/com.soomti.edwith E/ContentValues: invalidate: 
// 두번째 컨스트럭터 호출. 
// 두번째 생성자는 "레이아웃"으로 만들었을 때 사용한다고 했는데 메인액티비티에 대한 언급인가???
05-03 14:42:32.356 9131-9131/com.soomti.edwith E/ContentValues: BitmapButton: constructor!2
// init 함수. 버튼에 대한 설정이 초기화 되면 GL? 그래픽 렌더링 해주는듯 
05-03 14:42:32.444 9131-9148/com.soomti.edwith D/OpenGLRenderer: HWUI GL Pipeline
// 뷰가 윈도우(화면?) (3) 첨부 될 때 호출됩니다.
05-03 14:42:32.501 9131-9131/com.soomti.edwith E/ContentValues: onAttachedToWindow: 
// 뷰의 가로/세로 크기를 측정한다.
05-03 14:42:32.593 9131-9131/com.soomti.edwith E/ContentValues: onMeasure: 
05-03 14:42:32.744 9131-9131/com.soomti.edwith E/ContentValues: onMeasure: 
// 크기를 인지했으니 이제 어디 위치할지 정해준다. 
05-03 14:42:32.766 9131-9131/com.soomti.edwith E/ContentValues: layout: 
    onLayout: 
// 화면에 그려준다.
05-03 14:42:32.862 9131-9131/com.soomti.edwith E/ContentValues: draw: draw
05-03 14:42:32.863 9131-9131/com.soomti.edwith E/ContentValues: onDraw: 
// 자식들에게 viewGroup 이 그려주는걸 알려준다고 한다. 
05-03 14:42:32.864 9131-9131/com.soomti.edwith E/ContentValues: dispatchDraw: 

```



###  onAttachedToWindow() 

: 뷰를 윈도우에 부착해준다 근데 윈도우가 뭐죠..?

##### 윈도우가 뭐지? **(3)**

![Alt Text](https://i.stack.imgur.com/gPOF7.png)

간단히 말해 저 컬러로 걸쳐진 3개의 네모박스가  `window` 라고 한다. 안드로이드 화면에보이는 "화면 = 창" 인것 같다. 

저 사진에 친 3박스 각각이 `window` 라고 불린다. 

그리고 그걸 관리하는 `windowManager` 가 존재하는것같다. `windowManager`는 어플리케이션이 `window` 를 요청하면 해당하는 `window` 의 `Surface`를 생성해준다고 한다. 



##### Surface

화면의 비트를 하나하나 가지고 있는 객체이다. 윈도우마다 서페이스를 가지고 있다.

서페이스가 락되면?(4) 캔버스로 반환한다고한다. 캔버스로 서페이스에 있는 버튼을 그린다고생각했는데 아닌것같다..

##### Canvas

그림을 그릴때 사용하는 도구들을 가지고 있다. 

https://stackoverflow.com/questions/9451755/what-is-an-android-window





밑의 구조로 움직이는 것 같다 

```
Application //어플리케이션키면 메인 액티비티 호출 
  Activity // 액티비티는 화면에 띄워야하니 창을 관리하는 매니저 호출
    Window Manager // 윈도우 매니저가 윈도우를 실행하겟지
      Window // 윈도우 호출
        Surface //
          Canvas --->
            View Root ---> 
              View Group --->
                View ---> 
                  Bitmap/Open GL panel ---> 
                    Current Surface Buffer ---> 
                      Surface Flinger --->
                        Screen
```



https://stackoverflow.com/questions/9451755/what-is-an-android-window



### OnMeasure()

뷰의 가로/세로 크기를 측정한다.

### onLayout()

받은 뷰들의 위치를 할당해준다.

### onDraw()

받은 정보를 통해 그려준다

### onDispatchDraw()

자세하지않다.. 뷰그룹에게 그려준다는 것을 알려준다.



### requestLayout()

레이아웃 변경시 요청된다.

근데 나는 이걸 요청하지 않았는데 왜 요청이 되는걸까?

찾다가 이런 답변을 보았다.

```
말씀과 같이 루틴에 따라 화면만 갱신하는 것이라면 invalidate(), 레이아웃이 변해야하는 경우 requestLayout()을 호출하시면 됩니다. 둘다의 상황이라면 둘다 호출하시면 됩니다. UI와 상관없는 값이라면 둘다 호출 안하시면 됩니다. 위의 예제에서 값에 따라 텍스트의 길이가 달라지고 속성(width, height의 fill_parent, wrap_contents)에 따라 크기가 변경될수 있기 때문에 requestLayout()을 호출하고 있습니다. android 4.0 부터는 layout 변경사항이 없을때는 requestLayout() 을 호출해도 루틴을 동작하지 않아 오버헤드를 최소화한다고 들었습니다.(확실한 정보는 아닙니다.)

출처: http://onecellboy.tistory.com/344 [신불사 - 신현호라 불리는 사나이]
```

배경이 변하는건 레이아웃 변경이 아닌데 

## 버튼 클릭 시

로그

```verilog
05-03 15:44:52.928 9131-9131/com.soomti.edwith E/ContentValues: onMeasure: 
05-03 15:44:52.929 9131-9131/com.soomti.edwith E/ContentValues: layout: 
    onLayout: 
05-03 15:44:52.930 9131-9131/com.soomti.edwith E/ContentValues: draw: draw
    onDraw: 
05-03 15:44:52.931 9131-9131/com.soomti.edwith E/ContentValues: dispatchDraw: 
```



---



 ## 알수 없는점

- 그림에 있는`measure()` 메서드가 실제로는 존재하지 않는다 **(1)**
- `invalidate()`가 재갱신이라고했는데, 왜 처음부터 두번이나 호출이 될까? **(2)**
- 언제 `invalidate()`가 호출되고 언제 `requestLayout()` 이 호출되는지(3)