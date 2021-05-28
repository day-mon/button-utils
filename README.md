# button-pagination
## Adds Pagination API through Discord buttons

see src/main/examples for examples

docs coming soon

gradle:

```gradle
repositories {
  maven { url 'https://jitpack.io' }
}

dependencies {
  implementation 'com.github.arynxd:button-pagination:Tag'
}
```

maven:

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
    <groupId>com.github.arynxd</groupId>
    <artifactId>button-pagination</artifactId>
    <version>Tag</version>
</dependency>
```

## TODO
- Add timeout checks for greater than 15 minutes (interaction limit)
- Slash command paginator
- Refactor & organise
