# poc_redis

## Description

POC made in [Java](https://www.java.com/en/) to learn how to a project using [Redis](https://redis.io/) and learn the basics of this technology. Also used [Postgres](https://www.postgresql.org/) to handle all the data. 

Of course that cache would only be needed in operations that would take a lot of time to gather from databases, for example.
But as told, this was only an example to understand how it works.

In order to test this the procedure was the following:

1. When starting up the project - by making `make run` in the terminal - the Postgres database starts, alongside with redis and the app itself;
2. Then there is a couple of endpoints, like get user by id (`GET /users/{id}`), update a user (`PUT /users/{id}`) and delete a user (`DELETE /users/{id}`) that make alterations to the cache;

## How to check the alterations in the cache?

After starting the whole project and make the endpoint calls that are above referred, you can make the following:

1. Go to your redis container (Check the container id by doing `docker ps` and then make <br> `docker exec -it <docker container name> bash`);
2. Once inside the container you can start the redis client (run `redis-cli`);
3. And last you can check the all the keys and values you want by making `keys '*'` and <br> `get users::{userid}` and you will see the respective objects and follow the chances when calling the endpoints;

## How to run it:

To run it you can simply make the following command:

```shell
make run
```

and to stop the application: 
```shell
make stop
```