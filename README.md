# build-extension-sample
> 豆蔵デベロッパーサイトのブログ記事で利用しているサンプルアプリ

## 利用している記事
- [xxxx](https://developer.mamezou-tech.com/blogs/2023/11/26/build-extension-sample/)

## contents 
|プロジェクト|内容|
| ---------- | --- |
| [Helidonサンプル](helidon/) |Helidonを使ったBuildCompatibleExtensionのサンプルアプリ |
| [Quarkusサンプル](quarkus/) |Quarkusを使ったBuildCompatibleExtensionのサンプルアプリ |

## ビルドと実行
サンプルアプリのビルドにはMavenが必要です
> [!IMPORTANT]
> ビルドと実行にはHelidonサンプルはJava21以上が、QuarkusサンプルはJava17以上が必要です

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
