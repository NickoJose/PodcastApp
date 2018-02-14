# Coding guidelines:

1. Same line open brackets and camelCasing. void dealWithIt(){

   }

2. All non-constructor methods must have a javadoc header. This header must have an informally written description intended to most 
accurately explain the function to the level of a semi-experienced programmer. If applicable, leave a note on how the method use could 
be misunderstood. (Eg, Does a method accept on '>' vs '>=' ).

   - This header must contain one @param for every method parameter, even if it's self-explanatory in name (in which case it is acceptable to have a 1-3 word description).

   - The header must also contain an @return field. If the method is void, "@return - void" is perscribed.

3. Non-header comments are left up to programmer discression in order to show logic and chunk the code into 
sections.

4. Common abbreviations appearing throughout the project, 
such as 'desc', 'ep', etc should not have comments explaining them.

5. Classes need a header if and only if the purpose is not immediately obvious from the name. 

6. Variable names should be highly abbreviated when the object type is unique in the method.
For example, a method containing only one Episode object should be named "ep". 

   - This is because it is (hopefully) contextually obvious what the object is, and it aids in visual chunking for quickly reading.
If an object type is not unique in a method, detailed names are advised to reduce commenting and maintain
readability.

7. Recommended Unexplained abbreviation list:

	- Episode: ep
  
	- Channel: ch
  
	- result: res
  
	- Object: obj
  
	- iterator: iter
  
