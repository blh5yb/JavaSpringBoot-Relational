PROFILE=$1
TAG=$2

mvn clean install -P${PROFILE} -Dmaven.test.skip=true
docker build -t spring_lamda:${TAG} .
#docker-compose build app

