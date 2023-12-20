# Employee Manager (API Service)
Follow these steps to set up and run the Spring Boot Maven application:

## Prerequisites :
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) version 8 or later installed on your machine.
- [Maven](https://maven.apache.org/download.cgi) installed on your machine.
- API tester tool like 'Postman' or browser extension like - 'Talend API Tester'.



## Configurations :
- The application contains **application.properties** file (EmployeeAssignment\employwise\src\main\resources), which can be configured as per your needs.



## How to Run :

### Step 1: Navigate to the Project Directory
Navigate to the project directory using the command line or terminal:

```bash
cd path_to_project_folder/EmployeeAssignment/employwise
```

### Step 2: Build the Project
Build the project using Maven. Execute the following command in command line:

```bash
mvn clean install
```

### Step 3: Run the Application
Run the Spring Boot application using the following Maven command:

```bash
mvn spring-boot:run
```

The application will start, and you should see log messages indicating that the server has started.

### Step 4: Access the Application
Once the application has started, you can access it by navigating to [http://localhost:8080](http://localhost:8080) in your web browser.


## Entry Level Tasks :



### Adding Employee
- Method : **POST**
- Endpoint : http://localhost:8080/employees
- Request Body :
```
{
  "id": "",
  "employeeName": "Custom Employee Name",
  "phoneNumber": "1234567890",
  "email": "xyz@google.com",
  "reportsTo": "1",
  "profileImage": "https://abc.com/profile_image.jpg"
}
```
- Response : HTTP 200. Returns the auto-generated employeeId for the successful creation of record.
- Description :
Upon sending request, the user is created and the senior level manager is informed via the email.



###  Get All Employees
- Method : **GET**
- Endpoint : http://localhost:8080/employees
- Request Body : null
- Response Body :
```
[
    {
        "id": 1,
        "employeeName": "Vishwas",
        "phoneNumber": "7743898263",
        "email": "vishwasransingh1@gmail.com",
        "reportsTo": 1,
        "profileImage": "sampleurl4"
    },
    {
        "id": 2,
        "employeeName": "Abhi",
        "phoneNumber": "78618427",
        "email": "abhi@example.com",
        "reportsTo": 1,
        "profileImage": "sampleurl"
    },
    {
        "id": 3,
        "employeeName": "Bipin",
        "phoneNumber": "782382323",
        "email": "bips@example.com",
        "reportsTo": 2,
        "profileImage": "sampleurl1"
    },
    {
        "id": 4,
        "employeeName": "Chatur",
        "phoneNumber": "1234566",
        "email": "chat@example.com",
        "reportsTo": 2,
        "profileImage": "sampleurl2"
    },
    {
        "id": 5,
        "employeeName": "Diga",
        "phoneNumber": "6625384",
        "email": "diga@example.com",
        "reportsTo": 2,
        "profileImage": "sampleurl4"
    }
]

```
- Description : The request returns with the list of all the employees present on the database. The pagination and sorting option is available at other endpoint (see:  )




### Delete Employee by ID
- Method : **DELETE**
- Endpoint : http://localhost:8080/employees/{id}
- Request Body : null
- Response Body : null, HTTP 200.
- Description : This request deletes the employee of provided id in the endpoint as a Path-Variable.




### Update Employee by ID
- Method : **PUT**
- Endpoint : http://localhost:8080/employees/{id}
- Request Body : 
```
{
  "id": "",
  "employeeName": "Custom Employee Name",
  "phoneNumber": "1234567890",
  "email": "xyz@google.com",
  "reportsTo": "1",
  "profileImage": "https://abc.com/profile_image.jpg"
}
```
- Description : This request finds the existing employee by id and then replaces the necessary info of that with the new info .




## Intermediate Level Tasks :


### Get nth Level Manager of an Employee

- Method : **GET**
- Endpoint : http://localhost:8080/employees/{id}/manager/{level}
- Request Body : null
- Response Body : 
```
{
    "id": 1,
    "employeeName": "Abhi",
    "phoneNumber": "78618427",
    "email": "abhi@example.com",
    "reportsTo": 1,
    "profileImage": "sampleurl"
}

```
- Description : Upon providing employeeId and level via endpoint, the request returns with the nth level manager of the given employee.

### Get Employees with Pagination and Sorting
- Method : **GET**
- Endpoint : http://localhost:8080/employees/paginated?page=0&size=10&sortBy=phoneNumber
- Request Body : null
- Response Body : 
```
{
    "content": [
        {
            "id": 3,
            "employeeName": "Chatur",
            "phoneNumber": "1234566",
            "email": "chat@example.com",
            "reportsTo": 2,
            "profileImage": "sampleurl2"
        },
        {
            "id": 4,
            "employeeName": "Diga",
            "phoneNumber": "6625384",
            "email": "diga@example.com",
            "reportsTo": 2,
            "profileImage": "sampleurl4"
        },
        {
            "id": 5,
            "employeeName": "Eknath",
            "phoneNumber": "6625384",
            "email": "eknath@example.com",
            "reportsTo": 3,
            "profileImage": "sampleurl4"
        },
        {
            "id": 2,
            "employeeName": "Bipin",
            "phoneNumber": "782382323",
            "email": "bips@example.com",
            "reportsTo": 1,
            "profileImage": "sampleurl1"
        },
        {
            "id": 1,
            "employeeName": "Abhi",
            "phoneNumber": "78618427",
            "email": "abhi@example.com",
            "reportsTo": 1,
            "profileImage": "sampleurl"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 5,
    "totalPages": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}


```
- Description : This request allows the end user to use the pagination and sorting feature. The relevant parameters could be sent via the end-point as parameters.




## Advanced Level Tasks :

### Send Email to Level 1 Manager on New Employee Addition
- Description : Using spring-boot-starter-mail dependency, the mail-service is provided for notifying the level 1 manager.
- Configurations :
1. Currrently, the mail-service is run via the following credentials in application.properties, which may be changed as per need :
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=employee.mail.service@gmail.com
spring.mail.password=ssna asuh qgfa iytp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
2. The new mail for the pre-populated manager 'Vishwas' was created for the testing purpose with following credentials :
```
Email - vishwasransingh1@gmail.com
Password - Vishwas1@98
```

3. When the new employee reporting to 'Vishwas' (id = 1) is created, the mail-service sends the relevant mail to above email address.
