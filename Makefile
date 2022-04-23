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