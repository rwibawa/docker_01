# docker_01
## Author: [Ryan Wibawa](mailto:ryan.wibawa@gmail.com)

### Start a docker machine
```bash
$ docker-machine ls

$ docker-machine create --driver virtualbox default
Creating CA: /Users/502662077/.docker/machine/certs/ca.pem
Creating client certificate: /Users/502662077/.docker/machine/certs/cert.pem
Running pre-create checks...
(default) Image cache directory does not exist, creating it at /Users/502662077/.docker/machine/cache...
(default) No default Boot2Docker ISO found locally, downloading the latest release...
(default) Latest release for github.com/boot2docker/boot2docker is v17.06.0-ce
(default) Downloading /Users/502662077/.docker/machine/cache/boot2docker.iso from https://github.com/boot2docker/boot2docker/releases/download/v17.06.0-ce/boot2docker.iso...
(default) 0%....10%....20%....30%....40%....50%....60%....70%....80%....90%....100%
Creating machine...
(default) Copying /Users/502662077/.docker/machine/cache/boot2docker.iso to /Users/502662077/.docker/machine/machines/default/boot2docker.iso...
(default) Creating VirtualBox VM...
(default) Creating SSH key...
(default) Starting the VM...
(default) Check network to re-create if needed...
(default) Found a new host-only adapter: "vboxnet1"
(default) Waiting for an IP...
Waiting for machine to be running, this may take a few minutes...
Detecting operating system of created instance...
Waiting for SSH to be available...
Detecting the provisioner...
Provisioning with boot2docker...
Copying certs to the local machine directory...
Copying certs to the remote machine...
Setting Docker configuration on the remote daemon...
Checking connection to Docker...
Docker is up and running!
To see how to connect your Docker Client to the Docker Engine running on this virtual machine, run: docker-machine env default

$ docker-machine env default
export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://192.168.99.100:2376"
export DOCKER_CERT_PATH="/Users/502662077/.docker/machine/machines/default"
export DOCKER_MACHINE_NAME="default"
# Run this command to configure your shell:
# eval $(docker-machine env default)

$ eval "$(docker-machine env default)"
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER        ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v17.06.0-ce

DockerHub
$ docker pull mongo
$ docker images
$ docker run mongo

$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
82b0a35bf6a3        mongo               "docker-entrypoint..."   4 minutes ago       Up 4 minutes        27017/tcp           inspiring_visvesvaraya

$ docker stop 82b0a35bf6a3
$ docker rm 82b0a35bf6a3
```

### Running integration tests with Maven
#### Maven configuration
We are going to use the docker-maven-plugin to automatically load the defined Docker images and create a container before running the integration tests. In order to support integration tests, we use the maven-failsafe-plugin.

#### Creating integration tests
Integration tests are executed after creating a MongoDB container by the maven-docker-plugin. We are going to create a test to validate the following Zoo component, which only has one operation called addAnimal that inserts an animal to MongoDB.

To test the Zoo component, we just validate, with an empty database, that after invoking the addAnimal operation, MongoDB contains a new animal.

Notice that the IP address of our MongDB database is our Docker machine.

#### Running the tests
To run our integration tests, we simply need to execute the mvn verify command.

```bash
$ mvn verify
```
