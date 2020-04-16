# Daikon Core

![Daikon Core](./logo.svg)

Daikon Core is the routing implementation used by Daikon HttpServer and others Daikon libraries.

## How to add Daikon Core to your project
[![](https://jitpack.io/v/daikonweb/daikon-core.svg)](https://jitpack.io/#daikonweb/daikon-core)

### Gradle
- Add JitPack in your root build.gradle at the end of repositories:
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

- Add the dependency
```
implementation 'com.github.DaikonWeb:daikon-core:1.3.2'
```

### Maven
- Add the JitPack repository to your build file 
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
- Add the dependency
```
<dependency>
    <groupId>com.github.DaikonWeb</groupId>
    <artifactId>daikon-core</artifactId>
    <version>1.3.2</version>
</dependency>
```

## Resources
* Documentation: https://daikonweb.github.io
* Examples: https://github.com/DaikonWeb/daikon-examples

## Authors

* **[Marco Fracassi](https://github.com/fracassi-marco)**
* **[Alessio Coser](https://github.com/AlessioCoser)**

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details
