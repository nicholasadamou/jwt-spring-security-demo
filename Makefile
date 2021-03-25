ARTIFACT_NAME ?= jwt-spring-security-demo

.PHONY: all
all: clean-war build

.PHONY: build
build: target/$(ARTIFACT_NAME).jar

.PHONY: war
war: target/$(ARTIFACT_NAME).jar

# This should fix the dependencies between push
# and build without always triggering builds.
#
target/$(ARTIFACT_NAME).jar: pom.xml src
	mvn -e -U package

.PHONY: clean-war
clean-war:
	mvn clean install
