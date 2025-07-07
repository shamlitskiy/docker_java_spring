# Simple API

## For development:
1. Go to `./simple-api` dir and run:
```sh
docker-compose up --build
```
## For prod:
1. Add `.env` file in the `./simple-api` directory with settings:
```
REDIS_HOST=%redis_host_prod%
REDIS_PORT=%redis_port_prod%
```
2. Go to `./simple-api` dir and run:
```sh
docker-compose -f docker-compose.prod.yml up --build
```

## Example requests
```sh
curl --location --request POST 'localhost:8080/tasks' --header 'Content-Type: application/json' --data-raw '{"id": "8b171ce0-6f7b-4c22-aa6f-8b110c19f83a", "name": "A task", "description": "A task that need to be executed at the timestamp specified", "timestamp": 1645275972000 }'
```
```sh
curl --location --request POST 'localhost:8080/tasks' --header 'Content-Type: application/json' --data-raw '{"id": "8b171ce0-6f7b-4c22-aa6f-8b110c19f111", "name": "Test item", "description": "Some desc", "timestamp": 1645275972222 }'
```
```sh
curl --location --request GET 'localhost:8080/tasks'
```
```sh
curl --location --request GET 'localhost:8080/tasks/8b171ce0-6f7b-4c22-aa6f-8b110c19f111'
```
```sh
curl --location --request DELETE 'localhost:8080/tasks/8b171ce0-6f7b-4c22-aa6f-8b110c19f111'
```
