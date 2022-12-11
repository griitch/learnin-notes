package code_snippets;
import java.util.function.Consumer;

public class LambdaScopeTest {
	 
    public int x = 0;
 
    class FirstLevel {
 
        public int x = 1;
        
        void methodInFirstLevel(int x) {

            int z = 2;
             
            Consumer<Integer> myConsumer = (y) ->  
            {
                // The following statement causes the compiler to generate
                // the error "Local variable z defined in an enclosing scope
                // must be final or effectively final" 
                //
                // z = 99;
              //int x = 88; //THIS generates an error we're trying to shadow x of the enclosing scope
                System.out.println("x = " + x); //in this scope, x is the parameter, not the attribute
                System.out.println("y = " + y);
                System.out.println("z = " + z); 
                System.out.println("this.x = " + this.x); //this.x is x the attribute
                System.out.println("LambdaScopeTest.this.x = " +
                    LambdaScopeTest.this.x);
            };
 
            myConsumer.accept(x);
 
        }
    }
 
    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}