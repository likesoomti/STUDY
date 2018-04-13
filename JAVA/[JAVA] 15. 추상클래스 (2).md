# [JAVA] 15. 추상클래스 (2).md

##### 리뷰하기 - 추상클래스란

추상클래스는 슈퍼클래스가 있고, 이걸 상속하는 클래스가 있을 경우 슈퍼클래스에 있는 메서드를 강제적으로 사용해야 할 때

abstract class 를 사용하여 강제 구현 할 수 있습니다.



### 1. 어린이 집 아이들의 식대 계산 프로그래밍

```
어린이집에 어린이들의 점심 식대 관련한 프로그래밍

어린이들은 쌀밥, 불고기, 두부조림, 미역국이 배식됩니다.
추가로 요플레, 바나나, 우유, 아몬드가 간식으로 배식됩니다.

기본적인 쌀밥, 두부조림, 미역국 은 모든 아이들이 먹습니다.
하지만, 개인 체질에 따라서는 간식은 선택할 수 있습니다.
특히 알레르기있는 아이들은 간식을 전부 선택하지 않을 수 있습니다.
```



```java
abstract class LunchMenu {

    public int rice;
    public int bulgogi;
    public int banana;
    public int milk;
    public int almond;

    public LunchMenu(int rice,int banana,int bulgogi, int milk, int almond){
        this.rice = rice;
        this.banana = banana;
        this.bulgogi = bulgogi;
        this.milk = milk;
        this.almond = almond;
    }

    public abstract  int calculating();
}
class child1 extends LunchMenu{

    public child1(int rice,int banana,int bulgogi, int milk, int almond){
        super(rice,bulgogi,banana,almond,milk);
    }
    @Override
    public int calculating() {
        return rice+bulgogi+banana;
    }
}
class child2 extends LunchMenu{

    public child2(int rice,int banana,int bulgogi, int milk, int almond){
        super(rice,bulgogi,banana,almond,milk);
    }
    @Override
    public int calculating() {
        return rice+bulgogi+banana+milk;
    }
}
class PriceTable {
    public static  final int RICE = 1000;
    public static final int BANANA = 2000;
    public static final int MILK = 2000;
    public static final int BULGOGI = 1500;
    public static final int ALMOND = 2000;
}
public class LunchMenuEX {
    public static void main(String[] args) {
        LunchMenu child1 = new child1(PriceTable.RICE,PriceTable.BANANA,PriceTable.BULGOGI,PriceTable.MILK,PriceTable.ALMOND);
        LunchMenu child2 = new child2(PriceTable.RICE,PriceTable.BANANA,PriceTable.BULGOGI,PriceTable.MILK,PriceTable.ALMOND);

        System.out.println("child1"+child1.calculating());
        System.out.println("child2"+child2.calculating());
    }
}
```

