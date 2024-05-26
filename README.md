# User Feedback API

## Introduction

The User Feedback API allows users create feedback and comments for different forms of media. Giving feedback (a rating of 1 through 10) is possible for different media, such as books, but the chosen form of media for this project is movies and TV shows, keeping it a little more simple so that the API can act as a program similar to IMDB.

The three tables in this project have relationships to one another. The `Users` have a One-to-One relationship with `Feedbacks`, and the `Feedbacks` have a One-to-One relationship with `Comments`. This also logically implies that the `Comments` will show the `Users` that gave the written comment in text form, along with the `Feedback` rating of 1-10.

This API was designed to let users openly present their opinion about media they do or don't like. These users are hard coded into the database through SQL, letting them have their own table for their `id`, `name`, and `surname`. We also have hard coded values for the `Feedbacks` table, which involves the `id`, the `title` of the movie, the `description`, a `rating` of 1-10, and also a foreign key `user_id` that displays the `User` that left the rating. The `Comments` table simply has `text` as its own unique data, but it includes the foreign keys `feedback_id` and `user_id` so we can see which `user` left what `comment` for which movie `title`, and which `user` left a `rating` on the movie.

The `Feedback` and the `Comments` can have different `Users`, since this is different data. For example, a user with `id: 1` gives "Mario" (a `title` from `feedbacks` table) a `rating` of 10/10, and user with `id: 2` writes some `text` saying "this is a great movie". This also allows for the movie to have controversial opinions, as one user might give it a high `rating` through `feedback`, and another user might criticize it though `text` in a `comment`.

## Base URL
For users: `http://localhost:5618/api/users`  
For feedback: `http://localhost:5618/api/feedbacks`  
For comments: `http://localhost:5618/api/comments`  

## Endpoints
### 1. Managing Users
**View all users**
HTTP Method: `GET`
Endpoint: `/api/users`
Result: Retrieves all users in the database
Responses:
**200 OK:** List of users returned successfully.

```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 15:39:46 GMT

[
  {
    "id": 1,
    "name": "Jovan",
    "surname": "Tone"
  },
  {
    "id": 2,
    "name": "Vangel",
    "surname": "Tone"
  },
  {
    "id": 3,
    "name": "Prohor",
    "surname": "Muchev"
  },
  {
    "id": 4,
    "name": "Tristan",
    "surname": "Beason"
  }
]
Response file saved.
> 2024-05-25T173946.200.json

Response code: 200; Time: 7ms (7 ms); Content length: 173 bytes (173 B)
```
---
**View a single user**
HTTP Method: `GET`
Endpoint: `/api/users/{id}`
Result: Retrieve a single user by their ID.
Path Parameters:
-   `id` (long): User ID

Responses:
**200 OK:** Single user returned successfully
``` HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 15:53:10 GMT

{
  "id": 1,
  "name": "Jovan",
  "surname": "Tone"
}
Response file saved.
> 2024-05-25T175310.200.json

Response code: 200; Time: 18ms (18 ms); Content length: 40 bytes (40 B)
```

**404 Not Found:** User with given ID not found.
```HTTP/1.1 404 
Content-Length: 0
Date: Sat, 25 May 2024 15:51:41 GMT

<Response body is empty>

Response code: 404; Time: 115ms (115 ms); Content length: 0 bytes (0 B)
```
---
**Creating new user**
HTTP Method: `POST`
Endpoint: `/api/users`
Result: Adds new user in the database
Responses:
**200 OK:** Successfully added a new user
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 15:57:41 GMT

{
  "id": 5,
  "name": "New",
  "surname": "User"
}
Response file saved.
> 2024-05-25T175741.200.json

Response code: 200; Time: 46ms (46 ms); Content length: 38 bytes (38 B)
```

**400 Bad Request:** Failed to add user, name or surname exceeds 20 characters
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 15:58:29 GMT
Connection: close

{
  "timestamp": "2024-05-25T15:58:29.362+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.Exception: Name too long!\"\r\n\tat edu.uacs.cip.User.UserController.createUser(UserController.java:35)\r\n\tat",
  "message": "java.lang.Exception: Name too long!",
  "path": "/api/users"
}
Response file saved.
> 2024-05-25T175829.400.json

Response code: 400; Time: 19ms (19 ms); Content length: 5082 bytes (5.08 kB)
```
---
**Updating an existing user**
HTTP Method: `PUT`
Endpoint: `/api/users/{id}`
Result: Updates an existing user in the database
Path Parameters:

