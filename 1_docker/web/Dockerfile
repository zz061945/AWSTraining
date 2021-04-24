FROM node
RUN yarn global add serve 
WORKDIR /web
COPY build build
CMD [ "serve", "-s", "build"]

