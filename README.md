# AndroidNetwork
基于volley获取json的网络操作封装

## Import
[JitPack](https://jitpack.io/)

Add it in your project's build.gradle at the end of repositories:

```gradle
repositories {
  // ...
  maven { url "https://jitpack.io" }
}
```

Step 2. Add the dependency in the form

```gradle
dependencies {
  compile 'com.github.vilyever:AndroidNetwork:1.0.2'
}
```

## Usage
```java

VDNetwork.initial(this);
new VDNetwork<>(Model.class).fetchData("http://xxx", params, new VDNetworkDelegate<Model>() {
    @Override
    public Model willParseResponse(String response) {
        Model model = JsonToModel(response);
        return model;
    }

    @Override
    public void didResponse(Model data) {
        
    }

    @Override
    public void didOccurError(VDNetworkError error) {
        
    }
});
```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