-   `id` (long): User ID

Responses:
**200 OK:** Successfully updated an existing user
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 16:06:24 GMT

{
  "id": 1,
  "name": "Dejan",
  "surname": "Mitov"
}
Response file saved.
> 2024-05-25T180624.200.json

Response code: 200; Time: 168ms (168 ms); Content length: 41 bytes (41 B)
```
**400 Bad Request**: Unable to update - Name too long/User not found.
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 16:08:40 GMT
Connection: close

{
  "timestamp": "2024-05-25T16:08:40.591+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.Exception: Name too long!\"\r\n\tat edu.uacs.cip.User.UserController.updateUser(UserController.java:44)\r\n\tat",
  "message": "java.lang.Exception: Name too long!",
  "path": "/api/users/1"
}
Response file saved.
> 2024-05-25T180840.400.json

Response code: 400; Time: 21ms (21 ms); Content length: 5098 bytes (5.1 kB)
```
---
**Delete an existing user**
HTTP Method: `DELETE`
Endpoint: `/api/users/{id}`
Result: Deletes an existing user in the database
Path Parameters:

-   `id` (long): User ID

Responses:
**204 OK:** Successfully deleted an existing user, no content to return
```HTTP/1.1 204 
Date: Sat, 25 May 2024 16:22:10 GMT

<Response body is empty>

Response code: 204; Time: 16ms (16 ms); Content length: 0 bytes (0 B)
```
**500 Internal Server Error:** Cannot delete if user has a comment (integrity constraint violation)
```HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 16:52:40 GMT
Connection: close

{
  "timestamp": "2024-05-25T16:52:40.321+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "org.springframework.dao.DataIntegrityViolationException: could not execute statement [Referential integrity constraint violation: \"CONSTRAINT_AB: PUBLIC.COMMENTS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from users where id=? [23503-224]] [delete from users where id=?]; SQL [delete from users where id=?]; constraint [\"CONSTRAINT_AB: PUBLIC.COMMENTS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from users where id=? [23503-224]]\r\n",
  "message": "could not execute statement [Referential integrity constraint violation: \"CONSTRAINT_AB: PUBLIC.COMMENTS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from users where id=? [23503-224]] [delete from users where id=?]; SQL [delete from users where id=?]; constraint [\"CONSTRAINT_AB: PUBLIC.COMMENTS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from users where id=? [23503-224]]",
  "path": "/api/users/1"
}
Response file saved.
> 2024-05-25T185240.500.json

Response code: 500; Time: 7ms (7 ms); Content length: 13065 bytes (13.06 kB)
```
---
### 2. Managing Feedback
**View all feedback**  
HTTP Method: `GET`  
Endpoint: `/api/feedbacks`  
Result: Retrieves all feedback in the database  
Responses:  
**200 OK:** List of feedbacks returned successfully.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:07:30 GMT

[
  {
    "id": 1,
    "title": "mario",
    "rating": 10,
    "description": "he jumps on goombas",
    "user": {
      "id": 2,
      "name": "Vangel",
      "surname": "Tone"
    }
  },
  {
    "id": 2,
    "title": "thor",
    "rating": 6,
    "description": "his hammer is heavy",
    "user": {
      "id": 1,
      "name": "Jovan",
      "surname": "Tone"
    }
  },
  {
    "id": 3,
    "title": "spiderman",
    "rating": 8,
    "description": "webslinger",
    "user": {
      "id": 3,
      "name": "Prohor",
      "surname": "Muchev"
    }
  },
  {
    "id": 4,
    "title": "batman",
    "rating": 10,
    "description": "vengeance",
    "user": {
      "id": 2,
      "name": "Vangel",
      "surname": "Tone"
    }
  },
  {
    "id": 5,
    "title": "oppenheimer",
    "rating": 7,
    "description": "explosion",
    "user": {
      "id": 3,
      "name": "Prohor",
      "surname": "Muchev"
    }
  }
]
Response file saved.
> 2024-05-25T190730.200.json

