package cn.com.cybertech.sdly.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Streams;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Lambda {

    public static void main(String[] args) throws IOException {
/*        Optional<List<String>> optional = Test.test("s");
        optional.map(strings -> Optional.of(strings.stream().map(a->a.toUpperCase()))).get().get().collect(Collectors.toList()).forEach(a-> System.out.println(a));
        File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });
        Stream.of(files).forEach(file -> System.out.println(file.getName()));
      */
        List<Apple> apples=Lists.newArrayList();

    }

    public void prettyPrintApplie(List<Apple> apples,PrettyPrint prettyPrint){
        apples.forEach(apple -> prettyPrint.print(apple));
    }

    public void methodRef(){
        File file=new File(".");

        File[] files1 = file.listFiles(File::isHidden);
        assert files1!=null;
        Test test=new Test();
    }
}

class Test{
    public static Optional<List<String>> test(String s){
        return Optional.of(Lists.newArrayList("a","b","c"));
    }

    public  void Opfile(File file){
        System.out.println(file.getName());
    }
}


@FunctionalInterface
interface  Itest{
    void filter(Test test);
}
@FunctionalInterface
interface PrettyPrint{
    void print(Apple apple);
}
//产地
class Apple{
    private String color;
    private String weight;
    private String palceOfOrigin;

    public String getPalceOfOrigin() {
        return palceOfOrigin;
    }

    public void setPalceOfOrigin(String palceOfOrigin) {
        this.palceOfOrigin = palceOfOrigin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}