

# Spring mvc

## config
put the web and spring-config xml starter files in web.xml  
- spring config xml file : adds support for @Component scanning, shows where the static resources are located so they can be directly accessed with a get req, adds support for validation and formattin and conversion, and show the extension and the path of the views so we can reference the name of the view only and not its full path
- web.xml : Configure the spring MVC dispatcher servlet, which is like the main servlet that gets all the reqs

## Controllers
Controller classes are annotated with `@Controller`, and with `@RequestMapping` optionnaly, controller methods are annotated with `@RequestMapping(top lvl controller path if provided + path)`, specific requests are annotated just like in express xmpl PostMapping.  

controller methods return the name of the view (string)

To access query string params, pass in a String param annotated with `@RequestParam(name)` as an arg to the controller method, use thm in the jsp using `${param.key}`

We can pass a Model object to the controller methods to put data innit using `Model.addAttribute(key,value)`  

We can have access to model attributtes in jsp using `${key}`,  they render an empty string instead of null ila makanoch present

## Form tags and data bidnging 
- this is better explained w code check eclipse
- use the taglib thingy to load the tags in the jsp  
  
spring comes with a bunch of jsp tags that simplify alot of things. We create a bean that will hold thee form data and we set it as an attribute in the Mode object, then we send the view. The data that will be submitted will be set in the bean using the setter methods.  

The spring form elemets such as form:input have a `path` attribute, whose value is a property of the bean, when the form is loaded spring will call the getters and set the values if they were not empty. On submition the setters will be called