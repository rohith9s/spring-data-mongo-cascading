# Spring Data MongoDB cascade save/delete on DBRef objects.

 Cascade the document operations:
 	- Spring Data Mongo doesn't support cascading of the objects by default. The mapping framework does not 
 	handle cascading saves/delete. 
 	
 	- If you change an Address object that is referenced by a User object, you must save explicitly the Address
 	 object. On saving/deleting User object will not automatically save Address object in the property of User. 
 	 
 	- To overcome this we have to implement a custom way to cascade save & delete operations.   
 	

below is the endpoint for testing saveUser
	```
		http://localhost:8080/test/saveUser
	```
	
below is the endpoint for testing deleteUser
	```
		http://localhost:8080/test/deleteUser?userName=hari@gmail6d4066c9-f4cc-4973-85fe-689d35b9d206.coms
	```
