# insticator_project
Data Structures<br>
User:<br>
<img height="400" width="700" src="https://user-images.githubusercontent.com/16629900/45602923-a0754980-b9f4-11e8-8667-1354bcc15a4a.png" /> 
Trivia:<br>
 



Poll:<br>
 
Checkbox:<br>
 










Matrix:<br>
 
Also, we have different answer and its composite embeddable id tables for each type of question. Take a example of Trivia:<br>
 







TriviaansId:<br>
 
Routers<br>
The application has many routers for users, questions, and questions under a user. <br>
The routers for a user include: <br>
@GetMapping("/users"): Retrieve all users in the application<br>
@GetMapping("/users/{id}"): Retrieve a user using the id<br>
@GetMapping("/users/{id}/questions"): Retrieve all questions of four types under a user’s profile<br>
The routers for questions under a user. Take the example of checkbox type<br>
@PostMapping("/users/{uId}/checkboxs/{qId}"): Save a user’s answers for a checkbox question<br>
@GetMapping("/users/{uId}/checkboxs/{qId}"): Get a user’s answers for a checkbox question<br>
@GetMapping("/users/{uId}/checkboxs"): Get a user’s all checkbox questions<br>
The routers for questions in general:<br>
Take the example of matrix type:<br>
@GetMapping("/matrixs"): Retrieve all matrix questions in the whole application<br>
@GetMapping("/matrixs/{id}"): Retrieve a matrix question using the id<br>
@GetMapping("/matrixs/{id}/options"): Retrieve a matrix question’s all options using the id<br>



Screenshots of states<br>
(including the required web service API)<br>
A user with id 1 named Tommy is created in the main function. Four questions with each one being each type are added to the user’s profile.<br>
 
Now, if a question is answered, which in the case is a checkbox one, it will not show in the user’s question list anymore.<br>
 
 
You are also check the user’s answer to that question. If the user has not answered that yet or he does not have that question, exceptions will be thrown with detailed explanations.<br>
 
 
If the user is trying to access a type of question and it is empty, then an error message will tell the user the reasons.<br>
 
However, you can still find the checkbox question in the question router.<br>
 
Now, let’s look at how trivia looks like. Let’s say the user chose an answer for a trivia question and it is send to the back end.<br>
 


If the answer is correct, then it will give a message ‘Correct Answer!’. Otherwise, it should be ‘Wrong Answer!’.<br>
 
The user can also look at the answer saved in the database.<br>
 
Now the trivia question is no longer in the user’s profile.<br>
 
Poll questions work pretty like checkbox ones. So, I will just skip them. <br>
Finally, let’s take a look at matrix questions.<br>
 
Before you answer the matrix question, it gives you error messages ‘No answer found for UserId:1, MatrixId:1’.<br>
 
Now, the front end sends the chosen cell of that matrix question to the back end as an array(list), then it is saved in the database.<br>
 
 

Remarks<br>
1. To run this application, you should first create a database named ‘insticator’ with the MySQL port number ‘3306’.<br>
2. To improve the matrix type to including the ‘Age/Gender message, we should change the type of options from Set<String> to Map<String, Set<String>> with the keys in the map are ‘Age’ and ‘Gender’. The values are corresponding options, for example, for the key ‘Gender’, the value is a string set containing ‘Male’ and ‘Female’.<br>
 
3. Please elaborate how to make this request scalable when there are millions of questions and millision of visitors.<br>
The screenshots of this API were shown in the ‘screenshots’ section. <br>
(1) We can use a load balancer Ribbon to direct requests to any available servers to make the requests handled in time.<br>
(2) Optimize the configuration of the servers. For example, number of threads. Of course, we need to think in detail about problems like synchronization and race condition in the concurrent and multi-thread programming.<br>
(3) Use the master-slave structure of MySQL. <br>
With loading balancer, whenever data are changed in the database in any slave server, they will be synchronized in the other servers as well.<br>
(4) Use in memory database like Redis to cache some reused data so that the server load can be less.<br>
