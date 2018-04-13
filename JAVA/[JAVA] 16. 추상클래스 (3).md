# 16. 추상클래스 (3)

```
자동차 옵션 제작 프로그래밍
```



### 1. 자동차 옵션에 따라 변경되는 세금

```java
public class StarCarEX {
    public static void main(String[] args) {

        StarCar s1 = new StarCarLowGrade(CarSpecs.COLOR_BLUE,CarSpecs.TIRE_B,CarSpecs.DISPLACEMENT_2006,CarSpecs.HANDLE_B);
        StarCar s2 = new StarCarLowGrade(CarSpecs.COLOR_RED,CarSpecs.TIRE_W,CarSpecs.DISPLACEMENT_2010,CarSpecs.HANDLE_H);

        s1.getSpec();
        s2.getSpec();
    }
}
abstract class StarCar{
    String color;
    String tire;
    int displacement;
    String handle;

    public StarCar(String color,String tire,int displacement,String handle){
        this.color = color;
        this.tire = tire;
        this.displacement = displacement;
        this.handle = handle;
    }

    public abstract void getSpec();
}

class StarCarLowGrade extends StarCar {
    private  int tax = 1000;

    public  StarCarLowGrade(String color,String tire,int displacement,String handle){
        super(color,tire,displacement,handle);
    }

    @Override
    public void getSpec() {
        System.out.println("색상"+color);
        System.out.println("타이어:"+tire);
        System.out.printf("배기량:"+displacement);
        System.out.printf("핸들"+handle);

        if(displacement>2000) tax = 1500;
        System.out.println(" 세금 + tax");
    }
}

class CarSpecs{
    public static final String COLOR_RED = "레드";
    public static final String COLOR_BLUE = "파랑";

    public static final String TIRE_B = "기본 타이어";
    public static final String TIRE_W = "광폭 타이어;";

    public static final int DISPLACEMENT_2006 = 2000;
    public static final int DISPLACEMENT_2010 = 2200;

    public static final String HANDLE_B = "기본핸들";
    public static final String HANDLE_H = "좋은핸들";
}


```

