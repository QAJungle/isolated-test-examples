docker-compose -f docker/docker-compose.yml up -d

nohup yarn start > my.log 2>&1 &

yarn cy:run

kill $(ps aux | grep 'react-scripts' | awk '{print $2}')

docker-compose -f docker/docker-compose.yml down -v --remove-orphans

