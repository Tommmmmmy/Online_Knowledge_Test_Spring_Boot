# insticator_project
## Data Structures<br>
### User:<br>
<img height="300" width="500" src="https://user-images.githubusercontent.com/16629900/45603018-91db6200-b9f5-11e8-8ebb-07375e863c26.png" /><br>
### Trivia:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603024-acadd680-b9f5-11e8-8fd4-afc2d2127246.png" /><br>
### Poll:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603037-c64f1e00-b9f5-11e8-8d17-63a7b5a383e6.png" /><br>
### Checkbox:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603041-d49d3a00-b9f5-11e8-8d98-7282eeaf6ab7.png" /><br>

### Matrix:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603046-e088fc00-b9f5-11e8-8e24-2efdeab47805.png" /><br>
Also, we have different answer and its composite embeddable id tables for each type of question. Take a example of Trivia:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603051-044c4200-b9f6-11e8-9d43-cf269b79741a.png" /><br>
### TriviaansId:<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603056-1201c780-b9f6-11e8-9d10-4df301211ab4.png" /><br>
## Routers<br>
The application has many routers for users, questions, and questions under a user. <br>
The routers for a user include: <br>
##### @GetMapping("/users"): Retrieve all users in the application<br>
##### @GetMapping("/users/{id}"): Retrieve a user using the id<br>
##### @GetMapping("/users/{id}/questions"): Retrieve all questions of four types under a user’s profile<br>
The routers for questions under a user. Take the example of checkbox type<br>
##### @PostMapping("/users/{uId}/checkboxs/{qId}"): Save a user’s answers for a checkbox question<br>
##### @GetMapping("/users/{uId}/checkboxs/{qId}"): Get a user’s answers for a checkbox question<br>
##### @GetMapping("/users/{uId}/checkboxs"): Get a user’s all checkbox questions<br>
The routers for questions in general:<br>
Take the example of matrix type:<br>
##### @GetMapping("/matrixs"): Retrieve all matrix questions in the whole application<br>
##### @GetMapping("/matrixs/{id}"): Retrieve a matrix question using the id<br>
##### @GetMapping("/matrixs/{id}/options"): Retrieve a matrix question’s all options using the id<br>

## Screenshots of states (including the required web service API)<br>
A user with id 1 named Tommy is created in the main function. Four questions with each one being each type are added to the user’s profile.<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603063-23e36a80-b9f6-11e8-959a-fe7c9c61b5be.png" /><br>
Now, if a question is answered, which in the case is a checkbox one, it will not show in the user’s question list anymore.<br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603079-6147f800-b9f6-11e8-91f6-3305cbd22c81.png" /><br>
<img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603113-bab02700-b9f6-11e8-9a83-509786d9c45e.png" /><br>
You are also check the user’s answer to that question. If the user has not answered that yet or he does not have that question, exceptions will be thrown with detailed explanations.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603118-c7347f80-b9f6-11e8-851e-4f49e7337b37.png" /><br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603126-d0255100-b9f6-11e8-9e29-453ade10f4fb.png" /><br>
If the user is trying to access a type of question and it is empty, then an error message will tell the user the reasons.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603130-d87d8c00-b9f6-11e8-8520-f4c908e7f942.png" /><br>
However, you can still find the checkbox question in the question router.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603132-dfa49a00-b9f6-11e8-9549-27921ecce2c2.png" /><br>
Now, let’s look at how trivia looks like. Let’s say the user chose an answer for a trivia question and it is send to the back end.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603134-e6cba800-b9f6-11e8-85e4-ee86e10e1c62.png" /><br>

If the answer is correct, then it will give a message ‘Correct Answer!’. Otherwise, it should be ‘Wrong Answer!’.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603137-f0551000-b9f6-11e8-9285-fed914cc1531.png" /><br>
The user can also look at the answer saved in the database.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603140-f8ad4b00-b9f6-11e8-89ad-c9eb21ed1c56.png" /><br>
Now the trivia question is no longer in the user’s profile.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603144-02cf4980-b9f7-11e8-9afb-0801e008829b.png" /><br>
Poll questions work pretty like checkbox ones. So, I will just skip them. <br>
Finally, let’s take a look at matrix questions.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603147-0a8eee00-b9f7-11e8-9e34-586090066003.png" /><br>
Before you answer the matrix question, it gives you error messages ‘No answer found for UserId:1, MatrixId:1’.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603151-137fbf80-b9f7-11e8-9a3d-c7af6bed7f6c.png" /><br>
Now, the front end sends the chosen cell of that matrix question to the back end as an array(list), then it is saved in the database.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603157-1f6b8180-b9f7-11e8-885f-00963fc190a7.png" /><br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603162-2d210700-b9f7-11e8-8de1-0ada5fedf8d9.png" /><br>

## Remarks<br>
### 1. To run this application, you should first create a database named ‘insticator’ with the MySQL port number ‘3306’.<br>
### 2. To improve the matrix type to including the ‘Age/Gender message, we should change the type of options from Set<String> to Map<String, Set<String>> with the keys in the map are ‘Age’ and ‘Gender’. The values are corresponding options, for example, for the key ‘Gender’, the value is a string set containing ‘Male’ and ‘Female’.<br>
 <img height="300" width="700" src="https://user-images.githubusercontent.com/16629900/45603165-35794200-b9f7-11e8-9d54-4bb86ec5ef5f.png" /><br>
### 3. Please elaborate how to make this request scalable when there are millions of questions and millision of visitors.<br>
The screenshots of this API were shown in the ‘screenshots’ section. <br>
(1) We can use a load balancer Ribbon to direct requests to any available servers to make the requests handled in time.<br>
(2) Optimize the configuration of the servers. For example, number of threads. Of course, we need to think in detail about problems like synchronization and race condition in the concurrent and multi-thread programming.<br>
(3) Use the master-slave structure of MySQL. <br>
With loading balancer, whenever data are changed in the database in any slave server, they will be synchronized in the other servers as well.<br>
(4) Use in memory database like Redis to cache some reused data so that the server load can be less.<br>
