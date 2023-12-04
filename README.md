# build-extension-sample
> 豆蔵デベロッパーサイトのブログ記事で利用しているサンプルアプリ

> [!NOTE]
> このリポジトリのサンプルコードはRed Hatのプリンシパルソフトウェアエンジニアの[Ladislav Thon](https://github.com/Ladicek)さん個人のリポジトリにある[cdi-extensions-demo](https://github.com/Ladicek/cdi-extensions-demo)をもとにしています。Ladislav Thonさんのコードに対し最新のQuarkusでは動かない箇所や理解しやすいように一部修正したものをサンプルコードとして使っています。
> 


## 利用している記事
- [CDI 4.0 Lite - Build compatible extensionsをサンプルで理解する](https://developer.mamezou-tech.com/blogs/2023/11/26/build-extension-sample/)

## contents 
|プロジェクト|内容|
| ---------- | --- |
| [Helidonサンプル](helidon/) |Helidonを使ったBuildCompatibleExtensionのサンプルアプリ |
| [Quarkusサンプル](quarkus/) |Quarkusを使ったBuildCompatibleExtensionのサンプルアプリ |

## ビルドと実行
サンプルアプリのビルドにはMavenが必要です
> [!WARNING]
> ビルドと実行にはHelidonサンプルはJava21以上がQuarkusサンプルはJava17以上が必要です

### Helidonサンプルのビルドと実行
``` shell
# clone this repository
git clone https://github.com/extact-io/build-extension-sample.git
# change to Helidon Sample directory
cd helidon/
# local install
mvn clean install
# change to app directory
cd helidon-app/
# build the app
mvn -Pcopy-libs clean package -DskipTests
# launch the app
java -jar target/helidon-app.jar
# call the app
curl localhost:7001/processors
=> success
```
### Quarkusサンプルのビルドと実行
``` shell
# clone this repository
git clone https://github.com/extact-io/build-extension-sample.git
# change to Helidon Sample directory
cd quarkus/
# local install
mvn clean install
# change to app directory
cd quarkus-app/
# build the app
mvn clean package -DskipTests
# launch the app
java -jar target/quarkus-app/quarkus-run.jar
# call the app
curl localhost:8080/processors
=> success
```
