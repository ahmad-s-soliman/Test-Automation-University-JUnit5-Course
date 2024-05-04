package junit5tests;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParamProvider {

    //returns 2 params of type stream to the associated test function.
    static Stream<Arguments> sourceStream_StringDouble(){ //static so that it's associated
        // with the class so that it can be used in a difference class using the class name
        //data processing here
        return Stream.of(arguments("strawberry", 5.0), arguments("apples", 2.0));
    }

}
