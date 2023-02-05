# using the beans

MyObject = context.getBean("id of the bean", MyObject.class );

# xml based config

instanciate beans with the bean tag with the props id and class  

add dependencies either with :
- constructor : using the constructor-arg tag, props = { ref }  
    + constructor dep injection requires a constructor with the dependecies as params to be present
- setter : using the property props = { name, ref }
    + setter injection requires a no arg constructor and public setter methods, spring will look for the method setName 
- use { value } instead of ref for primitive types  
+ we can get values from a properties file, which is a text file that contains key=value lines, and we can access the values from the xml document using ${key} after loading the file using the context:property-placeholder tag

## beans lifecycle and scopes
scope = how many instance will be created at each request to the bean and other things
 - `singletone` : by default
 - `prototype` : new instance each call
 - `session and global-session` : for web apps

lifecycle methods : no args, any access modifier, void return type, any name
-   init-method attribute : called after instanciation
-   destroy-method : clean up method, does not get called for prototype scoped beans

# annotations 
enable component scanning in a given package and its subpackages by adding `<context:component-scan base-package="com.griitch" />` to the xml config file.

Add `@Component("bean id")` annotation to the classes u wanna instanciate as beans.

anotate components with `@Scope(value)` to give them a scope

anotate lifecycle methods with @PreDestroy and @PostConstruct, but you need to add a package tho ( its in the tools folder )

if no bean id was provided, the bean id will be the name of the class witht the first letter lowercased   
## Autowiring
annotate constructors, setters, or fields (even if private) with `@Autowired`, if there was a matching bean in the container, itll get assigned to the dep.   

We specify which bean exactly to choose if there were too many using the `Qualifier('bean id')` annotations.  

for construcor autowiring, anotate the params with qualifier not the function.      

Not only setters can be autowired, any other method that takes the given dependency as param can be autowired.  

# Configuration with java code only, no xml whatsover

use AnnotationConfigApplication context.  
Create a java class annotated with `@Configuration`  
from here we can either :  
- scan for @Component annotated classes in a package using the `@ComponentScan(fully qualified package name)` annotation.  
- manually create beans using java code as follows :  create a method in the config that returns the bean, it must be annotated with @Bean, the bean id will be the name of the method.  

to read props from a proprties files, use the `@PropertySource(path)` annotation, and annotate class fields with `@Value("${key in the props file"})`  

Usually, component scanning is used, the bean annotation is used to wrap around third party classes to which you dont have access to the source code so you can annotate it with @Component





    
     

    