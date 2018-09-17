# insticator_project
Data Structures
User:
 
Trivia:
 



Poll:
 
Checkbox
 










Matrix:
 
Also, we have different answer and its composite embeddable id tables for each type of question. Take a example of Trivia:
 







TriviaansId:
 
Routers
The application has many routers for users, questions, and questions under a user. 
The routers for a user include: 
@GetMapping("/users"): Retrieve all users in the application
@GetMapping("/users/{id}"): Retrieve a user using the id
@GetMapping("/users/{id}/questions"): Retrieve all questions of four types under a user’s profile
The routers for questions under a user. Take the example of checkbox type
@PostMapping("/users/{uId}/checkboxs/{qId}"): Save a user’s answers for a checkbox question
@GetMapping("/users/{uId}/checkboxs/{qId}"): Get a user’s answers for a checkbox question
@GetMapping("/users/{uId}/checkboxs"): Get a user’s all checkbox questions
The routers for questions in general:
Take the example of matrix type:
@GetMapping("/matrixs"): Retrieve all matrix questions in the whole application
@GetMapping("/matrixs/{id}"): Retrieve a matrix question using the id
@GetMapping("/matrixs/{id}/options"): Retrieve a matrix question’s all options using the id



Screenshots of states
(including the required web service API)
A user with id 1 named Tommy is created in the main function. Four questions with each one being each type are added to the user’s profile.
 
Now, if a question is answered, which in the case is a checkbox one, it will not show in the user’s question list anymore.
 
 
You are also check the user’s answer to that question. If the user has not answered that yet or he does not have that question, exceptions will be thrown with detailed explanations.
 
 
If the user is trying to access a type of question and it is empty, then an error message will tell the user the reasons.
 
However, you can still find the checkbox question in the question router.
 
Now, let’s look at how trivia looks like. Let’s say the user chose an answer for a trivia question and it is send to the back end.
 


If the answer is correct, then it will give a message ‘Correct Answer!’. Otherwise, it should be ‘Wrong Answer!’.
 
The user can also look at the answer saved in the database.
 
Now the trivia question is no longer in the user’s profile.
 
Poll questions work pretty like checkbox ones. So, I will just skip them. 
Finally, let’s take a look at matrix questions.
 
Before you answer the matrix question, it gives you error messages ‘No answer found for UserId:1, MatrixId:1’.
 
Now, the front end sends the chosen cell of that matrix question to the back end as an array(list), then it is saved in the database.
 
 

Remarks
1. To run this application, you should first create a database named ‘insticator’ with the MySQL port number ‘3306’.
2. To improve the matrix type to including the ‘Age/Gender message, we should change the type of options from Set<String> to Map<String, Set<String>> with the keys in the map are ‘Age’ and ‘Gender’. The values are corresponding options, for example, for the key ‘Gender’, the value is a string set containing ‘Male’ and ‘Female’.
 
3. Please elaborate how to make this request scalable when there are millions of questions and millision of visitors.
The screenshots of this API were shown in the ‘screenshots’ section. 
(1) We can use a load balancer Ribbon to direct requests to any available servers to make the requests handled in time.
(2) Optimize the configuration of the servers. For example, number of threads. Of course, we need to think in detail about problems like synchronization and race condition in the concurrent and multi-thread programming.
(3) Use the master-slave structure of MySQL. 
With loading balancer, whenever data are changed in the database in any slave server, they will be synchronized in the other servers as well.
(4) Use in memory database like Redis to cache some reused data so that the server load can be less.
