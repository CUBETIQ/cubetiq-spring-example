# Build variables
APP_MODULE_PATH=jpa
APP_PROFILE=demo

# Docker Image variables
VERSION=latest
IMAGE=api-demo
CONTAINER=$IMAGE
REGISTRY=registry.kh.cubetiqs.com
EXPOSE_PORT=8080
ROOT_HUB=$REGISTRY/$IMAGE:$VERSION