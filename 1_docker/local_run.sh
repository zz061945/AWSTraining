#!/bin/sh
docker compose down

echo "Build app..."
echo $PWD
cd app
./gradlew clean bootJar
docker build -t zz061945/awstraining_1_app .

echo "Build web..."
cd ../web
yarn build
docker build -t zz061945/awstraining_1_web .

cd ..
echo "Start system"
docker compose up -d
