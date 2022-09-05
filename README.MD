# Netheve RESTful API Documentation

## Login

Request :
- Method : POST
- Endpoint : `/api/account/login`
- Auth Required : NO
- Header : 
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "username": "[String]",
  "password": "[String]"
}
```
- Example :

```json
{
  "username": "foo",
  "password": "bar"
}
```

- Success Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Logged In"
  }
}
```
- Error Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Wrong username or password"
  }
}
```

## Register

Request :
- Method : POST
- Endpoint : `/api/account/register`
- Auth Required : NO
- Header :
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "username": "[String, 8-20 Character]",
  "password": "[String, 8-20 Character]",
  "password_confirmation": "[String, 8-20 Character, must be same as password above]",
  "email": "[String]",
  "firstname": "[String]",
  "lastname": "[String]"
}
```
- Example :

```json
{
  "username"  : "foobarbaz",
  "password"  : "fuzzfazz",
  "password_confirmation"  : "fuzzfazz",
  "email"     : "foo@bar.com",
  "firstname" : "bazz",
  "lastname"  : "buzz"
}
```

- Success Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Account Created"
  }
}
```
- Error Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Wrong username or password"
  }
}
```

## Update Password

Request :
- Method : POST
- Endpoint : `/api/account/change-password`
- Auth Required : YES
- Header :
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "username": "[String]",
  "oldPassword": "[String]",
  "newPassword": "[String]",
  "newConfirmationPassword": "[String]"
}
```
- Example:

```json
{
  "username": "foo",
  "oldPassword": "bar",
  "newPassword": "fizzbuzz",
  "newConfirmationPassword": "fizzbuzz"
}
```

- Success Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Password successfully changed"
  }
}
```
- Error Response :

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "error_message": "Account not found"
  }
}
```

## Get a Post 
Request :
- Method : GET
- Endpoint : `/api/post/{id}`
- Auth Required : YES
- Header :
  - Content Type : application/json
- Path Parameters :

| Parameter | Data Type |
|-----------|-----------|
| id        | Number    |   

- Request Example : `api/post/1`
- Success Response :
```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "account": {
      "id": 1,
      "username": "fuzzbazz",
      "createdAt": "2022-08-21T05:12:46.000+00:00",
      "updatedAt": "2022-08-21T05:12:47.000+00:00"
    },
    "textContent": "Hello World",
    "createdAt": "2022-08-23T05:12:30.751+00:00",
    "updatedAt": null,
    "comments": [
      {
        "id": 1,
        "text": "fuzz"
      },
      {
        "id": 2,
        "text": "fazz"
      }
    ],
    "id": 21
  }
}
```

## Create a Post
Request :
- Method : POST
- Endpoint : `/api/post`
- Auth Required : YES
- Header :
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "id": "[Number]",
  "textContent": "[String]"
}
```

- Request Example :
```json
{
  "id": 1,
  "textContent": "foo bar baz fizzbuzz"
}
```
- Success Response :
```json
{
  "code": 201,
  "status": "Created",
  "data": "Post successfully Created"
}
```

## Search a Post
Request :
- Method : GET
- Endpoint : `/api/post`
- Auth Required : YES
- Header :
  - Content Type : application/json
- Query Parameters :

| Parameter | Data Type |
|-----------|-----------|
| s         | Text      |

- Request Example : `api/post?s=fuzz`
- Success Response :
```json
{
  "code": 200,
  "status": "OK",
  "data": [
    {
      "account": {
        "id": 1,
        "username": "fuzzbarfoo",
        "createdAt": "2022-08-21T05:12:46.000+00:00",
        "updatedAt": "2022-08-21T05:12:47.000+00:00"
      },
      "textContent": "fuzz here",
      "createdAt": "2022-08-23T05:12:30.751+00:00",
      "updatedAt": null,
      "comments": [
        {
          "id": 1,
          "text": "fuzz"
        },
        {
          "id": 2,
          "text": "fazz"
        }
      ],
      "id": 21
    },
    {
      "account": {
        "id": 22,
        "username": "bazzfoobuz",
        "createdAt": "2022-08-23T05:21:36.854+00:00",
        "updatedAt": null
      },
      "textContent": "bazz here with fuzz",
      "createdAt": "2022-08-23T05:22:18.954+00:00",
      "updatedAt": null,
      "comments": [],
      "id": 23
    }
  ]
}
```

## Edit a Post
Request :
- Method : PATCH
- Endpoint : `/api/post`
- Auth Required : YES
- Header :
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "id": "[Number]",
  "textContent": "[String]"
}
```

- Request Example :

```json
{
  "id": 1,
  "textContent": "fuzz bazz edited"
}
```
- Success Response :
```json
{
  "code": 202,
  "status": "Accepted",
  "data": "Post successfully updated"
}
```
## Delete a Post
Request :
- Method : DELETE
- Endpoint : `/api/post`
- Auth Required : YES
- Header :
  - Content Type : application/json
  - Accept : application/json
- Body :

```json
{
  "id": "[Number]"
}
```

- Request Example :

```json
{
  "id": 1
}
```
- Success Response :
```json
{
  "code": 200,
  "status": "OK",
  "data": "Post successfully deleted"
}
```