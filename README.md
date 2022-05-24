# kutil-java-log

## About

* javaのloggingライブラリ
* ファイル書き込みのカスタマイズ
* 配列やエラー時の出力を楽に

## Usage

```pom.xml
<!-- pom.xml -->
<!-- https://github.com/kigawa01/kutil-java-log -->
    <dependencies>
        <dependency>
            <groupId>net.kigawa.kutil</groupId>
            <artifactId>log</artifactId>
            <version>2.0</version>
        </dependency>
    </dependencies>

```

```java
// Main.java
import net.kigawa.kutil.log.log.KLogger;

class Main
{
  public static void main(String[] args)
  {
    KLogger logger = new KLogger(
            "name",
            null,
            Level.INFO,
            null);
    logger.enable();
    
    
    logger.info("line1","line2");
    /*
            output separately by argument
            example 
            > line1
            > line2
     */
    
    logger.info(new Object());
    /*
            when input objects, call toString() and output
     */
    
    logger.info(new Throwable());
    /*
            when input throwable, out stack trace
     */
  }
}
```

## Dependency

* java
* kutil-java

## Author

* kigawa
    * kigawa.8390@gmail.com

# Making

## Version

### Example: 9.1dev

* **9.1dev**
    * バージョン
    * **9**: メジャー
    * **1**: マイナー
    * **dev**: ブランチ/mainのときはなし

## ToDo
