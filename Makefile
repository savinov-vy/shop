.PHONY: run
run:
	docker-compose up


.PHONY: build
build:
	docker-compose build

.PHONY: clear-all
clear-all:
	docker image prune
	docker image rm shop_shop:latest

.PHONY: prod-up
prod-up:
	docker-compose -f docker-compose.yml -f docker-compose.prod.yml up

.PHONY: prod-down
prod-down:
	docker-compose down

.PHONY: prod-up-build
prod-up-build:
	docker-compose -f docker-compose.yml -f docker-compose.prod.yml up --build

.PHONY: test
test:
	 ./mvnw test