Response code: 200; Time: 193ms (193 ms); Content length: 592 bytes (592 B)
```
---
**View a single feedback**
HTTP Method: `GET`
Endpoint: `/api/feedbacks/{id}`
Result: Retrieve a single feedback by its ID.
Path Parameters:
-   `id` (long): Feedback ID

Responses:
**200 OK:** Single feedback returned successfully.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:09:55 GMT

{
  "id": 1,
  "title": "mario",
  "rating": 10,
  "description": "he jumps on goombas",
  "user": {
    "id": 2,
    "name": "Vangel",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T190955.200.json

Response code: 200; Time: 16ms (16 ms); Content length: 121 bytes (121 B)
```
**404 Not Found:** Feedback with given ID not found.
```HTTP/1.1 404 
Content-Length: 0
Date: Sat, 25 May 2024 17:10:25 GMT

<Response body is empty>

Response code: 404; Time: 5ms (5 ms); Content length: 0 bytes (0 B)
```
---
**Creating new feedback**
HTTP Method: `POST`
Endpoint: `/api/feedbacks`
Result: Adds new feedback in the database
Responses:
**200 OK:** Successfully added new feedback.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:12:02 GMT

{
  "id": 6,
  "title": "New title",
  "rating": 10,
  "description": "New description",
  "user": {
    "id": 1,
    "name": "Jovan",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T191202.200.json

Response code: 200; Time: 47ms (47 ms); Content length: 120 bytes (120 B)
```

**400 Bad Request**: Given rating is not in range 1-10
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:14:22 GMT
Connection: close

{
  "timestamp": "2024-05-25T17:14:22.093+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.Exception: Invalid Feedback rating\"\r\n\tat edu.uacs.cip.Feedback.FeedbackController.createFeedback(FeedbackController.java:28)\r\n\tat",
  "message": "java.lang.Exception: Invalid Feedback rating",
  "path": "/api/feedbacks"
}
Response file saved.
> 2024-05-25T191422.400.json

Response code: 400; Time: 19ms (19 ms); Content length: 5120 bytes (5.12 kB)
```
---
**Updating existing feedback**
HTTP Method: `PUT`
Endpoint: `/api/feedbacks/{id}`
Result: Updates the existing rating and description of the title
Path Parameters:

-   `id` (long): Feedback ID

Responses:
**200 OK:** Successfully updated the existing rating and description.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:18:41 GMT

{
  "id": 1,
  "title": "mario",
  "rating": 1,
  "description": "Updated description",
  "user": {
    "id": 1,
    "name": "Jovan",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T191841.200.json

Response code: 200; Time: 6ms (6 ms); Content length: 119 bytes (119 B)
```
<sup>(Note: I would never rate Mario 1/10)</sup>
**400 Bad Request:** Failed to update feedback, rating not in range of 1-10
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 26 May 2024 13:47:24 GMT
Connection: close

{
  "timestamp": "2024-05-26T13:47:24.872+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.IllegalArgumentException: Invalid Feedback rating\"\r\n\tat edu.uacs.cip.Feedback.FeedbackController.updateFeedback(FeedbackController.java:45)\r\n\tat java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103)\r\n",
  "message": "java.lang.IllegalArgumentException: Invalid Feedback rating",
  "path": "/api/feedbacks/1"
}
Response file saved.
> 2024-05-26T154724.400.json

Response code: 400; Time: 624ms (624 ms); Content length: 5151 bytes (5.15 kB)
```

**404 Not Found:** Feedback with given ID not found.
```HTTP/1.1 404 
Content-Length: 0
Date: Sat, 25 May 2024 17:22:53 GMT

<Response body is empty>

Response code: 404; Time: 5ms (5 ms); Content length: 0 bytes (0 B)
```



---
**Delete existing feedback**
HTTP Method: `DELETE`
Endpoint: `/api/feedbacks/{id}`
Result: Deletes existing feedback in the database
Path Parameters:
-   `id` (long): Feedback ID

Responses:
**200 OK:** Successfully deleted the feedback.
```HTTP/1.1 200 
Content-Length: 0
Date: Sat, 25 May 2024 17:26:20 GMT

<Response body is empty>

Response code: 200; Time: 30ms (30 ms); Content length: 0 bytes (0 B)
```
**500 Internal Server Error:** Cannot delete if the feedback has a user (integrity constraint violation)
```HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:27:37 GMT
Connection: close

{
  "timestamp": "2024-05-25T17:27:37.657+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "org.springframework.dao.DataIntegrityViolationException: could not execute statement [Referential integrity constraint violation: \"CONSTRAINT_A: PUBLIC.COMMENTS FOREIGN KEY(FEEDBACK_ID) REFERENCES PUBLIC.FEEDBACKS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from feedbacks where id=? [23503-224]] [delete from feedbacks where id=?]; SQL [delete from feedbacks where id=?]; constraint [\"CONSTRAINT_A: PUBLIC.COMMENTS FOREIGN KEY(FEEDBACK_ID) REFERENCES PUBLIC.FEEDBACKS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from feedbacks where id=? [23503-224]]\r\n",
  "message": "could not execute statement [Referential integrity constraint violation: \"CONSTRAINT_A: PUBLIC.COMMENTS FOREIGN KEY(FEEDBACK_ID) REFERENCES PUBLIC.FEEDBACKS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from feedbacks where id=? [23503-224]] [delete from feedbacks where id=?]; SQL [delete from feedbacks where id=?]; constraint [\"CONSTRAINT_A: PUBLIC.COMMENTS FOREIGN KEY(FEEDBACK_ID) REFERENCES PUBLIC.FEEDBACKS(ID) (CAST(1 AS BIGINT))\"; SQL statement:\ndelete from feedbacks where id=? [23503-224]]",
  "path": "/api/feedbacks/1"
}
Response file saved.
> 2024-05-25T192737.500.json

Response code: 500; Time: 155ms (155 ms); Content length: 13669 bytes (13.67 kB)
```
---
### 3. Managing Comments
**View all comments**
HTTP Method: `GET`
Endpoint: `/api/comments`
Result: Retrieves all comments in the database
Responses:
**200 OK:** List of comments returned successfully.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:46:57 GMT

[
  {
    "id": 1,
    "text": "great movie",
    "feedback": {
      "id": 1,
      "title": "mario",
      "rating": 10,
      "description": "he jumps on goombas",
      "user": {
        "id": 2,
        "name": "Vangel",
        "surname": "Tone"
      }
    },
    "user": {
      "id": 1,
      "name": "Jovan",
      "surname": "Tone"
    }
  },
  {
    "id": 2,
    "text": "bad cgi",
    "feedback": {
      "id": 2,
      "title": "thor",
      "rating": 6,
      "description": "his hammer is heavy",
      "user": {
        "id": 1,
        "name": "Jovan",
        "surname": "Tone"
      }
    },
    "user": {
      "id": 1,
      "name": "Jovan",
      "surname": "Tone"
    }
  },
  {
    "id": 3,
    "text": "very nostalgic",
    "feedback": {
      "id": 3,
      "title": "spiderman",
      "rating": 8,
      "description": "webslinger",
      "user": {
        "id": 3,
        "name": "Prohor",
        "surname": "Muchev"
      }
    },
    "user": {
      "id": 3,
      "name": "Prohor",
      "surname": "Muchev"
    }
  }
]
Response file saved.
> 2024-05-25T194657.200.json

Response code: 200; Time: 227ms (227 ms); Content length: 629 bytes (629 B)
```
---
**View a single comment**  
HTTP Method: `GET`  
Endpoint: `/api/comments/{id}`  
Result: Retrieve a single comment by its ID.  
Path Parameters:

-   `id` (long): Comment ID

Responses:  
**200 OK:** Single comment returned successfully.
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:48:19 GMT

{
  "id": 1,
  "text": "great movie",
  "feedback": {
    "id": 1,
    "title": "mario",
    "rating": 10,
    "description": "he jumps on goombas",
    "user": {
      "id": 2,
      "name": "Vangel",
      "surname": "Tone"
    }
  },
  "user": {
    "id": 1,
    "name": "Jovan",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T194819.200.json

Response code: 200; Time: 16ms (16 ms); Content length: 210 bytes (210 B)
```
**404 Not Found:** Comment with given ID not found.
```HTTP/1.1 404 
Content-Length: 0
Date: Sat, 25 May 2024 17:50:34 GMT

<Response body is empty>

Response code: 404; Time: 6ms (6 ms); Content length: 0 bytes (0 B)
```
---
**Creating new comment**  
HTTP Method: `POST`  
Endpoint: `/api/comment`  
Result: Adds new comment in the database  
Responses:  
**200 OK:** Successfully added a new comment
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:51:20 GMT

{
  "id": 4,
  "text": "batman very cool",
  "feedback": {
    "id": 4,
    "title": "batman",
    "rating": 10,
    "description": "vengeance",
    "user": {
      "id": 2,
      "name": "Vangel",
      "surname": "Tone"
    }
  },
  "user": {
    "id": 2,
    "name": "Vangel",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T195120.200.json

Response code: 200; Time: 210ms (210 ms); Content length: 207 bytes (207 B)
```
**404 Not Found:** Feedback with given ID not found, cannot create comment.
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:52:29 GMT
Connection: close

{
  "timestamp": "2024-05-25T17:52:29.707+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.RuntimeException: Feedback not found\"\r\n",
  "message": "java.lang.RuntimeException: Feedback not found",
  "path": "/api/comments/10"
}
Response file saved.
> 2024-05-25T195229.400.json

Response code: 400; Time: 18ms (18 ms); Content length: 5122 bytes (5.12 kB)
```
---
**Updating an existing comment**  
HTTP Method: `PUT`  
Endpoint: `/api/comments/{id}`  
Result: Updates an existing comment in the database  
Path Parameters:
-   `id` (long): Comment ID

Responses:  
**200 OK:** Successfully updated an existing comment
```HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:55:06 GMT

{
  "id": 1,
  "text": "Updated text",
  "feedback": {
    "id": 1,
    "title": "mario",
    "rating": 10,
    "description": "he jumps on goombas",
    "user": {
      "id": 2,
      "name": "Vangel",
      "surname": "Tone"
    }
  },
  "user": {
    "id": 1,
    "name": "Jovan",
    "surname": "Tone"
  }
}
Response file saved.
> 2024-05-25T195506.200.json

Response code: 200; Time: 170ms (170 ms); Content length: 211 bytes (211 B)
```
**404 Not Found:** Comment with given ID not found.
```HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 25 May 2024 17:56:35 GMT
Connection: close

{
  "timestamp": "2024-05-25T17:56:35.334+00:00",
  "status": 400,
  "error": "Bad Request",
  "trace": "org.springframework.web.server.ResponseStatusException: 400 BAD_REQUEST \"java.lang.RuntimeException: Comment not found\"\r\n\tat edu.uacs.cip.Comment.CommentController.updateComment(CommentController.java:51)\r\n",
  "message": "java.lang.RuntimeException: Comment not found",
  "path": "/api/comments/10"
}
Response file saved.
> 2024-05-25T195635.400.json

Response code: 400; Time: 21ms (21 ms); Content length: 5119 bytes (5.12 kB)
```
---
**Delete an existing comment**
HTTP Method: `DELETE`
Endpoint: `/api/comments/{id}`
Result: Deletes an existing comment in the database
Path Parameters:
-   `id` (long): Comment ID

Responses:
**200 OK:** Successfully deleted the comment.
```HTTP/1.1 200 
Content-Length: 0
Date: Sat, 25 May 2024 17:57:27 GMT

<Response body is empty>

Response code: 200; Time: 11ms (11 ms); Content length: 0 bytes (0 B)
```
**404 Not Found:** Comment with given ID not found.
```HTTP/1.1 404 
Content-Length: 0
Date: Sat, 25 May 2024 17:58:57 GMT

<Response body is empty>

Response code: 404; Time: 5ms (5 ms); Content length: 0 bytes (0 B)
```
