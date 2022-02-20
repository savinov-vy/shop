.PHONY: run
run:
	docker-compose up


.PHONY: build
build:
	docker-compose build

.PHONY: clean-all
clean-all:
	docker-compose down
	docker image prune
	docker image rm shop_shop:latest
	docker image rm postgres:12-alpine
	docker image rm openjdk:8
	docker image rm maven:3.6.3-jdk-8